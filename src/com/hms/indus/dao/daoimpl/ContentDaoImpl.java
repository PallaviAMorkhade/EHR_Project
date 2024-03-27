package com.hms.indus.dao.daoimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hms.indus.bo.ContentFile;
import com.hms.indus.bo.ContentMaster;
import com.hms.indus.bo.PublishDetail;
import com.hms.indus.bo.PublishMaster;
import com.hms.indus.dao.ContentDao;


@Repository
public class ContentDaoImpl implements ContentDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public String saveContent(ContentMaster contentMaster) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(contentMaster);
		} catch (Exception e) {

		}
		return "Content saved successfully";
	}

	@Override
	public String updateContent(ContentMaster contentMaster) {
		return null;
	}

	@Override
	public String deleteContent(ContentMaster contentMaster) {
		ContentMaster contentMaster1 = (ContentMaster) sessionFactory
				.getCurrentSession().get(ContentMaster.class,
						contentMaster.getContentId());
		contentMaster1.setIsActive(false);
		contentMaster1.setModifyBy(contentMaster.getModifyBy());
		contentMaster1.setModifyOn(contentMaster.getModifyOn());
		sessionFactory.getCurrentSession().update(contentMaster1);
		return "Content Deleted Successfully";
	}

	@Override
	public List<ContentMaster> listOfContentMaster() {
		String hql = "from ContentMaster WHERE isActive = :isActive";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("isActive", true);
		List<ContentMaster> contentMasters  = (List<ContentMaster>) query.list();
		List<ContentMaster> contentMasters1  = new ArrayList<ContentMaster>();
		for(ContentMaster contentMaster : contentMasters) {
			ContentMaster contentMaster1 = new ContentMaster();
			contentMaster1.setContentId(contentMaster.getContentId());
			contentMaster1.setContentTitle(contentMaster.getContentTitle());
			contentMaster1.setContentDescription(contentMaster.getContentDescription());
			contentMaster1.setContentTags(contentMaster.getContentTags());
			contentMasters1.add(contentMaster1);
		}
		return contentMasters1;
	}

	@Override
	public ContentMaster getContentByContentId(Integer contentId) {
		String hql = "from ContentMaster WHERE contentId = :contentId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("contentId", contentId);
		ContentMaster contentMaster  = (ContentMaster) query.uniqueResult();
			ContentMaster contentMaster1 = new ContentMaster();
			contentMaster1.setContentId(contentMaster.getContentId());
			contentMaster1.setContentTitle(contentMaster.getContentTitle());
			contentMaster1.setContentDescription(contentMaster.getContentDescription());
			contentMaster1.setContentTags(contentMaster.getContentTags());
			contentMaster1.setCategories(contentMaster.getCategories());
			contentMaster1.setSubCategories(contentMaster.getSubCategories());
			contentMaster1.setTagStatus(contentMaster.getTagStatus());
			Set<ContentFile> contentFiles = new HashSet<ContentFile>();
			for(ContentFile contentFile : contentMaster.getContentFiles()) {
				if(contentFile.getIsActive()) {
				ContentFile contentFile1 = new ContentFile();
				contentFile1.setFileId(contentFile.getFileId());
				contentFile1.setFileName(contentFile.getFileName());
				contentFile1.setFileType(contentFile.getFileType());
				contentFiles.add(contentFile1);
				break;
				}
			}
			contentMaster1.setContentFiles(contentFiles);
		return contentMaster1;
	}
	
	@Override
	public List<ContentMaster> contentsPreview(ContentMaster contentMaster2) {
		List<ContentMaster> contentMasters1  = new ArrayList<ContentMaster>();
		String hql = "from ContentMaster WHERE isActive = :isActive";
		String contentIdIn = " and contentId in (:contentIds)";
		String orderBy = " order by contentId desc";
		String totalQuery = hql+orderBy;
		
		//For searching filters
		String publishQuery = "";
		if(contentMaster2.getModifyBy()!=null && !contentMaster2.getModifyBy().equals("")) {
			SQLQuery publishSql = sessionFactory.getCurrentSession().createSQLQuery("SELECT GROUP_CONCAT(distinct(content_id)) as content_ids FROM ehr_publish_master where publish_date<=now()");
			publishSql.addScalar("content_ids", StringType.INSTANCE);
			String contentIds = (String)publishSql.uniqueResult();
			if(contentIds!=null) {
				publishQuery = " and content_id in ("+contentIds+")";
			}
		}
		
		List<Integer> contentIdList = new ArrayList<>();
		if(contentMaster2.getSubCategories()!=null && !contentMaster2.getSubCategories().equals("")) {
			String sql = "SELECT GROUP_CONCAT(content_id) as content_ids FROM ehr_content_master where FIND_IN_SET("+contentMaster2.getSubCategories()+",sub_categories)";
			String contentSql = sql;
			if(!publishQuery.equals("")) {
				contentSql = sql + publishQuery;
			}
			SQLQuery subCategorySql = sessionFactory.getCurrentSession().createSQLQuery(contentSql);
			subCategorySql.addScalar("content_ids", StringType.INSTANCE);
			String contentIds = (String)subCategorySql.uniqueResult();
			if(contentIds!=null) {
			List<String> ids = Arrays.asList(contentIds.split(","));
			for (String id : ids) {
				contentIdList.add(Integer.valueOf(id));
			}
			totalQuery = hql + contentIdIn + orderBy;
			}
		}
		else if(contentMaster2.getCategories()!=null && !contentMaster2.getCategories().equals("")) {
			SQLQuery subCategorySql = sessionFactory.getCurrentSession().createSQLQuery("select GROUP_CONCAT(sub_category_id) as subcategories from ehr_sub_category_master where category_id ="+contentMaster2.getCategories()+"");
			subCategorySql.addScalar("subcategories", StringType.INSTANCE);
			String subCategories = (String)subCategorySql.uniqueResult();
			StringBuffer findSub = new StringBuffer();
			if(subCategories!=null) {
			String[] subCat = subCategories.split(",");
				for (int j=0;j<subCat.length;j++) {
					if(j==0) {
						findSub.append("or ( FIND_IN_SET("+subCat[j]+",sub_categories)");
					}else {
						findSub.append("or FIND_IN_SET("+subCat[j]+",sub_categories)");
					}
					
					if((j+1)==subCat.length) {
						findSub.append(")");
					}
				}
			}
			
			String query1 = "SELECT GROUP_CONCAT(content_id) as content_ids FROM ehr_content_master where FIND_IN_SET("+contentMaster2.getCategories()+",categories)";
			String contentQuery = query1;
			if(!findSub.equals("")) {
				contentQuery = query1 + findSub;
			}
			if(!publishQuery.equals("")) {
				contentQuery = contentQuery + publishQuery;
			}
			SQLQuery categorySql = sessionFactory.getCurrentSession().createSQLQuery(contentQuery);
			categorySql.addScalar("content_ids", StringType.INSTANCE);
			String contentIds = (String)categorySql.uniqueResult();
			if(contentIds!=null) {
			List<String> ids = Arrays.asList(contentIds.split(","));
			for (String id : ids) {
				contentIdList.add(Integer.valueOf(id));
			}
			totalQuery = hql + contentIdIn + orderBy;
			}
		}else if(!publishQuery.equals("")) {
			totalQuery = hql + publishQuery + orderBy;
		}
		//End Of searching filters
		
		Query query = sessionFactory.getCurrentSession().createQuery(totalQuery);
		query.setParameter("isActive", true);
		if(contentIdList.size()>0) {
			query.setParameterList("contentIds", contentIdList);
		}
		
		if(contentMaster2.getContentId()!=null) {
			query.setFirstResult(contentMaster2.getContentId());
		}
		query.setMaxResults(30);
		List<ContentMaster> contentMasters  = (List<ContentMaster>) query.list();
		for(ContentMaster contentMaster : contentMasters) {
			ContentMaster contentMaster1 = new ContentMaster();
			contentMaster1.setContentId(contentMaster.getContentId());
			contentMaster1.setContentTitle(contentMaster.getContentTitle());
			contentMaster1.setContentDescription(contentMaster.getContentDescription().replaceAll("\\<.*?\\>", ""));
			contentMaster1.setContentTags(contentMaster.getContentTags());
			contentMaster1.setAddedOn(contentMaster.getAddedOn());
			
			//For Category Fetching
			if(contentMaster.getCategories()!=null && !contentMaster.getCategories().equals("")) {
				SQLQuery categorySql = sessionFactory.getCurrentSession().createSQLQuery("select GROUP_CONCAT(category) as categories from ehr_category_master where category_id in ("+contentMaster.getCategories()+")");
				categorySql.addScalar("categories", StringType.INSTANCE);
				String categories = (String)categorySql.uniqueResult();
				contentMaster1.setCategories(categories);
			}else {
				contentMaster1.setCategories("-");
			}
			
			//For Sub-Category Fetching
			if(contentMaster.getSubCategories()!=null && !contentMaster.getSubCategories().equals("")) {
				SQLQuery subCategorySql = sessionFactory.getCurrentSession().createSQLQuery("select GROUP_CONCAT(sub_category) as subcategories from ehr_sub_category_master where sub_category_id in ("+contentMaster.getSubCategories()+")");
				subCategorySql.addScalar("subcategories", StringType.INSTANCE);
				String subCategories = (String)subCategorySql.uniqueResult();
				contentMaster1.setSubCategories(subCategories);
			}else {
				contentMaster1.setSubCategories("-");
			}
			
			Set<ContentFile> contentFiles = new HashSet<ContentFile>();
			for(ContentFile contentFile : contentMaster.getContentFiles()) {
				if(contentFile.getIsActive()) {
				ContentFile contentFile1 = new ContentFile();
				contentFile1.setFileId(contentFile.getFileId());
				contentFile1.setFileName(contentFile.getFileName());
				contentFile1.setFileType(contentFile.getFileType());
				contentFiles.add(contentFile1);
				break;
				}
			}
			contentMaster1.setContentFiles(contentFiles);
			
			Set<PublishMaster> publishMasters = new HashSet<PublishMaster>();
			for(PublishMaster publishMaster : contentMaster.getPublishMasters()) {
				if(publishMaster.getIsActive()) {
					PublishMaster publishMaster1 = new PublishMaster();
					publishMaster1.setPublishId(publishMaster.getPublishId());
					publishMaster1.setPublishDate(publishMaster.getPublishDate());
					publishMasters.add(publishMaster1);
				}
			}
			contentMaster1.setPublishMasters(publishMasters);
			contentMasters1.add(contentMaster1);
		}
		
		if(((contentMaster2.getSubCategories()!=null && !contentMaster2.getSubCategories().equals(""))
				|| (contentMaster2.getCategories()!=null && !contentMaster2.getCategories().equals("")))
				&& contentIdList.size()==0) {
			contentMasters1 = null;
		}
		return contentMasters1;
	}

	@Override
	public String publishContent(PublishDetail publishDetail) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(publishDetail);
		} catch (Exception e) {

		}
		return "Content Published successfully";
	}

}

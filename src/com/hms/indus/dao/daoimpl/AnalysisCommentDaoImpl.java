package com.hms.indus.dao.daoimpl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hms.indus.bo.AnalysisCommentMaster;
import com.hms.indus.dao.AnalysisCommentDao;

@Repository
public class AnalysisCommentDaoImpl implements AnalysisCommentDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public String saveAnalysisComment(AnalysisCommentMaster analysisCommentMaster) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(analysisCommentMaster);
		} catch (Exception e) {

		}
		return "Analysis Comment Saved Successfully";
	}

	@Override
	public String deleteAnalysisComment(AnalysisCommentMaster analysisanalysisCommentMaster) {
		AnalysisCommentMaster analysisanalysisCommentMaster1 = (AnalysisCommentMaster) sessionFactory.getCurrentSession().get(AnalysisCommentMaster.class,
				analysisanalysisCommentMaster.getCommentId());
		analysisanalysisCommentMaster1.setIsActive(false);
		analysisanalysisCommentMaster1.setModifyBy(analysisanalysisCommentMaster.getModifyBy());
		analysisanalysisCommentMaster1.setModifyOn(analysisanalysisCommentMaster.getModifyOn());
		sessionFactory.getCurrentSession().update(analysisanalysisCommentMaster1);
		return "Analysis Comment Deleted Successfully";
	}

	@Override
	public List<AnalysisCommentMaster> listOfAnalysisCommentMaster() {
		String hql = "from AnalysisCommentMaster WHERE isActive = :isActive";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("isActive", true);
		List<AnalysisCommentMaster> analysisComments = (List<AnalysisCommentMaster>) query.list();
		List<AnalysisCommentMaster> analysisComments1 = new ArrayList<AnalysisCommentMaster>();
		for (AnalysisCommentMaster analysisComment : analysisComments) {
			AnalysisCommentMaster analysisComment1 = new AnalysisCommentMaster();
			analysisComment1.setCommentId(analysisComment.getCommentId());
			analysisComment1.setComment(analysisComment.getComment());
			analysisComments1.add(analysisComment1);
		}
		return analysisComments1;
	}

	@Override
	public AnalysisCommentMaster getAnalysisCommentByCommentId(Integer analysisCommentId) {
		String hql = "from AnalysisCommentMaster WHERE commentId = :commentId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("commentId", analysisCommentId);
		AnalysisCommentMaster analysisComment = (AnalysisCommentMaster) query.uniqueResult();
		AnalysisCommentMaster analysisComment1 = new AnalysisCommentMaster();
		analysisComment1.setCommentId(analysisComment.getCommentId());
		analysisComment1.setComment(analysisComment.getComment());
		return analysisComment1;
	}

}

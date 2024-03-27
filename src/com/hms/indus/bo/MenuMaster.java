package com.hms.indus.bo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "ehr_menu_master")
public class MenuMaster {
	
	@Id
	@GeneratedValue
	@Column(name = "menu_id")
	private Integer menuId;
	
	@Column(name = "menu_name")
	private String menuName;
	
	@OneToMany(mappedBy = "menuMaster")
	@LazyCollection(value=LazyCollectionOption.FALSE)
	private List<SubMenuMaster> subMenuMastersList;

	public Integer getMenuId() {
		return menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public List<SubMenuMaster> getSubMenuMastersList() {
		return subMenuMastersList;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public void setSubMenuMastersList(List<SubMenuMaster> subMenuMastersList) {
		this.subMenuMastersList = subMenuMastersList;
	}
	

}

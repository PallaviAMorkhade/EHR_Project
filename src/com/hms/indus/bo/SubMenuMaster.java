package com.hms.indus.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "ehr_sub_menu_master")
public class SubMenuMaster {
	
	@Id
	@GeneratedValue
	@Column(name = "sub_menu_id")
	private Integer subMenuId;
	
	@Column(name = "sub_menu_name")
	private String subMenuName;
	
	@ManyToOne
	@JoinColumn(name = "menu_id")
	private MenuMaster menuMaster;

	public Integer getSubMenuId() {
		return subMenuId;
	}

	public String getSubMenuName() {
		return subMenuName;
	}

	@JsonIgnore
	public MenuMaster getMenuMaster() {
		return menuMaster;
	}

	public void setSubMenuId(Integer subMenuId) {
		this.subMenuId = subMenuId;
	}

	public void setSubMenuName(String subMenuName) {
		this.subMenuName = subMenuName;
	}

	public void setMenuMaster(MenuMaster menuMaster) {
		this.menuMaster = menuMaster;
	}
	
	

}

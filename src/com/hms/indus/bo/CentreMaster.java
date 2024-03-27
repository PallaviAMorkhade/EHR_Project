package com.hms.indus.bo;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "ehr_center_master")
public class CentreMaster implements Comparator<CentreMaster>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "center_id")
	private Integer centreId;
	
	@Column(name = "center_name")
	private String centreName;
	
	/*@Column(name = "center_shortname")
	private String centreShortName;*/
	
	@Column(name = "isactive")
	private Character isActive;
	
	@Column(name = "add_at",updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date addAt;
	
	@Column(name = "add_by",updatable=false)
	private String addBy;
	
	@Column(name = "mod_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modAt;
	
	@Column(name = "mod_by")
	private String modBy;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "centreMaster")
	@LazyCollection(value=LazyCollectionOption.FALSE)
	private Set<ClientReportHead> clientReportHeadSet = new HashSet<ClientReportHead>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "centreMaster")
	//@LazyCollection(value=LazyCollectionOption.FALSE)
	private Set<CheckUpMaster> checkUpMasterSet = new HashSet<CheckUpMaster>();

	@JsonIgnore
	public Set<ClientReportHead> getClientReportHeadSet() {
		return clientReportHeadSet;
	}

	//@JsonIgnore
	public Set<CheckUpMaster> getCheckUpMasterSet() {
		return checkUpMasterSet;
	}

	public void setClientReportHeadSet(Set<ClientReportHead> clientReportHeadSet) {
		this.clientReportHeadSet = clientReportHeadSet;
	}

	public void setCheckUpMasterSet(Set<CheckUpMaster> checkUpMasterSet) {
		this.checkUpMasterSet = checkUpMasterSet;
	}

	public Integer getCentreId() {
		return centreId;
	}

	public void setCentreId(Integer centreId) {
		this.centreId = centreId;
	}

	public String getCentreName() {
		return centreName;
	}

	public void setCentreName(String centreName) {
		this.centreName = centreName;
	}

	/*public String getCentreShortName() {
		return centreShortName;
	}

	public void setCentreShortName(String centreShortName) {
		this.centreShortName = centreShortName;
	}*/

	public Character getIsActive() {
		return isActive;
	}

	public void setIsActive(Character isActive) {
		this.isActive = isActive;
	}

	public String getAddBy() {
		return addBy;
	}

	public void setAddBy(String addBy) {
		this.addBy = addBy;
	}

	public String getModBy() {
		return modBy;
	}

	public void setModBy(String modBy) {
		this.modBy = modBy;
	}

	public Date getAddAt() {
		return addAt;
	}

	public void setAddAt(Date addAt) {
		this.addAt = addAt;
	}

	public Date getModAt() {
		return modAt;
	}

	public void setModAt(Date modAt) {
		this.modAt = modAt;
	}

	@Override
	public int compare(CentreMaster o1, CentreMaster o2) {
		return o1.getCentreName().compareToIgnoreCase(o2.getCentreName());
	}
}

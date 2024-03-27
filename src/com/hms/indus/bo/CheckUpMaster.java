package com.hms.indus.bo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "checkup_master")
public class CheckUpMaster {

	@Id
	@GeneratedValue
	@Column(name = "checkup_id")
	private Integer checkUpId;
	
	@Column(name = "checkup_date")
	private String checkUpDate;
	
	@Column(name = "hospital_reg_no")
	private String hospitalRegistrationNo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "client_id")
	private ClientMaster clientMaster;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "center_id")
	private CentreMaster centreMaster;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "package_id")
	private PackageMaster packageMaster;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "checkUpMaster")
	private Set<ClientReportHead> clientReportHeadSet = new HashSet<ClientReportHead>();
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="visit_type_id")
	@NotFound( action = NotFoundAction.IGNORE )
	private VisitTypeMaster visitTypeMaster;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="test_id")
	private TestMaster testMaster;
	
	@Column(name="health_report", columnDefinition = "BOOLEAN")
	private Boolean healthReport = false;
	
	@Column(name = "hr_generated_by")
	private Integer hrGeneratedBy;
	
	@Column(name = "ap_app_no")
	private String apAppNo;
	
	@Column(name = "ap_year")
	private String apYear;
	
	@Column(name = "hr_generated_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date hrGeneratedOn;
	
	@Column(name = "added_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date addedOn;
	
	@Column(name = "ap_member_id")
	private String apMemberId;
	
	public Boolean getHealthReport() {
		return healthReport;
	}

	public void setHealthReport(Boolean healthReport) {
		this.healthReport = healthReport;
	}

	public Integer getHrGeneratedBy() {
		return hrGeneratedBy;
	}

	public void setHrGeneratedBy(Integer hrGeneratedBy) {
		this.hrGeneratedBy = hrGeneratedBy;
	}

	public Date getHrGeneratedOn() {
		return hrGeneratedOn;
	}

	public void setHrGeneratedOn(Date hrGeneratedOn) {
		this.hrGeneratedOn = hrGeneratedOn;
	}

	//@JsonIgnore
	public TestMaster getTestMaster() {
		return testMaster;
	}

	public void setTestMaster(TestMaster testMaster) {
		this.testMaster = testMaster;
	}

	public Integer getCheckUpId() {
		return checkUpId;
	}

	public String getCheckUpDate() {
		return checkUpDate;
	}

	public String getHospitalRegistrationNo() {
		return hospitalRegistrationNo;
	}

	@JsonIgnore
	public ClientMaster getClientMaster() {
		return clientMaster;
	}

	@JsonIgnore
	public CentreMaster getCentreMaster() {
		return centreMaster;
	}

	//@JsonIgnore
	public PackageMaster getPackageMaster() {
		return packageMaster;
	}

	@JsonIgnore
	public Set<ClientReportHead> getClientReportHeadSet() {
		return clientReportHeadSet;
	}

	public void setCheckUpId(Integer checkUpId) {
		this.checkUpId = checkUpId;
	}

	public void setCheckUpDate(String checkUpDate) {
		this.checkUpDate = checkUpDate;
	}

	public void setHospitalRegistrationNo(String hospitalRegistrationNo) {
		this.hospitalRegistrationNo = hospitalRegistrationNo;
	}

	public void setClientMaster(ClientMaster clientMaster) {
		this.clientMaster = clientMaster;
	}

	public void setCentreMaster(CentreMaster centreMaster) {
		this.centreMaster = centreMaster;
	}

	public void setPackageMaster(PackageMaster packageMaster) {
		this.packageMaster = packageMaster;
	}

	public void setClientReportHeadSet(Set<ClientReportHead> clientReportHeadSet) {
		this.clientReportHeadSet = clientReportHeadSet;
	}

	//@JsonIgnore
	public VisitTypeMaster getVisitTypeMaster() {
		return visitTypeMaster;
	}

	public void setVisitTypeMaster(VisitTypeMaster visitTypeMaster) {
		this.visitTypeMaster = visitTypeMaster;
	}

	public String getApAppNo() {
		return apAppNo;
	}

	public void setApAppNo(String apAppNo) {
		this.apAppNo = apAppNo;
	}

	public String getApYear() {
		return apYear;
	}

	public void setApYear(String apYear) {
		this.apYear = apYear;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	public String getApMemberId() {
		return apMemberId;
	}

	public void setApMemberId(String apMemberId) {
		this.apMemberId = apMemberId;
	}

	
	
	
}

package com.hms.indus.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "ehr_task_slave")
public class TaskSlave implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "task_slave_id")
	private Integer taskSlaveId;
	
	@Column(name = "client_id")
	private Integer clientId;

	@Column(name = "checkup_id")
	private Integer checkUpId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task_id", nullable = false)
	private TaskMaster taskMaster;
	
	@Column(name = "submitted_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date submittedOn;
	
	@Transient
	private Integer workStatusId;

	public Integer getWorkStatusId() {
		return workStatusId;
	}

	public void setWorkStatusId(Integer workStatusId) {
		this.workStatusId = workStatusId;
	}

	public Integer getTaskSlaveId() {
		return taskSlaveId;
	}

	public void setTaskSlaveId(Integer taskSlaveId) {
		this.taskSlaveId = taskSlaveId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getCheckUpId() {
		return checkUpId;
	}

	public void setCheckUpId(Integer checkUpId) {
		this.checkUpId = checkUpId;
	}
	
	public TaskMaster getTaskMaster() {
		return taskMaster;
	}

	public void setTaskMaster(TaskMaster taskMaster) {
		this.taskMaster = taskMaster;
	}

	public Date getSubmittedOn() {
		return submittedOn;
	}

	public void setSubmittedOn(Date submittedOn) {
		this.submittedOn = submittedOn;
	}
	
	
}

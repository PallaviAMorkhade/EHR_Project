package com.hms.indus.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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

@Entity
@Table(name = "ehr_task_master")
public class TaskMaster implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "task_id")
	private Integer taskId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assigned_to", nullable = false)
	private UserMaster assignedTo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assigned_by", nullable = false)
	private UserMaster assignedBy;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "progress")
	private String progress;
	
	@Column(name = "task_description")
	private String taskDescription;
	
	@Column(name = "assigned_on",updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date assignedOn;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "taskMaster",cascade=CascadeType.ALL)
	private List<TaskSlave> taskSlaves;
	
	@Column(name = "work_status_id")
	private Integer workStatusId;

	public Integer getWorkStatusId() {
		return workStatusId;
	}

	public void setWorkStatusId(Integer workStatusId) {
		this.workStatusId = workStatusId;
	}

	public List<TaskSlave> getTaskSlaves() {
		return taskSlaves;
	}

	public void setTaskSlaves(List<TaskSlave> taskSlaves) {
		this.taskSlaves = taskSlaves;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public UserMaster getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(UserMaster assignedTo) {
		this.assignedTo = assignedTo;
	}

	public UserMaster getAssignedBy() {
		return assignedBy;
	}

	public void setAssignedBy(UserMaster assignedBy) {
		this.assignedBy = assignedBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public Date getAssignedOn() {
		return assignedOn;
	}

	public void setAssignedOn(Date assignedOn) {
		this.assignedOn = assignedOn;
	}
	
}

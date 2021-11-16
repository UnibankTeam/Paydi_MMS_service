package com.paydi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "mms_audit_logs")
public class MMSAuditLogEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "entity_type")
	private String entityType;
	@Column(name = "entity_id")
	private String entityId;
	@Column(name = "request_url")
	private String requestUrl;
	@Column(name = "action_code")
	private String actionCode;
	@Column(name = "request_content")
	private String requestContent;
	@Column(name = "response_content")
	private String responseContent;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "updated_by")
	private String updatedBy;
	@Column(name = "updated_date")
	private Date updatedDate;
	@Column(name = "created_date")
	private Date createdDate;

}
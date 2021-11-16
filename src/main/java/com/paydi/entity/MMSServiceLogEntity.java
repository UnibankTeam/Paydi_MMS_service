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
@Table(name = "mms_service_logs")
public class MMSServiceLogEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String channel;
	@Column(name = "service_code")
	private String serviceCode;
	@Column(name = "request_type")
	private String requestType;
	@Column(name = "request_url")
	private String requestUrl;
	@Column(name = "request_content")
	private String requestContent;
	@Column(name = "response_content")
	private String responseContent;
	@Column(name = "is_success")
	private int isSuccess;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "updated_by")
	private String updatedBy;
	@Column(name = "updated_date")
	private Date updatedDate;
	@Column(name = "created_date")
	private Date createdDate;

}
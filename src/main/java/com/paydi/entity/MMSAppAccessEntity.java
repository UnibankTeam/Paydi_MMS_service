package com.paydi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "mms_app_access")
public class MMSAppAccessEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "name")
	private String name;
	@Column(name = "desc")
	private String desc;
	@Column(name = "external_id")
	private String externalId;
	@Column(name = "hierarchy")
	private String hierarchy;
	@Column(name = "parent_id")
	private String parentId;
	@Column(name = "status")
	private String status;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "updated_by")
	private String updatedBy;
	@Column(name = "updated_date")
	private Date updatedDate;
	@Column(name = "created_date")
	private Date createdDate;

	@Transient
	private String apiKey;

	public MMSAppAccessEntity() {
	}

	public MMSAppAccessEntity(long id, String apiKey, String name, String desc, String externalId) {
		this.id = id;
		this.apiKey = apiKey;
		this.name = name;
		this.desc = desc;
		this.externalId = externalId;
	}

}
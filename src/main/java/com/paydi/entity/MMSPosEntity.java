package com.paydi.entity;

import java.sql.Date;

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
@Setter
@Getter
@Table(name = "mms_tid")
public class MMSPosEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "merchant_id")
	private Long merchantId;
	@Column(name = "tid")
	private String tid;
	@Column(name = "terminal_name")
	private String terminalName;
	@Column(name = "min_rev_default")
	private Long minRevDefault;
	@Column(name = "`status`")
	private int status;
	@Column(name = "created_by")
	private Long createdBy;
	@Column(name = "updated_by")
	private Long updatedBy;
	@Column(name = "updated_date")
	private Date updatedDate;
	@Column(name = "created_date")
	private Date createdDate;

	public MMSPosEntity() {
		super();
	}

	public MMSPosEntity(long id, Long merchantId, String tid, String terminalName, Long minRevDefault, 
			int status, Long createdBy, Long updatedBy, Date updatedDate, Date createdDate) {
		this.id = id;
		this.merchantId = merchantId;
		this.tid = tid;
		this.terminalName = terminalName;
		this.minRevDefault = minRevDefault;
		this.status = status;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.createdDate = createdDate;
	}

}
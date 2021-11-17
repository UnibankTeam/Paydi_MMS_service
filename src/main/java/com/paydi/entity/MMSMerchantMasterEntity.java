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
@Setter
@Getter
@Table(name = "mms_merchant")
public class MMSMerchantMasterEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "partner_id")
	private long partnerId;
	@Column(name = "code")
	private String code;
	@Column(name = "mid_master_code")
	private String midMasterCode;
	@Column(name = "`desc`")
	private String desc;
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

	public MMSMerchantMasterEntity() {
	}

}
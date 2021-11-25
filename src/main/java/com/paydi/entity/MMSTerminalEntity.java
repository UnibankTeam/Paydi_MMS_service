package com.paydi.entity;

import java.sql.Date;

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
@Table(name = "mms_terminal")
public class MMSTerminalEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "factory")
	private String factory;
	@Column(name = "serial_number")
	private String serialNumber;
	@Column(name = "merchant_id")
	private Long merchantId;
	@Column(name = "model")
	private String model;
	@Column(name = "assets_code")
	private String assetsCode;
	// @Column(name = "bank_code")
	// private String bankCode;
	@Column(name = "setup_address")
	private String setupAddress;
	@Column(name = "contract_no")
	private String contractNo;
	@Column(name = "contract_date")
	private Date contractDate;
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

	public MMSTerminalEntity() {
		super();
	}

	public MMSTerminalEntity(long id, String serialNumber, String model,
			// String bankCode,
			int status, Long createdBy, Long updatedBy, Date updatedDate, Date createdDate) {
		this.id = id;
		this.serialNumber = serialNumber;
		this.model = model;
		// this.bankCode = bankCode;
		this.status = status;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.createdDate = createdDate;
	}

}
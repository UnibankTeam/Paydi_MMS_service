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
@Table(name = "mms_pos_rate_config")
public class MMSPosRateConfigEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "tid")
	private Long tid;
	@Column(name = "cost_rate")
	private double costRate;
	@Column(name = "cogs_rate")
	private double cogsRate;
	@Column(name = "card_type")
	private String cardType;
	@Column(name = "merchant_id")
	private Long merchantId;
	@Column(name = "status")
	private int status;
	@Column(name = "created_by")
	private Long createdBy;
	@Column(name = "updated_by")
	private Long updatedBy;
	@Column(name = "updated_date")
	private Date updatedDate;
	@Column(name = "created_date")
	private Date createdDate;

	public MMSPosRateConfigEntity() {
	}

}
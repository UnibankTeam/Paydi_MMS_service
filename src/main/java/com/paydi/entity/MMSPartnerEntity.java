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
@Table(name = "mms_partner")
public class MMSPartnerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "code", length = 25)
	private String code;
	@Column(name = "`desc`")
	private String desc;
	@Column(name = "odoo_contact_id")
	private String odooContactId;
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
	@Column(name = "owner_external_id")
	private String ownerExternalId;

	public MMSPartnerEntity() {
		super();
	}

	public MMSPartnerEntity(long id, String code, String desc, String ownerExternalId, String odooContactId) {
		this.id = id;
		this.code = code;
		this.desc = desc;
		this.ownerExternalId = ownerExternalId;
		this.odooContactId = odooContactId;

	}

}
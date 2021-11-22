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
@Table(name = "mms_terminal_tid_mapping")
public class MMSTerminalTidMappingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "serial_number")
	private String serialNumber;
	@Column(name = "tid")
	private String tid;
	// active: status = 1, inactive status = 0
	@Column(name = "`status`")
	private int status;
	@Column(name = "active_by")
	private Long activeBy;
	@Column(name = "inactive_by")
	private Date inactiveBy;
	@Column(name = "active_date")
	private Long activeDate;
	@Column(name = "inactive_date")
	private Date inactiveDate;

	public MMSTerminalTidMappingEntity() {
	}

}
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
@Table(name = "mms_msg_log")
public class MMSMsgLogEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "uuid")
	private String uuid;
	@Column(name = "queue")
	private String queue;
	@Column(name = "msg")
	private String msg;
	@Column(name = "status")
	private String status;
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "execute")
	private int execute;

}

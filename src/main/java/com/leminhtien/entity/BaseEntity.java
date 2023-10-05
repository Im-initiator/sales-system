package com.leminhtien.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="create_date")
	private Date createDate;
	
	@Column(name="modify_date")
	private Date modifyDate;
	
	@Column(name="create_by")
	private String createBy;
	
	@Column(name="modify_by")
	private String modifyBy;
	
}

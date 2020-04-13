package com.devmobil.algamoney.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Posting extends BaseEntity {

	@NotNull
	private String description;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expiredAt;
	
	@Null
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate payedAt;
	
	@NotNull
	@Column(name = "value", precision = 10, scale= 2)
	private BigDecimal value;

	@Null
	private String observation;

	@Enumerated(EnumType.STRING)   
	private TypePosting type;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;
	
}

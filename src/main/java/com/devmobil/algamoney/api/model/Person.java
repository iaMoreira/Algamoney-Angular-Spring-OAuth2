package com.devmobil.algamoney.api.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name= "person")
public class Person extends BaseEntity{

	@NotNull
	@Size(min = 3, max = 30)
	private String name;
//	private boolean status;
	@Embedded
	private Address address;
	
}

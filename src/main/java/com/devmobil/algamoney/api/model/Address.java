package com.devmobil.algamoney.api.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class Address {
	private String place;
	private String number;
	private String complement;
	private String district;
	private String cep;
	private String city;
	private String state;
	
}

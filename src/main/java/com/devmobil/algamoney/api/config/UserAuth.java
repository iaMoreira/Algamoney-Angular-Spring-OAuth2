package com.devmobil.algamoney.api.config;


import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.devmobil.algamoney.api.model.User;

public class UserAuth extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 1L;

	private User user;

	public UserAuth(User user, Collection<? extends GrantedAuthority> authorities) {
		super(user.getEmail(), user.getPassword(), authorities);
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}

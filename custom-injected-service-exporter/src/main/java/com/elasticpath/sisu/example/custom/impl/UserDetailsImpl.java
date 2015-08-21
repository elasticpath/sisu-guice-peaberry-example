package com.elasticpath.sisu.example.custom.impl;

import javax.inject.Named;


import com.elasticpath.sisu.example.apis.User;

import com.elasticpath.sisu.example.guice.custom.InjectScope;
import com.elasticpath.sisu.example.guice.custom.InjectUserId;

@Named
public class UserDetailsImpl implements User {

	@InjectUserId
	private String userId;
	@InjectScope
	private String storeCode;

	public String printUserDetails() {
		return "UserID[" + userId + "] Scope[" + storeCode + "]";
	}
}

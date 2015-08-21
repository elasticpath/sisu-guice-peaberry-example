package com.cjbooms.sisu.test.custom.impl;

import javax.inject.Named;


import com.cjbooms.sisu.test.apis.User;

import com.elasticpath.sisu.guice.custom.InjectScope;
import com.elasticpath.sisu.guice.custom.InjectUserId;

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

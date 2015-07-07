package com.cjbooms.sisu.test.custom.impl;

import javax.inject.Named;

import com.cjbooms.sisu.test.apis.InjectScope;
import com.cjbooms.sisu.test.apis.InjectUserId;
import com.cjbooms.sisu.test.apis.User;

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

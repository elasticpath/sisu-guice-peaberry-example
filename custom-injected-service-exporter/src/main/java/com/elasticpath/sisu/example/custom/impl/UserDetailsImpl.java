package com.elasticpath.sisu.example.custom.impl;

import javax.inject.Named;


import com.elasticpath.sisu.example.apis.User;

import com.elasticpath.sisu.example.guice.custom.UserComment;
import com.elasticpath.sisu.example.guice.custom.UserName;

@Named
public class UserDetailsImpl implements User {

	@UserName
	private String userName;
	@UserComment
	private String comment;

	public String printUserDetails() {
		return userName + " " + comment;
	}
}

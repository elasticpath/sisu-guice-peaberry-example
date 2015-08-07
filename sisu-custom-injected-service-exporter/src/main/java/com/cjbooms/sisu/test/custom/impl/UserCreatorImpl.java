package com.cjbooms.sisu.test.custom.impl;


import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;

import com.cjbooms.sisu.test.apis.User;
import com.cjbooms.sisu.test.apis.UserCreator;

import org.osgi.service.component.annotations.Component;

@Singleton
@Named
@Component(
		service = UserCreator.class,
		immediate = true)
public class UserCreatorImpl implements UserCreator {

	Provider<User> userProvider;

	@Inject
	public UserCreatorImpl(final Provider<User> userProvider) {
		this.userProvider = userProvider;
	}

	@Override
	public User createUser() {
		return userProvider.get();
	}
}

package com.elasticpath.sisu.example.custom.impl;


import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;

import com.elasticpath.sisu.example.apis.User;
import com.elasticpath.sisu.example.apis.UserCreator;
import org.eclipse.sisu.contrib.peaberry.annotations.ServiceExport;

@Singleton
@Named
@ServiceExport(service = UserCreator.class)
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

package org.sonatype.examples.peaberry.test.impl;

import javax.inject.Named;

/**
 * Created by conor on 06/07/15.
 */
@Named
public class CustomInjectionPointExample {

	@InjectUserId
	private String userId;
	@InjectScope
	private String storeCode;

	public String printCustomInjectionPoints() {
		return "UserID[" + userId + "] Scope[" + storeCode + "]";
	}
}

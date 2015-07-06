package com.cjbooms.sisu.test.runner.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.cjbooms.sisu.test.UserCreator;
import com.cjbooms.sisu.test.WordGenerator;

import org.eclipse.sisu.EagerSingleton;
import org.ops4j.peaberry.ServiceUnavailableException;

@Named
@EagerSingleton
class RunOsgiTestApplication {

	private static final int TWO = 2;

	@Inject
	RunOsgiTestApplication(final WordGenerator wordGenerator, final UserCreator userCreator) {

		// test thread
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						System.out.println(
								"OSGI Service: { " + wordGenerator.createWords(TWO) + " }  |  " +
								"Guice CustomInjections: {" + userCreator.createUser().printUserDetails() + " }");
					} catch (final ServiceUnavailableException e) {
						System.err.println("Services not found!");
					}
					try {
						Thread.sleep(2000);
					} catch (final InterruptedException e) {
						// Do nothing
					}
				}
			}
		}).start();
	}
}
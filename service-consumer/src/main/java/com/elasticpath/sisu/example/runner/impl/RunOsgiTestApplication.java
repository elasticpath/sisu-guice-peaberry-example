package com.elasticpath.sisu.example.runner.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.elasticpath.sisu.example.apis.UserCreator;
import com.elasticpath.sisu.example.apis.WordGenerator;

import org.eclipse.sisu.EagerSingleton;
import org.eclipse.sisu.contrib.peaberry.annotations.ServiceImport;
import org.ops4j.peaberry.ServiceUnavailableException;

@Named
@EagerSingleton
class RunOsgiTestApplication {

	private static final int TWO = 2;

	@Inject
	RunOsgiTestApplication(
			@ServiceImport
			final WordGenerator wordGenerator,
			@ServiceImport
			final UserCreator userCreator) {

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

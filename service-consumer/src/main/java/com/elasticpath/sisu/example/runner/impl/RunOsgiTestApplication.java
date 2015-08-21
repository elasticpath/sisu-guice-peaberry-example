package com.elasticpath.sisu.example.runner.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.elasticpath.sisu.example.apis.UserGenerator;
import com.elasticpath.sisu.example.apis.WordGenerator;

import org.eclipse.sisu.EagerSingleton;
import org.eclipse.sisu.contrib.peaberry.annotations.ServiceImport;
import org.ops4j.peaberry.ServiceUnavailableException;

@Named
@EagerSingleton
class RunOsgiTestApplication {

	@Inject
	RunOsgiTestApplication(
			@ServiceImport
			final Iterable<WordGenerator> wordGenerators,
			@ServiceImport
			final UserGenerator userGenerator) {

		// test thread
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						System.out.println("Test Services");

						System.out.println("    Word Generators: ");
						for (WordGenerator wordGenerator : wordGenerators) {
							System.out.println("        " + wordGenerator.generateWords());
						}

						System.out.println("    User Generator: " + userGenerator.generateUser().printUserDetails());

						System.out.println();
					} catch (final ServiceUnavailableException e) {
						System.err.println("Services not found!");
					}
					try {
						Thread.sleep(5000);
					} catch (final InterruptedException e) {
						// Do nothing
					}
				}
			}
		}).start();
	}
}

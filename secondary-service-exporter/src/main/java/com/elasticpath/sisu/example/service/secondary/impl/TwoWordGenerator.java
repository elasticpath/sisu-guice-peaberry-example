package com.elasticpath.sisu.example.service.secondary.impl;

import java.util.Arrays;

import javax.inject.Named;
import javax.inject.Singleton;

import org.eclipse.sisu.contrib.peaberry.annotations.ServiceExport;

import com.elasticpath.sisu.example.apis.WordGenerator;


// Random word generator
@Named
@Singleton
@ServiceExport(interfaces = WordGenerator.class)
public class TwoWordGenerator implements WordGenerator {


	private static final String[] WORDS = {"Two", "Words"};

	@Override
	public String generateWords() {
		return Arrays.toString(WORDS);
	}
}

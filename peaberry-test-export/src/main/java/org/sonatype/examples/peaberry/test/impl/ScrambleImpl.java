package org.sonatype.examples.peaberry.test.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.sonatype.examples.peaberry.test.Scramble;

// basic text scrambler implementation
@Named
public class ScrambleImpl implements Scramble {


	private final ScrambleDelegate scrambleDelegate;

	@Inject
	public ScrambleImpl(
			ScrambleDelegate scrambleDelegate) {
		this.scrambleDelegate = scrambleDelegate;
	}

	@Override
	public String process( final String message )
    {

		return scrambleDelegate.process(message);
	}
}

package org.sonatype.examples.peaberry.test.impl;

import static org.ops4j.peaberry.Peaberry.service;

import javax.inject.Named;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

import org.ops4j.peaberry.Peaberry;
import org.sonatype.examples.peaberry.test.Scramble;

@Named
class ImportModule
    extends AbstractModule
{
    @Override
    protected void configure()
    {
        install( Peaberry.osgiModule() );
        bind( Scramble.class ).toProvider( service( Scramble.class ).single() );
		bindListener(Matchers.any(), new ScopeTypeListener());
		bindListener(Matchers.any(), new UserIdTypeListener());
    }
}

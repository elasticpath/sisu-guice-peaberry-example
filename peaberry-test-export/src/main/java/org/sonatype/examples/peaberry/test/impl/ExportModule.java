package org.sonatype.examples.peaberry.test.impl;

import static org.ops4j.peaberry.Peaberry.service;
import static org.ops4j.peaberry.util.TypeLiterals.export;

import javax.inject.Named;

import com.google.inject.AbstractModule;

import org.ops4j.peaberry.Peaberry;
import org.sonatype.examples.peaberry.test.Scramble;

@Named
class ExportModule
    extends AbstractModule
{


    @Override
    protected void configure()
    {
        install( Peaberry.osgiModule() );
        bind(export(Scramble.class)).toProvider(service(ScrambleImpl.class).export());
        // ^ append .asEagerSingleton(); if you want this service exported immediately
    }
}

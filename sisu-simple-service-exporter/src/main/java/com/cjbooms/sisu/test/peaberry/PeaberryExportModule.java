package com.cjbooms.sisu.test.peaberry;

import static org.ops4j.peaberry.Peaberry.service;
import static org.ops4j.peaberry.util.TypeLiterals.export;

import javax.inject.Named;

import com.cjbooms.sisu.test.apis.WordGenerator;
import com.cjbooms.sisu.test.impl.WordGeneratorImpl;
import com.google.inject.AbstractModule;

import org.ops4j.peaberry.Peaberry;

@Named
class PeaberryExportModule extends AbstractModule {

    @Override
    protected void configure() {
        install( Peaberry.osgiModule() );
        bind(export(WordGenerator.class)).toProvider(service(WordGeneratorImpl.class).export());
    }
}

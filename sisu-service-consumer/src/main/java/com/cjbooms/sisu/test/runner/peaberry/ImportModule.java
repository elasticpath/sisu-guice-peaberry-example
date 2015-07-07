package com.cjbooms.sisu.test.runner.peaberry;

import static org.ops4j.peaberry.Peaberry.service;

import javax.inject.Named;

import com.cjbooms.sisu.test.apis.UserCreator;
import com.cjbooms.sisu.test.apis.WordGenerator;
import com.google.inject.AbstractModule;

import org.ops4j.peaberry.Peaberry;

@Named
class ImportModule extends AbstractModule {
    @Override
    protected void configure()
    {
        install( Peaberry.osgiModule() );
        bind(WordGenerator.class).toProvider(service(WordGenerator.class).single());
        bind( UserCreator.class ).toProvider( service( UserCreator.class ).single() );
    }
}

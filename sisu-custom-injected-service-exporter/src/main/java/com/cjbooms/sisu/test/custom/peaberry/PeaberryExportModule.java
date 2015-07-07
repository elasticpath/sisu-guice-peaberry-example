package com.cjbooms.sisu.test.custom.peaberry;

import static org.ops4j.peaberry.Peaberry.service;
import static org.ops4j.peaberry.util.TypeLiterals.export;

import javax.inject.Named;
import javax.inject.Singleton;

import com.cjbooms.sisu.test.apis.UserCreator;
import com.cjbooms.sisu.test.custom.impl.UserCreatorImpl;
import com.google.inject.AbstractModule;

import org.ops4j.peaberry.Peaberry;

@Singleton
@Named
class PeaberryExportModule extends AbstractModule {

    @Override
    protected void configure() {
        install(Peaberry.osgiModule());
        bind(export(UserCreator.class)).toProvider(service(UserCreatorImpl.class).export());
    }
}

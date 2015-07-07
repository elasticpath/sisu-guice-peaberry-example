package com.cjbooms.sisu.test.custom.peaberry;

import static org.ops4j.peaberry.Peaberry.service;
import static org.ops4j.peaberry.util.TypeLiterals.export;

import javax.inject.Named;

import com.cjbooms.sisu.test.apis.ScopeTypeListener;
import com.cjbooms.sisu.test.apis.UserCreator;
import com.cjbooms.sisu.test.apis.UserIdTypeListener;
import com.cjbooms.sisu.test.custom.impl.UserCreatorImpl;
import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

import org.ops4j.peaberry.Peaberry;

@Named
class PeaberryExportModule extends AbstractModule {

    @Override
    protected void configure() {
        install(Peaberry.osgiModule());
		bindListener(Matchers.any(), new ScopeTypeListener());
		bindListener(Matchers.any(), new UserIdTypeListener());
        bind(export(UserCreator.class)).toProvider(service(UserCreatorImpl.class).export());
    }
}

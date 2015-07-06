/*******************************************************************************
 * Copyright (c) 2010, 2013 Sonatype, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stuart McCulloch (Sonatype, Inc.) - initial API and implementation
 *******************************************************************************/
package com.cjbooms.sisu.test.custom.peaberry;

import javax.inject.Inject;
import javax.inject.Named;

import com.cjbooms.sisu.test.UserCreator;

import org.eclipse.sisu.EagerSingleton;
import org.ops4j.peaberry.Export;

@Named
@EagerSingleton
class PeaberryServiceExport {

	@Inject
	Export<UserCreator> userCreator;
}

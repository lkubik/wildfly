/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2014, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.wildfly.clustering.server.group;

import java.util.Collection;
import java.util.Collections;

import org.jboss.msc.service.ServiceController;
import org.jboss.msc.service.ServiceName;
import org.jboss.msc.service.ServiceTarget;
import org.wildfly.clustering.spi.CacheServiceNames;
import org.wildfly.clustering.spi.ClusteredCacheServiceInstaller;
import org.wildfly.clustering.spi.LocalCacheServiceInstaller;

/**
 * @author Paul Ferraro
 */
public class CacheNodeFactoryServiceInstaller implements LocalCacheServiceInstaller, ClusteredCacheServiceInstaller {

    @Override
    public Collection<ServiceName> getServiceNames(String container, String cache) {
        return Collections.singleton(CacheServiceNames.NODE_FACTORY.getServiceName(container, cache));
    }

    @Override
    public void install(ServiceTarget target, String container, String cache) {
        ServiceName name = CacheServiceNames.NODE_FACTORY.getServiceName(container, cache);
        CacheNodeFactoryService.build(target, name, container, cache).setInitialMode(ServiceController.Mode.ON_DEMAND).install();
    }
}

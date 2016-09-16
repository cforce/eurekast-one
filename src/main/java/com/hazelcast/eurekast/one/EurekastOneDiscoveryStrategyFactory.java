/*
 * Copyright (c) 2008-2015, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.eurekast.one;

import com.hazelcast.config.properties.PropertyDefinition;
import com.hazelcast.logging.ILogger;
import com.hazelcast.spi.discovery.DiscoveryNode;
import com.hazelcast.spi.discovery.DiscoveryStrategy;
import com.hazelcast.spi.discovery.DiscoveryStrategyFactory;
import com.netflix.appinfo.EurekaInstanceConfig;
import com.netflix.discovery.EurekaClient;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class EurekastOneDiscoveryStrategyFactory
        implements DiscoveryStrategyFactory {

    private static final Collection<PropertyDefinition> PROPERTY_DEFINITIONS = Collections
            .singleton(EurekastOneProperties.SELF_REGISTRATION);
    
    private static EurekaInstanceConfig eurekaInstanceConfig;

    private static EurekaClient eurekaClient;

    public Class<? extends DiscoveryStrategy> getDiscoveryStrategyType() {
        return EurekastOneDiscoveryStrategy.class;
    }

    public DiscoveryStrategy newDiscoveryStrategy(DiscoveryNode discoveryNode, ILogger logger,
                                                  Map<String, Comparable> properties) {

        return new EurekastOneDiscoveryStrategy(eurekaInstanceConfig, eurekaClient,
                discoveryNode, logger, properties);
    }
    
    public Collection<PropertyDefinition> getConfigurationProperties() {
        return PROPERTY_DEFINITIONS;
    }

    public static void setEurekaInstanceConfig(EurekaInstanceConfig eurekaInstanceConfig) {
        EurekastOneDiscoveryStrategyFactory.eurekaInstanceConfig = eurekaInstanceConfig;
    }

    public static void setEurekaClient(EurekaClient eurekaClient) {
        EurekastOneDiscoveryStrategyFactory.eurekaClient = eurekaClient;
    }
}

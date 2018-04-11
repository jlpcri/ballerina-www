/*
 * Copyright (c) 2018, WSO2 Inc. (http://wso2.com) All Rights Reserved.
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
package org.ballerinalang.platform.playground.api;

import org.ballerinalang.platform.playground.api.core.cache.CacheStorage;
import org.ballerinalang.platform.playground.api.core.cache.adaptor.CacheStorageAdaptor;
import org.ballerinalang.platform.playground.api.core.cache.adaptor.InMemoryCacheStorageAdaptor;
import org.wso2.msf4j.MicroservicesRunner;

/**
 * Entry point for micro services server
 */
public class ServiceRunner {

    private static CacheStorageAdaptor inMemoryCache;

    public static void main(String[] args) {
        if (System.getenv(CacheStorage.USE_IN_MEMORY_CACHE) !=  null) {
            inMemoryCache = new InMemoryCacheStorageAdaptor();
        }
        MicroservicesRunner microservicesRunner = new MicroservicesRunner();
        microservicesRunner.deploy(new ParserService());
        microservicesRunner.deployWebSocketEndpoint(new RunService());
        microservicesRunner.start();
    }

    public static CacheStorageAdaptor getInMemoryCache() {
        return inMemoryCache;
    }
}

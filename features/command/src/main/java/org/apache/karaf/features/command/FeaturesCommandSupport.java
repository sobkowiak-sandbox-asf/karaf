/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.karaf.features.command;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.karaf.features.FeaturesService;
import org.apache.karaf.shell.console.commands.ComponentAction;

@Component(name = FeaturesCommandSupport.ID, componentAbstract = true)
public abstract class FeaturesCommandSupport extends ComponentAction {

    public static final String ID = "org.apache.karaf.features.command.base";

    @Reference
    private FeaturesService featuresService;

    public Object doExecute() throws Exception {
            doExecute(featuresService);
        return null;
    }

    protected abstract void doExecute(FeaturesService featuresService) throws Exception;

}

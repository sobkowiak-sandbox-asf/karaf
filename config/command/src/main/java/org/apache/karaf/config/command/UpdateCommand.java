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
package org.apache.karaf.config.command;

import java.util.Dictionary;

import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.commands.Option;

@Command(scope = "config", name = "update", description = "Saves and propagates changes from the configuration being edited.")
public class UpdateCommand extends ConfigCommandSupport {

    @Option(name = "-b", aliases = { "--bypass-storage" }, multiValued = false, required = false, description = "Do not store the configuration in a properties file, but feed it directly to ConfigAdmin")
    protected boolean bypassStorage;

    @SuppressWarnings("rawtypes")
    protected Object doExecute() throws Exception {
        Dictionary props = getEditedProps();
        if (props == null) {
            System.err.println("No configuration is being edited--run the edit command first");
            return null;
        }

        String pid = (String) this.session.get(PROPERTY_CONFIG_PID);
        this.configRepository.update(pid, props, bypassStorage);
        this.session.put(PROPERTY_CONFIG_PID, null);
        this.session.put(PROPERTY_CONFIG_PROPS, null);
        return null;
    }
}
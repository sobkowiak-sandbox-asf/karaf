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
package org.apache.karaf.tooling.exam.container.internal.runner;

import java.io.File;

/**
 * Abstracts the runner to be able to add different runners easier.
 */
public interface Runner {

    /**
     * Starts the environment in the specific environment.
     */
    public abstract void
    exec(final String[] environment, final File karafBase, final String javaHome, final String[] javaOpts,
         final String[] javaEndorsedDirs,
         final String[] javaExtDirs, final String karafHome, final String karafData, final String[] karafOpts,
         final String[] opts, final String[] classPath, final String main, final String options);

    /**
     * Shutsdown the runner again.
     */
    public abstract void shutdown();

}

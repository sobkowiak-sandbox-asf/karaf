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
package org.apache.karaf.shell.osgi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import org.apache.felix.gogo.commands.Command;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.karaf.shell.console.CompletableFunction;
import org.apache.karaf.shell.console.commands.ComponentAction;
import org.apache.karaf.util.StringEscapeUtils;
import org.osgi.framework.Bundle;

@Command(scope = Info.SCOPE_VALUE, name = Info.FUNCTION_VALUE, description = Info.DESCRIPTION)
@Component(name = Info.ID, description = Info.DESCRIPTION)
@Service(CompletableFunction.class)
@Properties({
        @Property(name = ComponentAction.SCOPE, value = Info.SCOPE_VALUE),
        @Property(name = ComponentAction.FUNCTION, value = Info.FUNCTION_VALUE)
})
public class Info extends BundlesCommandOptional {

    public static final String ID = "org.apache.karaf.shell.osgi.info";
    public static final String SCOPE_VALUE = "osgi";
    public static final String FUNCTION_VALUE =  "info";
    public static final String DESCRIPTION = "Displays detailed information of a given bundle.";

    protected void doExecute(List<Bundle> bundles) throws Exception {
        if (bundles == null) {
            Bundle[] allBundles = getBundleContext().getBundles();
            for (int i = 0; i < allBundles.length; i++) {
                printInfo(allBundles[i]);
            }
        } else {
            for (Bundle bundle : bundles) {
                printInfo(bundle);
            }
        }
    }

    /**
     * <p>
     * Get the OSGI-INF/bundle.info entry from the bundle and display it.
     * </p>
     *
     * @param bundle the bundle.
     */
    protected void printInfo(Bundle bundle) {
        String title = Util.getBundleName(bundle);
        System.out.println("\n" + title);
        System.out.println(Util.getUnderlineString(title));
        URL bundleInfo = bundle.getEntry("OSGI-INF/bundle.info");
        if (bundleInfo != null) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(bundleInfo.openStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(StringEscapeUtils.unescapeJava(line));
                }
                reader.close();
            } catch (Exception e) {
                // ignore
            }
        }
    }

}

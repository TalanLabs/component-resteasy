package com.synaptix.componentresteasy;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.module.SimpleModule;

public class ComponentBeanModule extends SimpleModule {

    public ComponentBeanModule() {
        super("ComponentBeanModule", new Version(1, 0, 0, null));

        this.addSerializer(new ComponentSerializer());
        this.setValueInstantiators(new ComponentValueInstantiators());
    }
}

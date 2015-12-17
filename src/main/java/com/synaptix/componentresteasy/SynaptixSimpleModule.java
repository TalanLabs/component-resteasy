package com.synaptix.componentresteasy;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.module.SimpleModule;

public class SynaptixSimpleModule extends SimpleModule {

    public SynaptixSimpleModule() {
        super("SynaptixComponentModule", new Version(1, 0, 0, null));

        this.addSerializer(new ComponentSerializer());
        this.setDeserializers(new ComponentDeserializers());
    }
}

package com.synaptix.componentresteasy;

import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class SynaptixComponentJacksonConfig implements ContextResolver<ObjectMapper> {

    private ObjectMapper objectMapper;

    public SynaptixComponentJacksonConfig() {
        super();

        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new SynaptixComponentModule());
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return this.objectMapper;
    }

}

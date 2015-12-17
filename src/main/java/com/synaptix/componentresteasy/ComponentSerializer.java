package com.synaptix.componentresteasy;

import com.synaptix.component.IComponent;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.std.SerializerBase;

import java.io.IOException;

public class ComponentSerializer extends SerializerBase<IComponent> {

    public ComponentSerializer() {
        super(IComponent.class);
    }

    @Override
    public void serialize(IComponent value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
        jgen.writeObject(value.straightGetProperties());
    }
}

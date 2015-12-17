package com.synaptix.componentresteasy;

import com.synaptix.component.IComponent;
import com.synaptix.component.factory.ComponentDescriptor;
import com.synaptix.component.factory.ComponentFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.deser.std.StdDeserializer;
import org.codehaus.jackson.type.JavaType;

import java.io.IOException;

public class ComponentDeserializer<E extends IComponent> extends StdDeserializer<E> {

    private ComponentDescriptor componentDescriptor;

    public ComponentDeserializer(Class<E> componentClass) {
        super(componentClass);

        this.componentDescriptor = ComponentFactory.getInstance().getDescriptor(componentClass);
    }

    @Override
    public E deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        DeserializationConfig config = ctxt.getConfig();
        DeserializerProvider deserializerProvider = ctxt.getDeserializerProvider();

        E component = ComponentFactory.getInstance().createInstance((Class<E>) _valueClass);

        JsonToken t = jp.getCurrentToken();
        if (t == JsonToken.START_OBJECT) {
            t = jp.nextToken();
        }

        for (; t == JsonToken.FIELD_NAME; t = jp.nextToken()) {
            // Must point to field name
            String fieldName = jp.getCurrentName();
            ComponentDescriptor.PropertyDescriptor pd = componentDescriptor.getPropertyDescriptor(fieldName);
            if (pd == null) {
                jp.skipChildren();
                continue;
            }

            JavaType typeDeser = ctxt.constructType(pd.getPropertyClass());
            JsonDeserializer<Object> valueDes = ctxt.getDeserializerProvider().findValueDeserializer(config, typeDeser, null);

            t = jp.nextToken();

            Object value;
            if (t == JsonToken.VALUE_NULL) {
                value = null;
            } else {
                value = valueDes.deserialize(jp, ctxt);
            }
            component.straightSetProperty(fieldName, value);
        }

        return component;
    }
}

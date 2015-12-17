package com.synaptix.componentresteasy;

import com.synaptix.component.IComponent;
import org.codehaus.jackson.map.*;
import org.codehaus.jackson.map.module.SimpleDeserializers;
import org.codehaus.jackson.type.JavaType;

import java.util.concurrent.ConcurrentHashMap;

public class ComponentDeserializers extends SimpleDeserializers {

    private ConcurrentHashMap<Class<?>, JsonDeserializer<?>> map = new ConcurrentHashMap<>();

    @Override
    public JsonDeserializer<?> findBeanDeserializer(JavaType type, DeserializationConfig config, DeserializerProvider provider, BeanDescription beanDesc, BeanProperty property)
            throws JsonMappingException {
        if (type.isInterface() && IComponent.class.isAssignableFrom(type.getRawClass())) {
            Class<? extends IComponent> clazz = (Class<? extends IComponent>) type.getRawClass();
            JsonDeserializer<?> dese;
            synchronized (clazz) {
                dese = map.get(clazz);
                if (dese == null) {
                    dese = new ComponentDeserializer(clazz);
                    map.put(clazz, dese);
                }
            }
            return dese;
        }
        return super.findBeanDeserializer(type, config, provider, beanDesc, property);
    }
}
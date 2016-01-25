package com.synaptix.componentresteasy;

import com.synaptix.component.IComponent;
import com.synaptix.component.factory.ComponentFactory;
import org.codehaus.jackson.map.BeanDescription;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.deser.ValueInstantiator;
import org.codehaus.jackson.map.module.SimpleValueInstantiators;

public class ComponentValueInstantiators extends SimpleValueInstantiators {

    @Override
    public ValueInstantiator findValueInstantiator(DeserializationConfig config, BeanDescription beanDesc, ValueInstantiator defaultInstantiator) {
        if (ComponentFactory.isClass(beanDesc.getBeanClass())) {
            defaultInstantiator = new ComponentValueInstantiator((Class<? extends IComponent>) beanDesc.getBeanClass(), defaultInstantiator);
        }
        return super.findValueInstantiator(config, beanDesc, defaultInstantiator);
    }
}

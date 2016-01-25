package com.synaptix.componentresteasy;

import com.synaptix.component.IComponent;
import com.synaptix.component.factory.ComponentFactory;
import org.codehaus.jackson.map.deser.ValueInstantiator;

public class ComponentValueInstantiator extends ValueInstantiator {

    private final Class<? extends IComponent> componentClass;

    private final ValueInstantiator defaultInstantiator;

    public ComponentValueInstantiator(Class<? extends IComponent> componentClass, ValueInstantiator defaultInstantiator) {
        super();

        this.componentClass = componentClass;
        this.defaultInstantiator = defaultInstantiator;
    }

    @Override
    public String getValueTypeDesc() {
        return defaultInstantiator.getValueTypeDesc();
    }

    @Override
    public boolean canCreateUsingDefault() {
        return true;
    }

    @Override
    public IComponent createUsingDefault() {
        return ComponentFactory.getInstance().createInstance(componentClass);
    }
}

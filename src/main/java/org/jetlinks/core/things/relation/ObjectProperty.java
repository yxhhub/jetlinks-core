package org.jetlinks.core.things.relation;

import org.jetlinks.core.config.ConfigKey;

import java.util.Objects;

public interface ObjectProperty {

    ConfigKey<String> name = ConfigKey.of("name");
    ConfigKey<String> description = ConfigKey.of("description");


    String getProperty();

    Object getValue();


    static ObjectProperty of(String property, Object value) {
        return SimpleObjectProperty
                .of(Objects.requireNonNull(property),
                    Objects.requireNonNull(value));
    }
}

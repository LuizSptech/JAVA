package school.sptech.util;

import java.util.HashMap;
import java.util.Map;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class ObjectFieldBuilder<T> {

    private final ObjectFieldMapping<T> mapping;
    private final Map<String, Object> fieldValues;
    private final Class<T> clazz;

    public ObjectFieldBuilder(Class<T> clazz) {
        this.clazz = clazz;
        this.fieldValues = new HashMap<>();
        this.mapping = new ObjectFieldMapping<>(clazz);
    }

    public ObjectFieldBuilder<T> with(String fieldName, Object value) {
        fieldValues.put(fieldName, value);
        return this;
    }

    public T build() throws ReflectiveOperationException {
        Objenesis objenesis = new ObjenesisStd();
        T obj = objenesis.newInstance(clazz);

        for (Map.Entry<String, Object> entry : fieldValues.entrySet()) {
            mapping.get(entry.getKey()).set(obj, entry.getValue());
        }

        return obj;
    }
}

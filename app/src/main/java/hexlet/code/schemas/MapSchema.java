package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema<Map<String, String>> {
    private Map<String, BaseSchema<String>> shapeSchemas;

    public MapSchema required() {
        addCheck("required", Objects::nonNull);
        return this;
    }

    public MapSchema sizeof(Integer length) {
        addCheck("sizeof", value -> value == null || value.size() == length);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        this.shapeSchemas = schemas;
        addCheck("shape", value -> {
            if (value == null || shapeSchemas.isEmpty()) {
                return true;
            }
            for (var entry : shapeSchemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<String> schema = entry.getValue();
                if (!value.containsKey(key) || !schema.isValid(value.get(key))) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }
}

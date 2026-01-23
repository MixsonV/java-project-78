package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema<Map<String, String>> {
    private boolean isRequired = false;
    private Integer mapSize = -1;
    private Map<String, BaseSchema<String>> mapSchemas = new HashMap<>();

    @Override
    public boolean isValid(Map<String, String> value) {
        if (isRequired && value == null) {
            return false;
        } else if (!isRequired && value == null) {
            return true;
        } else {
            if (mapSize != -1 && value.size() != mapSize) {
                return false;
            }
            for (Map.Entry<String, BaseSchema<String>> entry : mapSchemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<String> schema = entry.getValue();

                boolean isValidSchema = value.containsKey(key) && schema.isValid(value.get(key));
                if (!isValidSchema) {
                    return false;
                }
            }
            return true;
        }
    }

    public MapSchema required() {
        this.isRequired = true;
        return this;
    }

    public MapSchema sizeof(Integer length) {
        this.mapSize = length;
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        this.mapSchemas = schemas;
        return this;
    }
}

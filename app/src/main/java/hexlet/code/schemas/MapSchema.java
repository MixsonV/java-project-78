package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<String, String>> {
    private boolean isRequired = false;
    private Integer mapSize = -1;

    @Override
    public boolean isValid(Map<String, String> value) {
        if (isRequired && value == null) {
            return false;
        } else if (!isRequired && value == null) {
            return true;
        } else if (mapSize != -1) {
            return value.size() == mapSize;
        } else {
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
}

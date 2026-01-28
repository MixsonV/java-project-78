package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        addCheck("required", Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive", value -> value == null || value > 0);
        return this;
    }

    public NumberSchema range(Integer min, Integer max) {
        addCheck("range", value -> value == null || (value >= min && value <= max));
        return this;
    }

}

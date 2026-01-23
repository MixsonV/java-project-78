package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {
    private boolean isRequired = false;
    private Integer minRange = Integer.MIN_VALUE;
    private Integer maxRange = Integer.MAX_VALUE;
    private boolean isPositive = false;

    @Override
    public boolean isValid(Integer value) {
        if (isRequired && value == null) {
            return false;
        } else if (!isRequired && value == null) {
            return true;
        } else if (isPositive) {
            return value > 0 && value >= minRange && value <= maxRange;
        } else {
            return value >= minRange && value <= maxRange;
        }
    }

    public NumberSchema required() {
        this.isRequired = true;
        return this;
    }

    public NumberSchema positive() {
        this.isPositive = true;
        return this;
    }

    public NumberSchema range(Integer min, Integer max) {
        this.minRange = min;
        this.maxRange = max;
        return this;
    }

}

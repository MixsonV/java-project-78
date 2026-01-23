package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    private boolean isRequired = false;
    private int minLength = 0;
    private String text = "";

    @Override
    public boolean isValid(String value) {
        String formattedValue = (value == null) ? "" : value;

        boolean result = true;

        if (isRequired && formattedValue.isEmpty()
                || formattedValue.length() < minLength
                || !formattedValue.contains(text)) {
            result = false;
        }

        return result;
    }

    public StringSchema required() {
        this.isRequired = true;
        return this;
    }

    public StringSchema minLength(int length) {
        minLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        text = substring;
        return this;
    }

}

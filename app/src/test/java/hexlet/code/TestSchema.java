package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSchema {
    private static final int MAGIC_NUMBER_1 = 4;
    private static final int MAGIC_NUMBER_2 = 10;

    @Test
    void testNullEmpty() {
        StringSchema schema = new StringSchema();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
    }

    @Test
    void testLength() {
        StringSchema schema = new StringSchema();
        assertTrue(schema.minLength(MAGIC_NUMBER_1).isValid("Hexlet"));
        assertFalse(schema.minLength(MAGIC_NUMBER_2).isValid("Hexlet"));
    }

    @Test
    void testContains() {
        StringSchema schema = new StringSchema();
        assertTrue(schema.contains("Hello").isValid("Hello World!"));
        assertFalse(schema.contains("test").isValid("Hello World!"));
    }
}

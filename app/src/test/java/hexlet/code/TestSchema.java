package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSchema {
    private static final int MAGIC_NUMBER_1 = 4;
    private static final int MAGIC_NUMBER_2 = 10;
    private static final int MAGIC_NUMBER_3 = -10;
    private static final int MAGIC_NUMBER_4 = -100;
    private static final int MAGIC_NUMBER_5 = 100;

    @Test
    void testStringSchemaNullEmpty() {
        StringSchema schema = new StringSchema();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
    }

    @Test
    void testStringSchemaLength() {
        StringSchema schema = new StringSchema();
        assertTrue(schema.minLength(MAGIC_NUMBER_1).isValid("Hexlet"));
        assertFalse(schema.minLength(MAGIC_NUMBER_2).isValid("Hexlet"));
    }

    @Test
    void testStringSchemaContains() {
        StringSchema schema = new StringSchema();
        assertTrue(schema.contains("Hello").isValid("Hello World!"));
        assertFalse(schema.contains("test").isValid("Hello World!"));
    }

    @Test
    void testNumberSchemaNullEmpty() {
        NumberSchema schema = new NumberSchema();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(MAGIC_NUMBER_1));
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(MAGIC_NUMBER_1));
    }

    @Test
    void testNumberSchemaPositive() {
        NumberSchema schema = new NumberSchema();
        assertTrue(schema.isValid(MAGIC_NUMBER_3));
        assertTrue(schema.isValid(0));
        assertTrue(schema.isValid(MAGIC_NUMBER_2));
        schema.positive();
        assertFalse(schema.isValid(MAGIC_NUMBER_3));
        assertFalse(schema.isValid(0));
        assertTrue(schema.isValid(MAGIC_NUMBER_2));
    }

    @Test
    void testNumberSchemaRange() {
        NumberSchema schema = new NumberSchema();
        assertTrue(schema.isValid(MAGIC_NUMBER_2));
        assertTrue(schema.range(MAGIC_NUMBER_3, MAGIC_NUMBER_2).isValid(MAGIC_NUMBER_3));
        assertTrue(schema.range(MAGIC_NUMBER_3, MAGIC_NUMBER_2).isValid(MAGIC_NUMBER_1));
        assertTrue(schema.range(MAGIC_NUMBER_3, MAGIC_NUMBER_2).isValid(MAGIC_NUMBER_2));
        assertFalse(schema.range(MAGIC_NUMBER_3, MAGIC_NUMBER_2).isValid(MAGIC_NUMBER_4));
        assertFalse(schema.range(MAGIC_NUMBER_3, MAGIC_NUMBER_2).isValid(MAGIC_NUMBER_5));
    }
}

package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestSchema {
    private static final Integer MAGIC_NUMBER_NEGATIVE_100 = -100;
    private static final Integer MAGIC_NUMBER_NEGATIVE_10 = -10;
    private static final Integer MAGIC_NUMBER_2 = 2;
    private static final Integer MAGIC_NUMBER_4 = 4;
    private static final Integer MAGIC_NUMBER_10 = 10;
    private static final Integer MAGIC_NUMBER_100 = 100;

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
        assertTrue(schema.minLength(MAGIC_NUMBER_4).isValid("Hexlet"));
        assertFalse(schema.minLength(MAGIC_NUMBER_10).isValid("Hexlet"));
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
        assertTrue(schema.isValid(MAGIC_NUMBER_4));
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(MAGIC_NUMBER_4));
    }

    @Test
    void testNumberSchemaPositive() {
        NumberSchema schema = new NumberSchema();
        assertTrue(schema.isValid(MAGIC_NUMBER_NEGATIVE_10));
        assertTrue(schema.isValid(0));
        assertTrue(schema.isValid(MAGIC_NUMBER_10));
        schema.positive();
        assertFalse(schema.isValid(MAGIC_NUMBER_NEGATIVE_10));
        assertFalse(schema.isValid(0));
        assertTrue(schema.isValid(MAGIC_NUMBER_10));
    }

    @Test
    void testNumberSchemaRange() {
        NumberSchema schema = new NumberSchema();
        assertTrue(schema.isValid(MAGIC_NUMBER_10));
        assertTrue(schema.range(MAGIC_NUMBER_NEGATIVE_10, MAGIC_NUMBER_10).isValid(MAGIC_NUMBER_NEGATIVE_10));
        assertTrue(schema.range(MAGIC_NUMBER_NEGATIVE_10, MAGIC_NUMBER_10).isValid(MAGIC_NUMBER_4));
        assertTrue(schema.range(MAGIC_NUMBER_NEGATIVE_10, MAGIC_NUMBER_10).isValid(MAGIC_NUMBER_10));
        assertFalse(schema.range(MAGIC_NUMBER_NEGATIVE_10, MAGIC_NUMBER_10).isValid(MAGIC_NUMBER_NEGATIVE_100));
        assertFalse(schema.range(MAGIC_NUMBER_NEGATIVE_10, MAGIC_NUMBER_10).isValid(MAGIC_NUMBER_100));
    }

    @Test
    void testMapSchemaNullEmpty() {
        MapSchema schema = new MapSchema();
        Map<String, String> testedMap = new HashMap<>();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(testedMap));
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(testedMap));
    }

    @Test
    void testMapSchemaSizeof() {
        MapSchema schema = new MapSchema();
        Map<String, String> testedMap = Map.of(
                "key1", "value1",
                "key2", "value2"
        );
        assertTrue(schema.isValid(testedMap));
        assertTrue(schema.sizeof(MAGIC_NUMBER_2).isValid(testedMap));
        assertFalse(schema.sizeof(MAGIC_NUMBER_4).isValid(testedMap));
    }

    @Test
    void testMapSchemaShape() {
        MapSchema schemaMap = new MapSchema();
        StringSchema schemaString = new StringSchema();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", schemaString.required());
        schemas.put("lastName", schemaString.required().minLength(MAGIC_NUMBER_2));
        schemaMap.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schemaMap.isValid(human1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(schemaMap.isValid(human2));

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(schemaMap.isValid(human3));
    }

}

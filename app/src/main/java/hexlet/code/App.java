package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

import java.util.HashMap;

public class App {
    // для примера
    private static final int STRING_MAGIC_NUMBER_1 = 10;
    private static final int STRING_MAGIC_NUMBER_2 = 4;
    private static final int NUMBER_MAGIC_NUMBER_1 = 5;
    private static final int NUMBER_MAGIC_NUMBER_2 = 10;
    private static final int NUMBER_MAGIC_NUMBER_3 = -10;
    private static final int NUMBER_MAGIC_NUMBER_4 = 4;
    private static final int NUMBER_MAGIC_NUMBER_5 = 11;

    public static void main(String[] args) {
        // пример StringSchema
        System.out.println("\nStringSchema:");
        Validator v1 = new Validator();
        StringSchema string = v1.string();

        System.out.println(string.isValid("")); // true
        System.out.println(string.isValid(null)); // true

        System.out.println(string.required());

        System.out.println(string.isValid(null)); // false
        System.out.println(string.isValid("")); // false
        System.out.println(string.isValid("what does the fox say")); // true
        System.out.println(string.isValid("hexlet")); // true

        System.out.println(string.contains("wh").isValid("what does the fox say")); // true
        System.out.println(string.contains("what").isValid("what does the fox say")); // true
        System.out.println(string.contains("whatthe").isValid("what does the fox say")); // false

        System.out.println(string.isValid("what does the fox say")); // false

        StringSchema string1 = v1.string();
        System.out.println(string1.minLength(STRING_MAGIC_NUMBER_1).minLength(STRING_MAGIC_NUMBER_2)
                .isValid("Hexlet")); // true

        // пример NumberSchema
        System.out.println("\nNumberSchema:");

        Validator v2 = new Validator();
        NumberSchema number = v2.number();

        System.out.println(number.isValid(NUMBER_MAGIC_NUMBER_1)); // true

        System.out.println(number.isValid(null)); // true
        System.out.println(number.positive().isValid(null)); // true

        System.out.println(number.required());

        System.out.println(number.isValid(null)); // false
        System.out.println(number.isValid(NUMBER_MAGIC_NUMBER_2)); // true

        System.out.println(number.isValid(NUMBER_MAGIC_NUMBER_3)); // false
        System.out.println(number.isValid(0)); // false

        System.out.println(number.range(NUMBER_MAGIC_NUMBER_1, NUMBER_MAGIC_NUMBER_2));

        System.out.println(number.isValid(NUMBER_MAGIC_NUMBER_1)); // true
        System.out.println(number.isValid(NUMBER_MAGIC_NUMBER_2)); // true
        System.out.println(number.isValid(NUMBER_MAGIC_NUMBER_4)); // false
        System.out.println(number.isValid(NUMBER_MAGIC_NUMBER_5)); // false

        // пример MapSchema
        System.out.println("\nMapSchema:");
        var v3 = new Validator();

        var map = v3.map();

        System.out.println(map.isValid(null)); // true

        System.out.println(map.required());

        System.out.println(map.isValid(null)); // false
        System.out.println(map.isValid(new HashMap<>())); // true
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        System.out.println(map.isValid(data)); // true

        System.out.println(map.sizeof(2));

        System.out.println(map.isValid(data));  // false
        data.put("key2", "value2");
        System.out.println(map.isValid(data)); // true
    }

}

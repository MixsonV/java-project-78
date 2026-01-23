package hexlet.code;

import hexlet.code.schemas.StringSchema;

public class App {
    // для примера
    private static final int EXAMPLE_LENGTH_1 = 10;
    private static final int EXAMPLE_LENGTH_2 = 4;

    public static void main(String[] args) {
        // пример
        Validator v = new Validator();
        StringSchema schema = v.string();

        System.out.println(schema.isValid("")); // true
        System.out.println(schema.isValid(null)); // true

        System.out.println(schema.required());

        System.out.println(schema.isValid(null)); // false
        System.out.println(schema.isValid("")); // false
        System.out.println(schema.isValid("what does the fox say")); // true
        System.out.println(schema.isValid("hexlet")); // true

        System.out.println(schema.contains("wh").isValid("what does the fox say")); // true
        System.out.println(schema.contains("what").isValid("what does the fox say")); // true
        System.out.println(schema.contains("whatthe").isValid("what does the fox say")); // false

        System.out.println(schema.isValid("what does the fox say")); // false

        StringSchema schema1 = v.string();
        System.out.println(schema1.minLength(EXAMPLE_LENGTH_1).minLength(EXAMPLE_LENGTH_2).isValid("Hexlet")); // true
    }
}

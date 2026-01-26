### Hexlet tests and linter status:
[![Actions Status](https://github.com/MixsonV/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/MixsonV/java-project-78/actions)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=MixsonV_java-project-78&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=MixsonV_java-project-78)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=MixsonV_java-project-78&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=MixsonV_java-project-78)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=MixsonV_java-project-78&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=MixsonV_java-project-78)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=MixsonV_java-project-78&metric=coverage)](https://sonarcloud.io/summary/new_code?id=MixsonV_java-project-78)

# Знакомьтесь - это валидатор данных!

## Предназначен для проверки валидности переданных данных.

## В состав валидаторов входят:
* `StringSchema` - валидатор строк, типа `String`
* `NumberSchema` - валидатор целых чисел, типа `Integer`
* `MapSchema` - валидатор мап и их вложений, типа `Map<String, String>`

## Методы валидаторов:
### StringSchema
* `required()` - добавляет в схему ограничение, которое не позволяет использовать null или пустую строку в качестве значения.
* `minLength(Integer)` - добавляет в схему ограничение минимальной длины для строки. Строка должна быть равна или длиннее указанного числа.
* `contains(String)` - добавляет в схему ограничение по содержимому строки. Строка должна содержать определённую подстроку.
* `isValid(String)` - проверка валидности строки.

### NumberSchema
* `required()` - добавляет в схему ограничение, которое не позволяет использовать null в качестве значения.
* `positive()` - добавляет ограничение на знак числа. Число должно быть положительным.
* `range(Integer, Integer)` - добавляет допустимый диапазон, в который должно попадать значение числа включая границы.
* `isValid(Integer)` - проверка валидности числа.

### MapSchema
* `required()` - добавляет в схему ограничение, которое не позволяет использовать null в качестве значения.
* `sizeof(Integer)` - добавляет ограничение на размер мапы. Количество пар ключ-значений в объекте Map должно быть равно заданному.
* `shape(Map<String, BaseSchema<String>>)` - добавляет определение свойств объекта Map. Мапа схем для валидации значений.
* `isValid(Map<String, String>)` - проверка валидности мапы.

## Пример использования:
```java
    Validator validator = new Validator();

    // валидатор строк
    StringSchema stringSchema = validator.string();
    stringSchema.required().minLength(3).contains("wh").isValid("what does the fox say"); // true
    stringSchema.contains("whatthe").isValid("what does the fox say"); // false
    
    // валидатор чисел
    NumberSchema numberSchema = validator.number();
    numberSchema.required().range(-10, 100).isValid(-1); // true
    numberSchema.required().positive().range(-11, 100).isValid(0); // false
    
    // валидатор мап
    MapSchema mapSchema = validator.map();
    Map<String, BaseSchema<String>> schemas = new HashMap<>();
    schemas.put("firstName", v4.string().required());
    schemas.put("lastName", v4.string().required().minLength(2));
    mapSchema.shape(schemas);

    Map<String, String> human1 = new HashMap<>();
    human1.put("firstName", "John");
    human1.put("lastName", "Smith");
    mapSchema.isValid(human1); // true

    Map<String, String> human2 = new HashMap<>();
    human2.put("firstName", "John");
    human2.put("lastName", null);
    mapSchema.isValid(human2); // false
    
```
package helpers;

import annotations.DisplayEnumValue;

import java.lang.reflect.Field;

public class EnumExtension {
    public static String getStringValue(Enum<?> enumValue) {
        try {
            Field field = enumValue.getClass().getField(enumValue.name());
            DisplayEnumValue annotation = field.getAnnotation(DisplayEnumValue.class);
            return annotation != null ? annotation.value() : enumValue.name();
        } catch (NoSuchFieldException e) {
            return enumValue.name();
        }
    }
}

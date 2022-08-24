package com.qiqi.rs.admin.core.utils;

public class FieldCheckUtil {
    public static boolean isIdValid(Long id) {
        return id != null && id > 0;
    }

    public static boolean isLessThanOrEqual0(Long id) {
        return id != null && id <= 0;
    }

    public static boolean isIdEqual(Long id1, Long id2) {
        if (id1 == null || id2 == null) {
            return false;
        }
        return id1.longValue() == id2.longValue();
    }

    public static boolean isNullOr0(Double value) {
        return value == null || value == 0;
    }

    public static boolean isNullOrLessOr0(Double value) {
        return value == null || value <= 0;
    }

    public static boolean isNullOrGreatOr0(Integer value) {
        return value == null || value >= 0;
    }

    public static boolean isNullOrGreatOr0(Double value) {
        return value == null || value >= 0;
    }

    public static boolean isNullOrGreatOr0(Long value) {
        return value == null || value >= 0;
    }

    public static boolean isNullOr0(Long value) {
        return value == null || value == 0;
    }

    public static boolean isNullOrLessOr0(Long value) {
        return value == null || value <= 0;
    }

    public static boolean isNullOrLessOr0(Integer value) {
        return value == null || value <= 0;
    }

    public static boolean isLessThan0(Double value) {
        return value != null && value < 0;
    }

    public static boolean isLessThan0(Integer value) {
        return value != null && value < 0;
    }

    public static boolean isGreatThan0(Double value) {
        return value != null && value > 0;
    }

    public static boolean isGreatThan0(Integer value) {
        return value != null && value > 0;
    }

    public static boolean isNullOrLessThan0(Double value) {
        return value == null || value < 0;
    }

    public static boolean isNullOrGreatThan0(Double value) {
        return value == null || value > 0;
    }


    public static double getValueOrDefault(Double value) {
        if (value == null) return 0;
        return value.doubleValue();
    }

    public static long getValueOrDefault(Long value) {
        if (value == null) return 0;
        return value.longValue();
    }

    public static int getValueOrDefault(Integer value) {
        if (value == null) return 0;
        return value.intValue();
    }

    public static String getValueOrDefault(String value) {
        if (value == null) return "";
        return value;
    }
}

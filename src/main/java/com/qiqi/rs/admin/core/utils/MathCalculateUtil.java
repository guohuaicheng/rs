package com.qiqi.rs.admin.core.utils;

import java.math.BigDecimal;

public class MathCalculateUtil {

    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    public static double add(double... vs) {
        if (vs == null || vs.length == 0) {
            throw new IllegalArgumentException("no value");
        }
        BigDecimal value = new BigDecimal("0");
        for (double v : vs) {
            value = value.add(new BigDecimal(Double.toString(v)));
        }

        return value.doubleValue();
    }

    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    public static double mul(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        double v = b1.multiply(b2).doubleValue();
        return round(v, scale);
    }

    public static double div(double v1, double v2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 只用于抹零均摊，以及正常四舍五入，不要用于计算后再四舍五入
     *
     * @param v
     * @param scale
     * @return
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        return b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double round(double v) {
        BigDecimal b = new BigDecimal(Double.toString(v));
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double round(String v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(v);
        return b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

//    public static String numberFormat(double d, int maximumFractionDigits) {
//        NumberFormat nf = NumberFormat.getInstance();
//        nf.setGroupingUsed(false);
//        nf.setRoundingMode(RoundingMode.HALF_UP);
//        nf.setMaximumFractionDigits(maximumFractionDigits);
//        nf.setMaximumIntegerDigits(20);
//        return nf.format(d);
//    }
//
//    public static void main(String[] args) {
//        System.out.println(8490.905/4.25);
//        System.out.println(div(0.1,100, 2));
//        System.out.println(mul(1997.86,4.25, 2));
//        System.out.println(mul(1997.86,4.25));
//        System.out.println(add(5979.56, 0, 412));
//    }

//    public static String formatToMoney(Double money) {
//        if (money == null) return "";
//        return DecimalFormatInstanceEnum.INSTANCE_0_00.getDecimalFormat().format(money.doubleValue());
//    }

}

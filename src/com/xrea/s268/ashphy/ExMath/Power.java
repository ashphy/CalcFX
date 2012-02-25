package com.xrea.s268.ashphy.ExMath;

/**
 * Power - Math function
 * 
 * @version $Revision: 1.4 $, $Date: 2003/03/02 06:44:58 $
 */

public class Power {
    public static double power(double x, int n) {
        int absN = Math.abs(n);
        double r = 1.0;

        while (absN != 0) {
            if ((absN & 1) != 0) r *= x;
            x *= x;  absN >>= 1;
        }
        return (n >= 0) ? r : 1 / r;
    }

    public static double power(double x, double y) {
        if (y <=  Integer.MAX_VALUE &&
            y >= -Integer.MAX_VALUE && y == (int)y)
            return power(x, (int)y);
        if (x > 0) return ExMath.exp(y * ExMath.ln(x));
        if (x != 0 || y <= 0)
            throw new ArithmeticException("domain error");
        return 0;
    }
}

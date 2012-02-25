package com.xrea.s268.ashphy.ExMath;

/**
 * Extend Math Class
 * @author Kazuki Hamasaki
 */
public class ExMath
{
	private static double LOG2 = 0.693147180559945309417232121458; // log_e 2
	private static double SQRT2 = 1.41421356237309504880168872421; // sqrt(2)

	public static double ln(double x)
	{
		// ���R�ΐ��i�����W�J�Łj
		if (x <= 0) return Double.NaN;
		Frexp frexp = new Frexp(x / SQRT2);
			// 2^(frexp.k-1) <= x/sqrt(2) < 2^frexp.k
		x /= Exp.ldexp(1, frexp.k); // x -> x / 2^frexp.k
		x = (x - 1) / (x + 1);
		double x2 = x * x, s = x, last;
		int i = 1;
		do
		{
			x *= x2;
			i += 2;
			last = s;
			s += x / i;
		}
		while (last != s);
		return LOG2 * frexp.k + 2 * s;
	}

	public static double log(double x, double base)
	{
		return ln(x) / ln(base);
	}
	
	// C/C++ ���C�u�����֐� t = frexp(x,&k) (x = 2^k t) �� Java ��
	private static class Frexp
	{
		private static double bias = Double
				.longBitsToDouble(0x3ca0000000000000L); // 2^-52
		public double t;
		public int k;

		public Frexp(double x)
		{
			long bits = Double.doubleToLongBits(x);
			k = (int) ((bits >> 52) & 0x7ffL);
			int s = (bits & 0x8000000000000000L) != 0 ? -1 : 1;
			bits &= 0xfffffffffffffL;
			if (k != 0) bits |= 0x10000000000000L; // ���K��
			t = bits * s * bias;
			k -= 1022;
		}
	}

	public static double pow(double x, double base)
	{
		return Power.power(x, base);
	}
	
	public static double exp(double d)
	{
		return Exp.exp(d);
	}
}

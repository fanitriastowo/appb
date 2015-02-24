package com.skripsi.apsb.util;

import java.math.BigDecimal;

public class RoundDecimal {

	// MEMBUAT DOUBLE MEMILIKI BATAS ANGKA DIBELAKANG KOMA, DAN DIBELAKANG KOMA
	// BISA DIATUR APAKAH INGIN DIBULATKAN ATAU TIDAK
	public static double doubleWithDecimalPlace(double x, int scale) {
		return round(x, scale, BigDecimal.ROUND_HALF_EVEN);
	}

	public static double round(double x, int scale, int roundingMethod) {
		try {
			return (new BigDecimal(Double.toString(x)).setScale(scale, roundingMethod)).doubleValue();
		} catch (NumberFormatException ex) {
			if (Double.isInfinite(x)) {
				return x;
			} else {
				return Double.NaN;
			}
		}
	}
}

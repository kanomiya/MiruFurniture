package com.kanomiya.mcmod.core.util.bit;

/**
 * @author Kanomiya
 *
 */
public class BitFieldHelper {

	/**
	 * 指定長マスク値の取得
	 *
	 * @param length マスク値の長さ
	 * @return lengthの長さのマスク値
	 */
	public static int getMask(int length) {
		return (int) (Math.pow(2, length) -1);
	}

	public static boolean intToBool(int flag) {
		if (flag == 1) return true;
		return false;
	}

}

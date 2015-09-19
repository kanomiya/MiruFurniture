package com.kanomiya.mcmod.core.util.bit;

import java.util.Map;

import com.google.common.collect.Maps;
import com.mojang.realmsclient.util.Pair;


/**
 * @author Kanomiya
 *
 */
public class BitFieldStructure {
	protected Map<String, Pair<Integer, Integer>> strToBF = Maps.newConcurrentMap();

	/**
	 *
	 * @param name
	 * @param beginBit Beginning Bit Index (Right To Left)
	 * @param bitLength
	 */
	protected void allocate(String name, int beginBit, int bitLength) {
		if (beginBit < 0) throw new RuntimeException("Exception at allocating " + name + ": beginBit cannot be minus");
		if (beginBit +1 < bitLength) throw new RuntimeException("Exception at allocating " + name + ": too large allocating");

		strToBF.put(name, Pair.of(beginBit, bitLength));
	}

	/**
	 *
	 * raw値から指定ビットフィールドの値を取得<br>
	 * <br>
	 * 処理の流れ<br>
	 * (111011101)<br>
	 * の4番目から3ビット分(110)取得<br>
	 * <br>
	 * >> (4 -3)<br>
	 * <br>
	 * (000011101)1101<br>
	 * 000(011101110)1<br>
	 * <br>
	 *  & (2^3 -1) // 参考に {@link BitFieldHelper#getMask(int)}
	 * <br>
	 * 000(000000110)0<br>
	 * <br>
	 * 下3ビット(110)が取り出せる <br>
	 * <br>
	 * @param name ビットフィールド名
	 * @param raw 生の値
	 * @return 指定ビットフィールド値
	 */
	public int getValue(String name, int raw) {
		if (! strToBF.containsKey(name)) {
			System.out.println("Unknown bit field: " + name);
			return 0;
		}

		Pair<Integer, Integer> pair = strToBF.get(name);

		int value = raw >> (pair.first() - pair.second());

		return value & BitFieldHelper.getMask(pair.second());
	}

	/**
	 *
	 * raw値の指定ビットフィールドに値を設定<br>
	 * <br>
	 * 処理の流れ<br>
	 * (111011101)<br>
	 * の4番目から3ビット分(110)に(001)を設定<br>
	 * <br>
	 * (001) & (2^3 -1)<br>
	 * 値を固定長に<br>
	 * <br>
	 * (001)<br>
	 * <br>
	 * (001) << (4 -3 +1)<br>
	 * <br>
	 * (000000010)<br>
	 * <br>
	 * (111011101) & (~((2^3 -1) << (4 -3 +1)) & (111111111))<br>
	 * <br>
	 * (111010001)<br>
	 * raw値の指定ビットフィールドの値を削除<br>
	 * <br>
	 * (000000010) | (111010001)<br>
	 * <br>
	 * (111010011)<br>
	 * <br>
	 * <br>
	 * 指定ビットフィールド(110)は(001)に設定される <br>
	 * <br>
	 * @param name ビットフィールド名
	 * @param raw 生の値
	 * @return 設定された生の値
	 */
	public int setValue(String name, int raw, int value) {
		if (! strToBF.containsKey(name)) {
			System.out.println("Unknown bit field: " + name);
			return raw;
		}

		Pair<Integer, Integer> pair = strToBF.get(name);

		int mask = BitFieldHelper.getMask(pair.second());
		int movement = (pair.first() - pair.second() +1);

		value = value & mask;
		value = value << movement;

		raw = raw & ~(mask << movement);

		return (raw | value);
	}



}

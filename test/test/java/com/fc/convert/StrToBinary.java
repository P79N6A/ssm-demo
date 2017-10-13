package com.fc.convert;

import com.fc.constant.NormalNumberConstant;

/**
 * @author fangcong
 */
public class StrToBinary {

    /**
     * 将Unicode字符串转换成bool型数组
     */
	public boolean[] strToBool(String input) {
		boolean[] output = binstr16ToBool(binstrToBinstr16(strToBinstr(input)));
		return output;
	}

    /**
     * 将bool型数组转换成Unicode字符串
     * @param input
     * @return
     */
	public String boolToStr(boolean[] input) {
		String output = binstrToStr(binstr16ToBinstr(boolToBinstr16(input)));
		return output;
	}

    /**
     * 将字符串转换成二进制字符串，以空格相隔
     * @param str
     * @return
     */
	private String strToBinstr(String str) {
		char[] strChar = str.toCharArray();
		String result = "";
		for (int i = 0; i < strChar.length; i++) {
			result += Integer.toBinaryString(strChar[i]) + " ";
		}
		return result;
	}

    /**
     * 将二进制字符串转换成Unicode字符串
     * @param binStr
     * @return
     */
	private String binstrToStr(String binStr) {
		String[] tempStr = strToStrArray(binStr);
		char[] tempChar = new char[tempStr.length];
		for (int i = 0; i < tempStr.length; i++) {
			tempChar[i] = binstrToChar(tempStr[i]);
		}
		return String.valueOf(tempChar);
	}

    /**
     * 将二进制字符串格式化成全16位带空格的Binstr
     * @param input
     * @return
     */
	private String binstrToBinstr16(String input) {
		StringBuffer output = new StringBuffer();
		String[] tempStr = strToStrArray(input);
		for (int i = 0; i < tempStr.length; i++) {
			for (int j = NormalNumberConstant.INT_16 - tempStr[i].length(); j > 0; j--) {
				output.append('0');
			}
			output.append(tempStr[i] + " ");
		}
		return output.toString();
	}

    /**
     * 将全16位带空格的Binstr转化成去0前缀的带空格Binstr
     *
     * @param input
     * @return
     */
	private String binstr16ToBinstr(String input) {
		StringBuffer output = new StringBuffer();
		String[] tempStr = strToStrArray(input);
		for (int i = 0; i < tempStr.length; i++) {
			for (int j = 0; j < NormalNumberConstant.INT_16; j++) {
				if (tempStr[i].charAt(j) == '1') {
					output.append(tempStr[i].substring(j) + " ");
					break;
				}
				if (j == 15 && tempStr[i].charAt(j) == '0') {
					output.append("0" + " ");
				}
			}
		}
		return output.toString();
	}

    /**
     * 二进制字串转化为boolean型数组 输入16位有空格的Binstr
     *
     * @param input
     * @return
     */
	private boolean[] binstr16ToBool(String input) {
		String[] tempStr = strToStrArray(input);
		boolean[] output = new boolean[tempStr.length * 16];
		for (int i = 0, j = 0; i < input.length(); i++, j++) {
            if (input.charAt(i) == '1') {
                output[j] = true;
            } else if (input.charAt(i) == '0') {
                output[j] = false;
            } else {
                j--;
            }
        }
		return output;
	}

    /**
     * boolean型数组转化为二进制字串 返回带0前缀16位有空格的Binstr
     *
     * @param input
     * @return
     */
	private String boolToBinstr16(boolean[] input) {
		StringBuffer output = new StringBuffer();
		for (int i = 0; i < input.length; i++) {
			if (input[i]) {
                output.append('1');
            } else {
                output.append('0');
            }
			if ((i + 1) % 16 == 0) {
                output.append(' ');
            }
		}
		output.append(' ');
		return output.toString();
	}

    /**
     * 将二进制字符串转换为char
     *
     * @param binStr
     * @return
     */
	private char binstrToChar(String binStr) {
		int[] temp = binstrToIntArray(binStr);
		int sum = 0;
		for (int i = 0; i < temp.length; i++) {
			sum += temp[temp.length - 1 - i] << i;
		}
		return (char) sum;
	}

    /**
     * 将初始二进制字符串转换成字符串数组，以空格相隔
     *
     * @param str
     * @return
     */
	private String[] strToStrArray(String str) {
		return str.split(" ");
	}

    /**
     * 将二进制字符串转换成int数组
     *
     * @param binStr
     * @return
     */
	private int[] binstrToIntArray(String binStr) {
		char[] temp = binStr.toCharArray();
		int[] result = new int[temp.length];
		for (int i = 0; i < temp.length; i++) {
			result[i] = temp[i] - 48;
		}
		return result;
	}
}

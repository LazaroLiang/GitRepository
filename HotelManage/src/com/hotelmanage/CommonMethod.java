package com.hotelmanage;

import java.sql.Timestamp;

/*
 * 公有类方法，主要封装一些常用的一些函数，该类主要是调节底层数据库与界面显示上存在的分歧
 * 2016-04-21创建
 * author:liangzhuang
 * */
public class CommonMethod {
	/* 将String类型true,false转换为bool类型 */
	public static boolean StringToBool(String sex) {
		if (sex.equals("true")) {
			return true;
		} else if (sex.equals("false")) {
			return false;
		} else if (sex.equals("男")) {
			return true;
		} else if (sex.equals("女")) {
			return false;
		} else {
			return false;
		}
	}

	/* 将String类型时间转换为TimeStamp类型 */
	public static Timestamp StringToDate(String time) {
		// 2016年04月12日
		String year = time.substring(0, 4);
		String month = time.substring(5, 7);
		String day = time.substring(8, 10);
		// System.out.println(year + " " + month + " " + day);
		return Timestamp.valueOf(year + "-" + month + "-" + day + " 00:00:00");
		// 12:00:00");
	}

	/* 将JSON 传输过来的字符串顾客类型对应成数据库中的存储类型 */
	public static String guestTypeChange(String guestType) {
		if (guestType.equals("普通客户")) {
			return "P";
		} else if (guestType.equals("会员")) {
			return "H";
		} else if (guestType.equals("VIP")) {
			return "V";
		} else if (guestType.equals("P")) {
			return "普通客户";
		} else if (guestType.equals("H")) {
			return "会员";
		} else if (guestType.equals("V")) {
			return "VIP";
		} else {
			return null;
		}
	}

	// 床型对应
	public static String roomTypeRefelct(String roomType) {
		if (roomType.equals("单人间")) {
			return "S";
		} else if (roomType.equals("标准间")) {
			return "D";
		} else if (roomType.equals("三人间")) {
			return "T";
		} else if (roomType.equals("大床房")) {
			return "B";
		} else if (roomType.equals("S")) {
			return "单人间";
		} else if (roomType.equals("B")) {
			return "大床房";
		} else if (roomType.equals("T")) {
			return "三人间";
		} else if (roomType.equals("D")) {
			return "标准间";
		} else {
			return null;
		}
	}

	// 房间状态映射
	public static String roomStateRefelct(String roomState) {
		if (roomState.equals("空闲")) {
			return "F";
		} else if (roomState.equals("预定")) {
			return "P";
		} else if (roomState.equals("锁房")) {
			return "L";
		} else if (roomState.equals("已租")) {
			return "R";
		} else if (roomState.equals("清洁")) {
			return "C";
		} else if (roomState.equals("F")) {
			return "空闲";
		} else if (roomState.equals("P")) {
			return "预定";
		} else if (roomState.equals("L")) {
			return "锁房";
		} else if (roomState.equals("C")) {
			return "清洁";
		} else if (roomState.equals("R")) {
			return "已租";
		}
		return null;
	}

	// 订单类型映射
	public static boolean orderTypeReflect(String orderType) {
		if (orderType.equals("非钟点房")) {
			return false;
		} else if (orderType.equals("钟点房")) {
			return true;
		} else {
			return false;
		}
	}

	public static String orderTypeBoolToStr(Boolean orderType) {
		if (orderType) {
			return "钟点房";
		} else {
			return "非钟点房";
		}
	}

	public static boolean orderStateReflect(String orderState) {
		if (orderState.equals("未结算")) {
			return false;
		} else if (orderState.equals("已结算")) {
			return true;
		} else {
			return false;
		}
	}

	public static String orderStateBoolToStr(Boolean orderState) {
		if (orderState) {
			return "已结算";
		} else {
			return "未结算";
		}
	}
}

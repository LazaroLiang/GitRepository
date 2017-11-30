package com.hotelmanage;

import java.sql.Timestamp;

/*
 * �����෽������Ҫ��װһЩ���õ�һЩ������������Ҫ�ǵ��ڵײ����ݿ��������ʾ�ϴ��ڵķ���
 * 2016-04-21����
 * author:liangzhuang
 * */
public class CommonMethod {
	/* ��String����true,falseת��Ϊbool���� */
	public static boolean StringToBool(String sex) {
		if (sex.equals("true")) {
			return true;
		} else if (sex.equals("false")) {
			return false;
		} else if (sex.equals("��")) {
			return true;
		} else if (sex.equals("Ů")) {
			return false;
		} else {
			return false;
		}
	}

	/* ��String����ʱ��ת��ΪTimeStamp���� */
	public static Timestamp StringToDate(String time) {
		// 2016��04��12��
		String year = time.substring(0, 4);
		String month = time.substring(5, 7);
		String day = time.substring(8, 10);
		// System.out.println(year + " " + month + " " + day);
		return Timestamp.valueOf(year + "-" + month + "-" + day + " 00:00:00");
		// 12:00:00");
	}

	/* ��JSON ����������ַ����˿����Ͷ�Ӧ�����ݿ��еĴ洢���� */
	public static String guestTypeChange(String guestType) {
		if (guestType.equals("��ͨ�ͻ�")) {
			return "P";
		} else if (guestType.equals("��Ա")) {
			return "H";
		} else if (guestType.equals("VIP")) {
			return "V";
		} else if (guestType.equals("P")) {
			return "��ͨ�ͻ�";
		} else if (guestType.equals("H")) {
			return "��Ա";
		} else if (guestType.equals("V")) {
			return "VIP";
		} else {
			return null;
		}
	}

	// ���Ͷ�Ӧ
	public static String roomTypeRefelct(String roomType) {
		if (roomType.equals("���˼�")) {
			return "S";
		} else if (roomType.equals("��׼��")) {
			return "D";
		} else if (roomType.equals("���˼�")) {
			return "T";
		} else if (roomType.equals("�󴲷�")) {
			return "B";
		} else if (roomType.equals("S")) {
			return "���˼�";
		} else if (roomType.equals("B")) {
			return "�󴲷�";
		} else if (roomType.equals("T")) {
			return "���˼�";
		} else if (roomType.equals("D")) {
			return "��׼��";
		} else {
			return null;
		}
	}

	// ����״̬ӳ��
	public static String roomStateRefelct(String roomState) {
		if (roomState.equals("����")) {
			return "F";
		} else if (roomState.equals("Ԥ��")) {
			return "P";
		} else if (roomState.equals("����")) {
			return "L";
		} else if (roomState.equals("����")) {
			return "R";
		} else if (roomState.equals("���")) {
			return "C";
		} else if (roomState.equals("F")) {
			return "����";
		} else if (roomState.equals("P")) {
			return "Ԥ��";
		} else if (roomState.equals("L")) {
			return "����";
		} else if (roomState.equals("C")) {
			return "���";
		} else if (roomState.equals("R")) {
			return "����";
		}
		return null;
	}

	// ��������ӳ��
	public static boolean orderTypeReflect(String orderType) {
		if (orderType.equals("���ӵ㷿")) {
			return false;
		} else if (orderType.equals("�ӵ㷿")) {
			return true;
		} else {
			return false;
		}
	}

	public static String orderTypeBoolToStr(Boolean orderType) {
		if (orderType) {
			return "�ӵ㷿";
		} else {
			return "���ӵ㷿";
		}
	}

	public static boolean orderStateReflect(String orderState) {
		if (orderState.equals("δ����")) {
			return false;
		} else if (orderState.equals("�ѽ���")) {
			return true;
		} else {
			return false;
		}
	}

	public static String orderStateBoolToStr(Boolean orderState) {
		if (orderState) {
			return "�ѽ���";
		} else {
			return "δ����";
		}
	}
}

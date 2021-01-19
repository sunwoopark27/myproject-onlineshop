package com.sunwoo.project;

import java.sql.Date;

public class OrderHandler {

  //주문
  static final int ORDER_LENGTH = 3;
  static int[] omemberNumber = new int[ORDER_LENGTH];
  static int[] orderNumber = new int[ORDER_LENGTH];
  static String[] product = new String[ORDER_LENGTH];
  static Date[] orderDate = new Date[ORDER_LENGTH];
  static String[] request = new String[ORDER_LENGTH];
  static int osize = 0;

  static void addOrder() {

    System.out.println("[주문 등록]");

    omemberNumber[osize] = Prompt.promptInt("고객 번호: ");
    orderNumber[osize] = Prompt.promptInt("주문 번호: ");
    product[osize] = Prompt.promptString("상품명: ");
    orderDate[osize] = Prompt.promptDate("주문 날짜: ");
    request[osize] = Prompt.promptString("요청사항: ");
    osize++;

    System.out.println();
  }

  static void listOrder() {
    System.out.println("[주문 목록]");

    for(int i = 0; i < osize; i++) {
      System.out.printf("%d, %d, %s, %s, %s\n", omemberNumber[i], orderNumber[i], product[i], orderDate[i], request[i]);
    }

    System.out.println();
  }
}

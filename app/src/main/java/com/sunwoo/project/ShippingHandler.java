package com.sunwoo.project;

public class ShippingHandler {

  //배송
  static final int SHIPPING_LENGTH = 3;
  static int[] sorderNumber = new int[SHIPPING_LENGTH];
  static int[] trackingNumber = new int[SHIPPING_LENGTH];
  static int[] shippingStatus = new int[SHIPPING_LENGTH];
  static String[] shippingManager = new String[SHIPPING_LENGTH];
  static int ssize = 0;

  static void addShipping() {
    System.out.println("[배송 등록]");

    sorderNumber[ssize] = Prompt.promptInt("주문번호: ");
    trackingNumber[ssize] = Prompt.promptInt("운송장 번호: ");
    shippingStatus[ssize] = Prompt.promptInt("배송상태\n" + "0: 배송준비중\n" + "1: 배송중\n" + "2: 배송완료\n" + "> ");
    shippingManager[ssize] = Prompt.promptString("배송 담당자: ");
    ssize++;

    System.out.println();
  }

  static void listShipping() {
    System.out.println("[배송 목록]");

    for (int i = 0; i < ssize; i++) {

      String statusLabel = null;

      switch (shippingStatus[i]) {
        case 0:
          statusLabel = "배송 준비중";
          break;
        case 1: 
          statusLabel = "배송중";
          break;
        case 2:
          statusLabel = "배송 완료";
          break;
      }

      System.out.printf("%d, %d, %s, %s\n", sorderNumber[i], trackingNumber[i], statusLabel, shippingManager[i]);
    }
    System.out.println();
  }
}

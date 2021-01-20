package com.sunwoo.project.Handler;

import com.sunwoo.util.Prompt;

public class ShippingHandler {

  static class Shipping {
    int orderNumber;
    int trackingNumber;
    int shippingStatus;
    String shippingManager;
  }

  //배송
  static final int LENGTH = 100;

  static Shipping[] shippings = new Shipping[LENGTH];

  static int size = 0;

  public static void add() {
    System.out.println("[배송 등록]");

    Shipping s = new Shipping();

    s.orderNumber = Prompt.promptInt("주문번호: ");
    s.trackingNumber = Prompt.promptInt("운송장 번호: ");
    s.shippingStatus = Prompt.promptInt("배송상태\n" + "0: 배송준비중\n" + "1: 배송중\n" + "2: 배송완료\n" + "> ");
    s.shippingManager = Prompt.promptString("배송 담당자: ");

    shippings[size++] = s;

    System.out.println();
  }

  public static void list() {
    System.out.println("[배송 목록]");

    for (int i = 0; i < size; i++) {

      Shipping s = shippings[i];

      String statusLabel = null;

      switch (s.shippingStatus) {
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

      System.out.printf("%d, %d, %s, %s\n", s.orderNumber, s.trackingNumber, statusLabel, s.shippingManager);
    }
    System.out.println();
  }
}

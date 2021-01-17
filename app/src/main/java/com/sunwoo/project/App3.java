package com.sunwoo.project;

import java.util.Scanner;

public class App3 {
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    final int LENGTH = 3;
    int[] orderNumber = new int[LENGTH];
    int[] trackingNumber = new int[LENGTH];
    int[] shippingStatus = new int[LENGTH];
    String[] shippingManager = new String[LENGTH];

    int size = 0;

    System.out.println("[배송]");

    for (int i = 0; i < LENGTH; i++) {

      System.out.print("주문 번호: ");
      orderNumber[i] = scanner.nextInt();

      System.out.print("운송장 번호: ");
      trackingNumber[i] = scanner.nextInt();
      scanner.nextLine();

      System.out.println("배송 상태");
      System.out.println("0: 배송 준비중");
      System.out.println("1: 배송중");
      System.out.println("2: 배송 완료");
      System.out.print("> ");
      shippingStatus[i] = Integer.valueOf(scanner.nextLine());

      System.out.print("배송 담당자: ");
      shippingManager[i] = scanner.nextLine();

      size++;

      System.out.print("계속 입력하시겠습니까?(y/N) ");
      String response = scanner.nextLine();
      if(response.length() == 0 || response.equalsIgnoreCase("n")) {
        System.out.println("안녕!");
        break;
      }

    }

    scanner.close();

    System.out.println("-------------------------------------------------------------------");

    for (int i = 0; i < size; i++) {

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

      System.out.printf("%d, %d, %s, %s\n", orderNumber[i], trackingNumber[i], statusLabel, shippingManager[i]);
    }
  }
}

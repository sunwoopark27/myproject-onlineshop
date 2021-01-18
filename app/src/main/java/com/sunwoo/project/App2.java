
package com.sunwoo.project;

import java.sql.Date;
import java.util.Scanner;

public class App2 {
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    System.out.println("[주문 정보]");

    final int LENGTH = 3;

    int[] customerNumber = new int[LENGTH];
    int[] orderNumber = new int[LENGTH];
    String[] product = new String[LENGTH];
    Date[] orderDate = new Date[LENGTH];
    String[] request = new String[LENGTH];

    int size = 0;

    for(int i = 0; i < LENGTH; i++) {

      System.out.print("고객 번호: ");
      customerNumber[i] = scanner.nextInt();

      System.out.print("주문 번호: ");
      orderNumber[i] = scanner.nextInt();
      scanner.nextLine();

      System.out.print("상품명: ");
      product[i] = scanner.nextLine();

      System.out.print("주문 날짜: ");
      orderDate[i] = Date.valueOf(scanner.nextLine());

      System.out.print("요청사항: "); // 아무것도 입력 안하면 없음 들어가게
      request[i] = scanner.nextLine();

      size++;

      System.out.println();

      System.out.print("계속 하시겠습니까?(y/N) ");
      String response = scanner.nextLine();

      if(response.length() == 0 || response.equalsIgnoreCase("n")) {
        System.out.println("안녕!");
        break;
      }

      System.out.println();

    }

    scanner.close();

    System.out.println("--------------------------------------------------------------");

    for(int i = 0; i < size; i++) {
      System.out.printf("%d, %d, %s, %s, %s\n", customerNumber[i], orderNumber[i], product[i], orderDate[i], request[i]);
    }

  }
}
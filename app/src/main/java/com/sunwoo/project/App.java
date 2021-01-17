package com.sunwoo.project;

import java.sql.Date;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);







    final int LENGTH = 3;
    int[] customerNumber = new int[LENGTH];
    String[] customerName = new String[LENGTH];
    String[] customerPhone = new String[LENGTH];
    String[] password = new String[LENGTH];
    String[] address = new String[LENGTH];
    String[] mail = new String[LENGTH];
    Date[] joinDate = new Date[LENGTH];

    int size = 0;

    System.out.println("[고객 정보]");

    for (int i = 0; i < LENGTH; i++) {

      System.out.print("고객 번호: ");
      customerNumber[i] = scanner.nextInt();
      scanner.nextLine();

      System.out.print("이름: ");
      customerName[i] = scanner.nextLine();

      System.out.print("전화번호: ");
      customerPhone[i] = scanner.nextLine();

      System.out.print("비밀번호: ");
      password[i] = scanner.nextLine();

      System.out.print("주소: ");
      address[i] = scanner.nextLine();

      System.out.print("메일: ");
      mail[i] = scanner.nextLine();

      joinDate[i] = new Date(System.currentTimeMillis());

      size++;

      System.out.println();

      System.out.print("계속 입력하시겠습니까?(y/N) ");
      String response = scanner.nextLine();
      if(response.length() == 0 || response.equalsIgnoreCase("n")) {
        System.out.println("안녕!");
        break;
      }


      System.out.println();

    }

    scanner.close();


    System.out.println("--------------------------------------------------------------------------------------------");

    for(int i = 0; i < size; i++) {
      System.out.printf("%d, %s, %s, %s, %s, %s\n",customerNumber[i], customerName[i], customerPhone[i], address[i], mail[i], joinDate[i]);

    }

  }
}

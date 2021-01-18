package com.sunwoo.project;

import java.sql.Date;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    //회원
    final int MEMBER_LENGTH = 3;
    int[] memberNumber = new int[MEMBER_LENGTH];
    String[] memberName = new String[MEMBER_LENGTH];
    String[] memberPhone = new String[MEMBER_LENGTH];
    String[] password = new String[MEMBER_LENGTH];
    String[] address = new String[MEMBER_LENGTH];
    String[] mail = new String[MEMBER_LENGTH];
    Date[] joinDate = new Date[MEMBER_LENGTH];
    int size = 0;

    //주문
    final int ORDER_LENGTH = 3;
    int[] omemberNumber = new int[ORDER_LENGTH];
    int[] orderNumber = new int[ORDER_LENGTH];
    String[] product = new String[ORDER_LENGTH];
    Date[] orderDate = new Date[ORDER_LENGTH];
    String[] request = new String[ORDER_LENGTH];
    int osize = 0;

    //배송
    final int SHIPPING_LENGTH = 3;
    int[] sorderNumber = new int[SHIPPING_LENGTH];
    int[] trackingNumber = new int[SHIPPING_LENGTH];
    int[] shippingStatus = new int[SHIPPING_LENGTH];
    String[] shippingManager = new String[SHIPPING_LENGTH];
    int ssize = 0;

    while(true) {
      System.out.println("    ▶명령어 목록◀");
      System.out.println("회원 등록: /member/add");
      System.out.println("회원 목록: /member/list");
      System.out.println("주문 등록: /order/add");
      System.out.println("주문 목록: /order/list");
      System.out.println("배송 등록: /shipping/add");
      System.out.println("배송 목록: /shipping/add");
      System.out.println();

      System.out.print("명령> ");
      String command = scanner.nextLine();

      if(command.equalsIgnoreCase("/member/add")) {
        System.out.println("[회원 등록]");

        System.out.print("회원 번호: ");
        memberNumber[size] = scanner.nextInt();
        scanner.nextLine();

        System.out.print("이름: ");
        memberName[size] = scanner.nextLine();

        System.out.print("전화번호: ");
        memberPhone[size] = scanner.nextLine();

        System.out.print("비밀번호: ");
        password[size] = scanner.nextLine();

        System.out.print("주소: ");
        address[size] = scanner.nextLine();

        System.out.print("메일: ");
        mail[size] = scanner.nextLine();

        joinDate[size] = new Date(System.currentTimeMillis());

        size++;

        System.out.println();

      }else if(command.equalsIgnoreCase("/member/list")) {
        System.out.println("[회원 목록]");

        for(int i = 0; i < size; i++) {
          System.out.printf("%d, %s, %s, %s, %s, %s\n",memberNumber[i], memberName[i], memberPhone[i], address[i], mail[i], joinDate[i]);

        }

        System.out.println();

      }else if(command.equalsIgnoreCase("/order/add")) {
        System.out.println("[주문 등록]");

        System.out.print("고객 번호: ");
        omemberNumber[osize] = scanner.nextInt();

        System.out.print("주문 번호: ");
        orderNumber[osize] = scanner.nextInt();
        scanner.nextLine();

        System.out.print("상품명: ");
        product[osize] = scanner.nextLine();

        System.out.print("주문 날짜: ");
        orderDate[osize] = Date.valueOf(scanner.nextLine());

        System.out.print("요청사항: "); // 아무것도 입력 안하면 없음 들어가게
        request[osize] = scanner.nextLine();

        osize++;

        System.out.println();

      }else if(command.equalsIgnoreCase("/order/list")) {
        System.out.println("[주문 목록]");

        for(int i = 0; i < osize; i++) {
          System.out.printf("%d, %d, %s, %s, %s\n", omemberNumber[i], orderNumber[i], product[i], orderDate[i], request[i]);
        }

        System.out.println();

      }else if (command.equalsIgnoreCase("/shipping/add")) {
        System.out.println("[배송 등록]");

        System.out.print("주문 번호: ");
        sorderNumber[ssize] = scanner.nextInt();

        System.out.print("운송장 번호: ");
        trackingNumber[ssize] = scanner.nextInt();
        scanner.nextLine();

        System.out.println("배송 상태");
        System.out.println("0: 배송 준비중");
        System.out.println("1: 배송중");
        System.out.println("2: 배송 완료");
        System.out.print("> ");
        shippingStatus[ssize] = Integer.valueOf(scanner.nextLine());

        System.out.print("배송 담당자: ");
        shippingManager[ssize] = scanner.nextLine();

        ssize++;

        System.out.println();
      }else if (command.equalsIgnoreCase("/shipping/list")) {
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
      }else if(command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("exit")) {
        System.out.println("안녕!");
        break;
      }else {
        System.out.println("실행할 수 없는 명령어 입니다.");
      }
    }

    scanner.close();
  }
}
package com.sunwoo.project;

import java.sql.Date;
import java.util.Scanner;

public class App {

  static Scanner scanner = new Scanner(System.in);

  //회원
  static final int MEMBER_LENGTH = 3;
  static int[] memberNumber = new int[MEMBER_LENGTH];
  static String[] memberName = new String[MEMBER_LENGTH];
  static String[] memberPhone = new String[MEMBER_LENGTH];
  static String[] password = new String[MEMBER_LENGTH];
  static String[] address = new String[MEMBER_LENGTH];
  static String[] mail = new String[MEMBER_LENGTH];
  static Date[] joinDate = new Date[MEMBER_LENGTH];
  static int size = 0;

  static void addMember(){
    System.out.println("[회원 등록]");

    memberNumber[size] = promptInt("회원 번호: ");

    memberName[size] = promptString("이름: ");

    memberPhone[size] = promptString("전화번호: ");

    password[size] = promptString("비밀번호: ");

    address[size] = promptString("주소: ");

    mail[size] = promptString("메일: ");

    joinDate[size] = new Date(System.currentTimeMillis());

    size++;

    System.out.println();

  }

  static void listMember() {
    System.out.println("[회원 목록]");

    for(int i = 0; i < size; i++) {
      System.out.printf("%d, %s, %s, %s, %s, %s\n",memberNumber[i], memberName[i], memberPhone[i], address[i], mail[i], joinDate[i]);

    }

    System.out.println();
  }

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

    omemberNumber[osize] = promptInt("고객 번호: ");

    orderNumber[osize] = promptInt("주문 번호: ");

    product[osize] = promptString("상품명: ");

    orderDate[osize] = promptDate("주문 날짜: ");

    request[osize] = promptString("요청사항: ");

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

  //배송
  static final int SHIPPING_LENGTH = 3;
  static int[] sorderNumber = new int[SHIPPING_LENGTH];
  static int[] trackingNumber = new int[SHIPPING_LENGTH];
  static int[] shippingStatus = new int[SHIPPING_LENGTH];
  static String[] shippingManager = new String[SHIPPING_LENGTH];
  static int ssize = 0;

  static void addShipping() {
    System.out.println("[배송 등록]");

    sorderNumber[ssize] = promptInt("주문번호");

    trackingNumber[ssize] = promptInt("운송장 번호: ");

    shippingStatus[ssize] = promptInt("배송상태\n" + "0: 배송준비중\n" + "1: 배송중\n" + "2: 배송완료\n" + "> ");

    shippingManager[ssize] = promptString("배송 담당자: ");

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

  public static void main(String[] args) {

    while(true) {
      System.out.println("    ▶명령어 목록◀");
      System.out.println("회원 등록: /member/add");
      System.out.println("회원 목록: /member/list");
      System.out.println("주문 등록: /order/add");
      System.out.println("주문 목록: /order/list");
      System.out.println("배송 등록: /shipping/add");
      System.out.println("배송 목록: /shipping/list");
      System.out.println();

      String command = promptString("명령> ");

      if(command.equalsIgnoreCase("/member/add")) {
        addMember();

      }else if(command.equalsIgnoreCase("/member/list")) {
        listMember();

      }else if(command.equalsIgnoreCase("/order/add")) {
        addOrder();

      }else if(command.equalsIgnoreCase("/order/list")) {
        listOrder();

      }else if (command.equalsIgnoreCase("/shipping/add")) {
        addShipping();

      }else if (command.equalsIgnoreCase("/shipping/list")) {
        listShipping();

      }else if(command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("exit")) {
        System.out.println("안녕!");
        break;
      }else {
        System.out.println("실행할 수 없는 명령어 입니다.");
        System.out.println();
      }
    }
    scanner.close();
  }

  //사용자에게 명령 입력 받는 메소드
  static String promptString (String title) {
    System.out.print(title);
    return scanner.nextLine();
  }

  //명령으로 string 입력받아 int값으로 변환해주는 메소드
  static int promptInt (String title) {
    String str = promptString(title);
    return Integer.valueOf(str);
  }
  //명령으로 string 입력받아 Date값으로 변환해주는 메소드
  static Date promptDate (String title) {
    String str = promptString(title);
    return Date.valueOf(str);
  }
}
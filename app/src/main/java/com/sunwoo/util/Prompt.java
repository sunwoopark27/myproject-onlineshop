package com.sunwoo.util;

import java.sql.Date;
import java.util.Scanner;

public class Prompt {
  static Scanner scanner = new Scanner(System.in);
  //사용자에게 명령 입력 받는 메소드
  public static String inputString (String title) {
    System.out.print(title);
    return scanner.nextLine();
  }

  //명령으로 string 입력받아 int값으로 변환해주는 메소드
  public static int inputInt (String title) {
    String str = inputString(title);
    return Integer.valueOf(str);
  }
  //명령으로 string 입력받아 Date값으로 변환해주는 메소드
  public static Date inputDate (String title) {
    String str = inputString(title);
    return Date.valueOf(str);
  }

  public static void close() {
    scanner.close();
  }
}

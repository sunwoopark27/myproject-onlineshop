package com.sunwoo.project;

import com.sunwoo.project.Handler.BoardHandler;
import com.sunwoo.project.Handler.BoardHandler2;
import com.sunwoo.project.Handler.BoardHandler3;
import com.sunwoo.project.Handler.MemberHandler;
import com.sunwoo.project.Handler.OrderHandler;
import com.sunwoo.project.Handler.ProductHandler;
import com.sunwoo.project.Handler.ShippingHandler;
import com.sunwoo.util.Prompt;

public class App {

  public static void main(String[] args) {

    while(true) {
      System.out.println("▶명령어◀");
      System.out.println("1. 회원 등록");
      System.out.println("2. 회원 목록");
      System.out.println("3. 상품 등록");
      System.out.println("4. 상품 목록");
      System.out.println("5. 주문 등록");
      System.out.println("6. 주문 목록");
      System.out.println("7. 배송 등록");
      System.out.println("8. 배송 목록");
      System.out.println("9. 상품 문의");
      System.out.println("10. 상품 문의 목록");
      System.out.println("11. 배송 문의");
      System.out.println("12. 배송 문의 목록");
      System.out.println("13. 교환/반품 문의");
      System.out.println("14. 교환/반품 문의 목록");
      System.out.println();

      String command = Prompt.promptString("명령> ");

      if(command.equalsIgnoreCase("회원 등록")) {
        MemberHandler.add();

      }else if(command.equalsIgnoreCase("회원 목록")) {
        MemberHandler.list();

      }else if (command.equalsIgnoreCase("상품 등록")) {
        ProductHandler.add();

      }else if (command.equalsIgnoreCase("상품 목록")) {
        ProductHandler.list();

      }else if(command.equalsIgnoreCase("주문 등록")) {
        OrderHandler.add();

      }else if(command.equalsIgnoreCase("주문 목록")) {
        OrderHandler.list();

      }else if (command.equalsIgnoreCase("배송 등록")) {
        ShippingHandler.add();

      }else if (command.equalsIgnoreCase("배송 목록")) {
        ShippingHandler.list();
      }else if (command.equalsIgnoreCase("상품 문의")) {
        BoardHandler.add();
      }else if (command.equalsIgnoreCase("상품 문의 목록")) {
        BoardHandler.list();
      }else if (command.equalsIgnoreCase("배송 문의")) {
        BoardHandler2.add();
      }else if (command.equalsIgnoreCase("배송 문의 목록")) {
        BoardHandler2.list();
      }else if (command.equalsIgnoreCase("교환/반품 문의")) {
        BoardHandler3.add();
      }else if (command.equalsIgnoreCase("교환/반품 문의 목록")) {
        BoardHandler3.list();
      }else if(command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("exit")) {
        System.out.println("안녕!");
        break;
      }else {
        System.out.println("실행할 수 없는 명령어 입니다.");
        System.out.println();
      }
    }
    Prompt.close();
  }


}
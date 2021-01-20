package com.sunwoo.project;

import com.sunwoo.project.Handler.MemberHandler;
import com.sunwoo.project.Handler.OrderHandler;
import com.sunwoo.project.Handler.ShippingHandler;
import com.sunwoo.util.Prompt;

public class App {

  public static void main(String[] args) {

    while(true) {
      System.out.println("▶명령어◀");
      System.out.println("회원 등록");
      System.out.println("회원 목록");
      System.out.println("주문 등록");
      System.out.println("주문 목록");
      System.out.println("배송 등록");
      System.out.println("배송 목록");
      System.out.println();

      String command = Prompt.promptString("명령> ");

      if(command.equalsIgnoreCase("회원 등록")) {
        MemberHandler.add();

      }else if(command.equalsIgnoreCase("회원 목록")) {
        MemberHandler.list();

      }else if(command.equalsIgnoreCase("주문 등록")) {
        OrderHandler.add();

      }else if(command.equalsIgnoreCase("주문 목록")) {
        OrderHandler.list();

      }else if (command.equalsIgnoreCase("배송 등록")) {
        ShippingHandler.add();

      }else if (command.equalsIgnoreCase("배송 목록")) {
        ShippingHandler.list();

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
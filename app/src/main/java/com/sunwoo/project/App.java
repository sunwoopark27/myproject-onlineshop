package com.sunwoo.project;

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
        MemberHandler.addMember();

      }else if(command.equalsIgnoreCase("회원 목록")) {
        MemberHandler.listMember();

      }else if(command.equalsIgnoreCase("주문 등록")) {
        OrderHandler.addOrder();

      }else if(command.equalsIgnoreCase("주문 목록")) {
        OrderHandler.listOrder();

      }else if (command.equalsIgnoreCase("배송 등록")) {
        ShippingHandler.addShipping();

      }else if (command.equalsIgnoreCase("배송 목록")) {
        ShippingHandler.listShipping();

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
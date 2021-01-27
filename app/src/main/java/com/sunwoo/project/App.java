package com.sunwoo.project;

import com.sunwoo.project.handler.BoardHandler;
import com.sunwoo.project.handler.MemberHandler;
import com.sunwoo.project.handler.OrderHandler;
import com.sunwoo.project.handler.ProductHandler;
import com.sunwoo.project.handler.ShippingHandler;
import com.sunwoo.util.Prompt;

public class App {

  public static void main(String[] args) {

    BoardHandler boardList = new BoardHandler();
    BoardHandler boardList2 = new BoardHandler();
    BoardHandler boardList3 = new BoardHandler();

    MemberHandler memberList = new MemberHandler();

    ProductHandler productList = new ProductHandler();

    OrderHandler orderList = new OrderHandler(memberList, productList);

    ShippingHandler shippingList = new ShippingHandler(memberList);


    while(true) {
      System.out.println("▶명령어◀");
      System.out.println("<회원> 등록/목록");
      System.out.println("<상품> 등록/목록");
      System.out.println("       문의/문의 목록");
      System.out.println("<주문> 등록/목록");
      System.out.println("<배송> 등록/목록");
      System.out.println("       문의/문의 목록");
      System.out.println("<교환/반품> 문의/문의 목록");
      System.out.println();

      String command = Prompt.promptString("명령> ");

      if(command.equalsIgnoreCase("회원 등록")) {
        memberList.add();

      }else if(command.equalsIgnoreCase("회원 목록")) {
        memberList.list();

      }else if (command.equalsIgnoreCase("상품 등록")) {
        productList.add();

      }else if (command.equalsIgnoreCase("상품 목록")) {
        productList.list();

      }else if(command.equalsIgnoreCase("주문 등록")) {
        orderList.add();

      }else if(command.equalsIgnoreCase("주문 목록")) {
        orderList.list();

      }else if (command.equalsIgnoreCase("배송 등록")) {
        shippingList.add();

      }else if (command.equalsIgnoreCase("배송 목록")) {
        shippingList.list();

      }else if (command.equalsIgnoreCase("상품 문의")) {
        boardList.add();

      }else if (command.equalsIgnoreCase("상품 문의 목록")) {
        boardList.list();

      }else if (command.equalsIgnoreCase("배송 문의")) {
        boardList2.add();

      }else if (command.equalsIgnoreCase("배송 문의 목록")) {
        boardList2.list();

      }else if (command.equalsIgnoreCase("교환/반품 문의")) {
        boardList3.add();

      }else if (command.equalsIgnoreCase("교환/반품 문의 목록")) {
        boardList3.list();

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
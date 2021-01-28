package com.sunwoo.project;

import com.sunwoo.project.handler.BoardHandler;
import com.sunwoo.project.handler.MemberHandler;
import com.sunwoo.project.handler.OrderHandler;
import com.sunwoo.project.handler.ProductHandler;
import com.sunwoo.project.handler.ShippingHandler;
import com.sunwoo.util.Prompt;

public class App {

  public static void main(String[] args) {

    BoardHandler boardProduct = new BoardHandler();
    BoardHandler boardShipping = new BoardHandler();
    BoardHandler boardExchangeReturn = new BoardHandler();

    MemberHandler memberList = new MemberHandler();

    ProductHandler productList = new ProductHandler();

    OrderHandler orderList = new OrderHandler(memberList, productList);

    ShippingHandler shippingList = new ShippingHandler(memberList,orderList);

    while(true) {
      System.out.println("▶명령어◀");
      System.out.println("<회원> 등록/목록/상세/수정/삭제");
      System.out.println("<상품> 등록/목록/상세/수정/삭제");
      System.out.println("<주문> 등록/목록/상세/수정/삭제");
      System.out.println("<배송> 등록/목록/상세/수정/삭제");
      System.out.println("<문의> Q&A");
      System.out.println();

      String command = Prompt.promptString("명령> ");

      if(command.equalsIgnoreCase("회원 등록")) {
        memberList.add();

      }else if(command.equalsIgnoreCase("회원 목록")) {
        memberList.list();

      }else if(command.equalsIgnoreCase("회원 상세")) {
        memberList.detail();

      }else if(command.equalsIgnoreCase("회원 수정")) {
        memberList.update();

      }else if(command.equalsIgnoreCase("회원 삭제")) {
        memberList.delete();

      }else if (command.equalsIgnoreCase("상품 등록")) {
        productList.add();

      }else if (command.equalsIgnoreCase("상품 목록")) {
        productList.list();

      }else if (command.equalsIgnoreCase("상품 상세")) {
        productList.detail();

      }else if (command.equalsIgnoreCase("상품 수정")) {
        productList.update();

      }else if (command.equalsIgnoreCase("상품 삭제")) {
        productList.delete();

      }else if(command.equalsIgnoreCase("주문 등록")) {
        orderList.add();

      }else if(command.equalsIgnoreCase("주문 목록")) {
        orderList.list();

      }else if(command.equalsIgnoreCase("주문 상세")) {
        orderList.detail();

      }else if(command.equalsIgnoreCase("주문 수정")) {
        orderList.update();

      }else if(command.equalsIgnoreCase("주문 삭제")) {
        orderList.delete();

      }else if (command.equalsIgnoreCase("배송 등록")) {
        shippingList.add();

      }else if (command.equalsIgnoreCase("배송 목록")) {
        shippingList.list();

      }else if(command.equalsIgnoreCase("배송 상세")) {
        shippingList.detail();

      }else if(command.equalsIgnoreCase("배송 수정")) {
        shippingList.update();

      }else if(command.equalsIgnoreCase("배송 삭제")) {
        shippingList.delete();


      }else if(command.equals("Q&A")) {
        while(true) {
          System.out.println("[Q&A]");
          System.out.println("▶명령어◀");
          System.out.println("<상품> 문의/목록/상세/수정/삭제");
          System.out.println("<배송> 문의/문의 목록");
          System.out.println("<교환/반품> 문의/문의 목록");
          System.out.println("<뒤로가기> back");
          System.out.println();
          command = Prompt.promptString("명령> ");

          if (command.equalsIgnoreCase("상품 문의")) {
            boardProduct.add();

          }else if (command.equalsIgnoreCase("상품 문의 목록")) {
            boardProduct.list();

          }else if (command.equalsIgnoreCase("상품 문의 상세")) {
            boardProduct.detail();

          }else if (command.equalsIgnoreCase("상품 문의 수정")) {
            boardProduct.update();

          }else if (command.equalsIgnoreCase("상품 문의 삭제")) {
            boardProduct.delete();

          }else if (command.equalsIgnoreCase("배송 문의")) {
            boardShipping.add();

          }else if (command.equalsIgnoreCase("배송 문의 목록")) {
            boardShipping.list();

          }else if (command.equalsIgnoreCase("교환/반품 문의")) {
            boardExchangeReturn.add();

          }else if (command.equalsIgnoreCase("교환/반품 문의 목록")) {
            boardExchangeReturn.list();

          }else if (command.equalsIgnoreCase("back")) {

            break;
          }
        }
      }else if(command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("exit")) {
        System.out.println("이용해주셔서 감사합니다!");
        break;
      }else {
        System.out.println("실행할 수 없는 명령어 입니다.");
        System.out.println();
      }
    }
    Prompt.close();
  }


}
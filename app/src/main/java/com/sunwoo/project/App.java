package com.sunwoo.project;

import com.sunwoo.project.handler.BoardHandler;
import com.sunwoo.project.handler.MemberHandler;
import com.sunwoo.project.handler.OrderHandler;
import com.sunwoo.project.handler.ProductHandler;
import com.sunwoo.project.handler.ShippingHandler;
import com.sunwoo.util.Prompt;

public class App {

  static BoardHandler boardProduct = new BoardHandler();
  static BoardHandler boardShipping = new BoardHandler();
  static BoardHandler boardExchangeReturn = new BoardHandler();
  static BoardHandler boardReview = new BoardHandler();

  public static void main(String[] args) {


    MemberHandler memberHandler = new MemberHandler();

    ProductHandler productHandler = new ProductHandler();

    OrderHandler orderHandler = new OrderHandler(memberHandler, productHandler);

    ShippingHandler shippingHandler = new ShippingHandler(memberHandler, orderHandler);

    loop: 
      while(true) {
        System.out.println("▷ 메인 ◁");
        System.out.println("1. 회원");
        System.out.println("2. 상품");
        System.out.println("3. 주문");
        System.out.println("4. 배송");
        System.out.println("5. 게시판");
        System.out.println("0. 종료");
        System.out.println();

        String command = Prompt.inputString("명령> ");
        System.out.println();

        switch(command) {

          case "1" :
            memberHandler.service();
            break;

          case "2" :
            productHandler.service();
            break;

          case "3" :
            orderHandler.service();
            break;

          case "4" :
            shippingHandler.service();
            break;

          case "5" :
            chooseBoard();
            break;

          case "0" :

            System.out.println("이용해주셔서 감사합니다.");
            break loop;

          default :

            System.out.println("메뉴 번호가 맞지 않습니다.");

        }
      }
  }
  public static void chooseBoard() {
    loop:
      while(true) {
        System.out.println("[메인 > 게시판]");
        System.out.println("1. 상품 문의");
        System.out.println("2. 배송 문의");
        System.out.println("3. 교환/반품 문의");
        System.out.println("4. 리뷰");
        System.out.println("0. 이전 메뉴");
        System.out.println();

        String command = com.sunwoo.util.Prompt.inputString("명령> ");
        System.out.println();

        switch(command) {
          case "1" :
            boardProduct.service("상품 문의");

          case "2" :
            boardShipping.service("배송 문의");

          case "3" :
            boardExchangeReturn.service("교환/반품 문의");

          case "4" :
            boardReview.service("리뷰");

          case "0" :
            System.out.println("메인으로 돌아갑니다.");
            break loop;
          default :
            System.out.println("잘못된 메뉴 번호 입니다.");

        }
        System.out.println();
      }
  }
}
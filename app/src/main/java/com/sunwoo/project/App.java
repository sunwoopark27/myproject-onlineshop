package com.sunwoo.project;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;
import com.sunwoo.project.handler.BoardServiceExchangeReturn;
import com.sunwoo.project.handler.BoardServiceProduct;
import com.sunwoo.project.handler.BoardServiceReview;
import com.sunwoo.project.handler.BoardServiceShipping;
import com.sunwoo.project.handler.MemberService;
import com.sunwoo.project.handler.MemberValidatorHandler;
import com.sunwoo.project.handler.OrderService;
import com.sunwoo.project.handler.OrderValidatorHandler;
import com.sunwoo.project.handler.ProductService;
import com.sunwoo.project.handler.ProductValidatorHandler;
import com.sunwoo.project.handler.ShippingService;
import com.sunwoo.util.Prompt;

public class App {

  static BoardServiceProduct boardServiceProduct = new BoardServiceProduct();
  static BoardServiceReview boardServiceReview = new BoardServiceReview();
  static BoardServiceShipping boardServiceShipping = new BoardServiceShipping();
  static BoardServiceExchangeReturn boardServiceExchangeReturn = new BoardServiceExchangeReturn();

  static ArrayDeque<String> commandStack = new ArrayDeque<>();
  static LinkedList<String> commandQueue = new LinkedList<>();

  public static void main(String[] args) throws CloneNotSupportedException {

    MemberService memberService = new MemberService();
    MemberValidatorHandler memberValidatorHandler = new MemberValidatorHandler(memberService.getMemberList());

    ProductService productService = new ProductService();
    ProductValidatorHandler productValidatorHandler = new ProductValidatorHandler(productService.getProductList());

    OrderService orderService = new OrderService(memberValidatorHandler, productValidatorHandler);
    OrderValidatorHandler orderValidatorHandler = new OrderValidatorHandler(orderService.getOrderList());

    ShippingService shippingService = new ShippingService(memberValidatorHandler, orderValidatorHandler);

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

        commandStack.push(command);
        commandQueue.offer(command);

        switch(command) {

          case "1" :
            memberService.menu();
            break;

          case "2" :
            productService.menu();
            break;

          case "3" :
            orderService.menu();
            break;

          case "4" :
            shippingService.menu();
            break;

          case "5" :
            chooseBoard();
            break;

          case "history" : 
            printCommandHistory(commandStack.iterator());
            System.out.println();
            break;

          case "history2" : 
            printCommandHistory(commandQueue.iterator());
            System.out.println();
            break;

          case "0" :

            System.out.println("이용해주셔서 감사합니다.");
            break loop;

          default :

            System.out.println("메뉴 번호가 맞지 않습니다.");
            System.out.println();

        }

      }
  }

  public static void chooseBoard() throws CloneNotSupportedException {
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
            boardServiceProduct.menu("상품 문의");
            break;

          case "2" :
            boardServiceShipping.menu("배송 문의");
            break;

          case "3" :
            boardServiceExchangeReturn.menu("교환/반품 문의");
            break;

          case "4" :
            boardServiceReview.menu("리뷰");
            break;

          case "0" :
            System.out.println("메인으로 돌아갑니다.");
            System.out.println();
            return;

          default :
            System.out.println("잘못된 메뉴 번호 입니다.");

        }
        System.out.println();
      }
  }

  private static void printCommandHistory(Iterator<String> iterator) {

    int count = 0;
    while(iterator.hasNext()) {
      System.out.println(iterator.next());
      if(++count % 5 == 0) {
        String input = Prompt.inputString(": ");
        if(input.equalsIgnoreCase("q")) {
          break;
        }

      }
    }
  }

}
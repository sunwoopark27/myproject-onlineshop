package com.sunwoo.project;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;
import com.sunwoo.project.handler.BoardChooseHandler;
import com.sunwoo.project.handler.MemberService;
import com.sunwoo.project.handler.MemberValidatorHandler;
import com.sunwoo.project.handler.OrderService;
import com.sunwoo.project.handler.OrderValidatorHandler;
import com.sunwoo.project.handler.ProductService;
import com.sunwoo.project.handler.ProductValidatorHandler;
import com.sunwoo.project.handler.ShippingService;
import com.sunwoo.util.Prompt;

public class App {

  static ArrayDeque<String> commandStack = new ArrayDeque<>();
  static LinkedList<String> commandQueue = new LinkedList<>();


  public static void main(String[] args) throws Exception {
    App app = new App();
    app.service();
  }

  public void service() throws Exception {

    MemberService memberService = new MemberService();
    MemberValidatorHandler memberValidatorHandler = new MemberValidatorHandler(memberService.getMemberList());

    ProductService productService = new ProductService();
    ProductValidatorHandler productValidatorHandler = new ProductValidatorHandler(productService.getProductList());

    OrderService orderService = new OrderService(memberValidatorHandler, productValidatorHandler);
    OrderValidatorHandler orderValidatorHandler = new OrderValidatorHandler(orderService.getOrderList());

    ShippingService shippingService = new ShippingService(memberValidatorHandler, orderValidatorHandler);

    BoardChooseHandler boardChooseHandler = new BoardChooseHandler();

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
            boardChooseHandler.chooseBoard();
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

  private void printCommandHistory(Iterator<String> iterator) {

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
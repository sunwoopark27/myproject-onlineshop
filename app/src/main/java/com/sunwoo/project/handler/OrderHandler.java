package com.sunwoo.project.handler;

import java.sql.Date;
import com.sunwoo.project.domain.Order;
import com.sunwoo.util.Prompt;

public class OrderHandler {

  private MemberHandler memberHandler;
  private ProductHandler productHandler;

  private OrderList orderList = new OrderList();

  public OrderList getOrderList(OrderList orderList) {
    return this.orderList;
  }

  public OrderHandler(MemberHandler memberHandler, ProductHandler productHandler){
    this.memberHandler = memberHandler;
    this.productHandler = productHandler;
  }

  public void add() {

    System.out.println("[주문 등록]");

    Order o = new Order();

    o.setNumber(Prompt.inputInt("주문 번호: "));

    o.setMemberId(memberHandler.inputMemberId()); 
    if(o.getMemberId() == null) {
      System.out.println("주문 등록을 취소합니다.");
      System.out.println();
      return;
    }

    o.setProducts(productHandler.inputProducts("상품명(enter(완료)): "));

    o.setRequest(Prompt.inputString("요청사항: "));
    o.setRegisteredDate(new Date(System.currentTimeMillis()));

    orderList.add(o);

    System.out.println();
  }

  public void list() {
    System.out.println("[주문 목록]");

    Order[] orders = orderList.toArray();
    for(Order o : orders) {

      System.out.printf("주문 번호: %d 회원 아이디: %s\n주문한 상품: %s\n총 가격: %d원\n주문 날짜: %s 요청사항: %s\n"
          , o.getNumber(), o.getMemberId(), o.getProducts(), o.getTotalPrice(), o.getRegisteredDate(), o.getRequest());
      System.out.println("----------------------------------------------------------");

    }

    System.out.println();
  }


  public void detail() {
    System.out.println("[주문 상세보기]");

    Order order = orderList.get(Prompt.inputInt("번호? "));

    if (order == null) {
      System.out.println("해당 번호의 주문이 없습니다.");
      System.out.println();
    }else {
      System.out.printf("회원 아이디: %s\n", order.getMemberId());
      System.out.printf("주문한 상품: %s\n", order.getProducts());
      System.out.printf("주문 날짜: %s\n", order.getRegisteredDate());
      System.out.printf("요청사항: %s\n", order.getRequest());
      System.out.printf("총 가격: %d원\n", order.getTotalPrice());
      System.out.println("-------------------------------------------------------------");
      return;

    }
  }

  public void update() {
    System.out.println("[주문 수정하기]");

    Order order = orderList.get(Prompt.inputInt("번호? "));
    if(order == null) {

      System.out.println("해당 번호의 주문이 없습니다.");
      System.out.println();

    }else {

      String products = productHandler.inputProducts(String.format("주문할 상품(%s)?(완료: 빈문자열)",order.getProducts()));

      String request = Prompt.inputString(String.format("요청사항(%s)? ",order.getRequest()));

      String userChoice = Prompt.inputString("정말 수정하시겠습니까?(y/N) ");
      if(userChoice.equalsIgnoreCase("y")) {
        order.setProducts(products);
        order.setRequest(request);
        order.setRegisteredDate(new Date(System.currentTimeMillis()));
        System.out.println("주문 수정이 완료되었습니다.");
        System.out.println();
      }else {
        System.out.println("주문 수정을 취소합니다.");
        System.out.println();
        return;
      }
    }
  }

  public void delete() {
    System.out.println("[주문 삭제]");

    int no = Prompt.inputInt("번호? ");
    Order order = orderList.get(no);
    if(order == null) {
      System.out.println("해당 번호의 주문이 없습니다.");
      System.out.println();

    }else {
      String userChoice = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");

      if(userChoice.equalsIgnoreCase("y")) {
        orderList.delete(no);
        System.out.println("주문 삭제를 완료하였습니다.");
        System.out.println();
      }else {
        orderList.delete(no);
        System.out.println("주문 삭제를 취소하였습니다.");
        System.out.println();
        return;
      }
    }
  }

  int inputOrderNumber(String promptTitle) {
    int number = -1;
    while(true) {
      number = Prompt.inputInt(promptTitle);
      if(number == -1) {
        return -1;
      }else if(orderList.exist(number)) {
        return number;
      }
      System.out.println("잘못된 주문 번호 입니다.");
    }
  }

}
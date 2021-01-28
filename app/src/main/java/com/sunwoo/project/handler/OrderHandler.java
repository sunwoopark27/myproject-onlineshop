package com.sunwoo.project.handler;

import java.sql.Date;
import com.sunwoo.project.domain.Order;
import com.sunwoo.util.Prompt;

public class OrderHandler {

  //주문
  static final int LENGTH = 100;

  Order[] orders = new Order[LENGTH];

  int size = 0;

  MemberHandler memberList;
  ProductHandler productList;

  public OrderHandler(MemberHandler memberHandler, ProductHandler productHandler){
    this.memberList = memberHandler;
    this.productList = productHandler;
  }

  public void add() {

    System.out.println("[주문 등록]");

    Order o = new Order();

    o.number = Prompt.promptInt("주문 번호: ");
    while(true) {
      String id = Prompt.promptString("회원 아이디(enter(취소)): ");
      if(id.equals("")) {
        System.out.println("주문 등록을 취소합니다.");
        System.out.println();
        return;
      }
      if(this.memberList.exist(id)) {
        o.memberId = id;
        break;
      }
      System.out.println("잘못된 회원 번호입니다.");
    }

    o.products = "";
    while(true) {
      String name = Prompt.promptString("상품명(enter(완료)): ");
      if(name.isEmpty()) {
        break;
      }
      if(this.productList.exist(name)) {
        if(o.products.length() != 0) {
          o.products += ", ";
        }
        o.products += name;
      }else {
        System.out.println("잘못된 상품명입니다.");
      }
    }
    o.request = Prompt.promptString("요청사항: ");
    o.registeredDate = new Date(System.currentTimeMillis());

    this.orders[this.size++] = o;

    System.out.println();
  }

  public void list() {
    System.out.println("[주문 목록]");

    for(int i = 0; i < this.size; i++) {
      Order o = this.orders[i]; 

      System.out.printf("주문 번호: %d 회원 아이디: %s\n주문한 상품: %s\n총 가격: %d원\n주문 날짜: %s 요청사항: %s\n"
          , o.number, o.memberId, o.products, o.totalPrice, o.registeredDate, o.request);
      System.out.println("----------------------------------------------------------");
    }

    System.out.println();
  }

  boolean exist(int number){
    for(int i = 0; i < this.size; i++) {
      if(number == this.orders[i].number) {
        return true;
      }
    }
    return false;
  }

  public void detail() {
    System.out.println("[주문 상세보기]");

    Order order = findByNo(Prompt.promptInt("번호? "));

    if (order == null) {
      System.out.println("해당 번호의 주문이 없습니다.");
      System.out.println();
    }else {
      System.out.printf("회원 아이디: %s\n", order.memberId);
      System.out.printf("주문한 상품: %s\n", order.products);
      System.out.printf("주문 날짜: %s\n", order.registeredDate);
      System.out.printf("요청사항: %s\n", order.request);
      System.out.printf("총 가격: %d원\n", order.totalPrice);
      System.out.println("-------------------------------------------------------------");
      return;

    }
  }

  public void update() {
    System.out.println("[주문 수정하기]");

    Order order = findByNo(Prompt.promptInt("번호? "));
    if(order == null) {

      System.out.println("해당 번호의 주문이 없습니다.");
      System.out.println();

    }else {

      String products = "";
      while(true) {
        String name = Prompt.promptString(String.format("주문할 상품(%s)?(완료: 빈문자열)",order.products));
        if(name.isEmpty()) {
          break;
        }
        if(this.productList.exist(name)) {
          if(products.length() != 0) {
            products += ", ";
          }
          products += name;
          break;
        }else {
          System.out.println("잘못된 상품명입니다.");
        }
      }

      String request = Prompt.promptString(String.format("요청사항(%s)? ",order.request));

      String userChoice = Prompt.promptString("정말 수정하시겠습니까?(y/N) ");
      if(userChoice.equalsIgnoreCase("y")) {
        order.products = products;
        order.request = request;
        order.registeredDate = new Date(System.currentTimeMillis());
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

    int index = indexOf(Prompt.promptInt("번호? "));
    if(index == -1) {
      System.out.println("해당 번호의 주문이 없습니다.");
      System.out.println();

    }else {
      String userChoice = Prompt.promptString("정말 삭제하시겠습니까?(y/N) ");

      if(userChoice.equalsIgnoreCase("y")) {

        for(int x = index + 1; x < this.size; x++) {

          orders[x - 1] = orders[x];
        }
        this.orders[--this.size] = null;
        System.out.println("주문 삭제가 완료되었습니다.");
        System.out.println();
        return;
      }else {

        System.out.println("주문 삭제를 취소하였습니다.");
        System.out.println();
        return;
      }
    }
  }


  int indexOf(int orderNo) {
    for(int i = 0; i < this.size; i++) {
      Order order = this.orders[i];
      if(orderNo == order.number) {
        return i;
      }
    }
    return -1;
  }

  Order findByNo(int orderNo) {
    int i = indexOf(orderNo);
    if(i == -1) {
      return null;
    }else {
      return this.orders[i];
    }
  }
}
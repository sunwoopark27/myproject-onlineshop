package com.sunwoo.project.handler;

import java.sql.Date;
import com.sunwoo.project.domain.Order;
import com.sunwoo.util.Prompt;

public class OrderHandler {

  MemberList memberList;
  ProductList productList;

  public OrderList orderList = new OrderList();

  public OrderHandler(MemberList memberList, ProductList productList){
    this.memberList = memberList;
    this.productList = productList;
  }

  public void add() {

    System.out.println("[주문 등록]");

    Order o = new Order();

    o.number = Prompt.inputInt("주문 번호: ");

    o.memberId = inputMemberId(); 
    if(o.memberId == null) {
      System.out.println("주문 등록을 취소합니다.");
      System.out.println();
      return;
    }

    o.products = inputProducts("상품명(enter(완료)): ");

    o.request = Prompt.inputString("요청사항: ");
    o.registeredDate = new Date(System.currentTimeMillis());

    orderList.add(o);

    System.out.println();
  }

  public void list() {
    System.out.println("[주문 목록]");

    Order[] orders = orderList.toArray();
    for(Order o : orders) {

      System.out.printf("주문 번호: %d 회원 아이디: %s\n주문한 상품: %s\n총 가격: %d원\n주문 날짜: %s 요청사항: %s\n"
          , o.number, o.memberId, o.products, o.totalPrice, o.registeredDate, o.request);
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

    Order order = orderList.get(Prompt.inputInt("번호? "));
    if(order == null) {

      System.out.println("해당 번호의 주문이 없습니다.");
      System.out.println();

    }else {

      String products = inputProducts(String.format("주문할 상품(%s)?(완료: 빈문자열)",order.products));

      String request = Prompt.inputString(String.format("요청사항(%s)? ",order.request));

      String userChoice = Prompt.inputString("정말 수정하시겠습니까?(y/N) ");
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


  String inputMemberId(){
    while(true) {
      String id = Prompt.inputString("회원 아이디(enter(취소)): ");
      if(id.equals("")) {
        return null;
      }
      if(this.memberList.exist(id)) {
        return id;
      }
      System.out.println("등록된 회원이 아닙니다.");
    }
  }

  String inputProducts(String promptTitle) {
    String products = "";
    while(true) {
      String name = Prompt.inputString(promptTitle);
      if(name.isEmpty()) {
        return products;
      }
      if(this.productList.exist(name)) {
        if(products.length() != 0) {
          products += ", ";
        }
        products += name;
      }else {
        System.out.println("등록된 상품이 아닙니다.");
      }
    }
  }

}
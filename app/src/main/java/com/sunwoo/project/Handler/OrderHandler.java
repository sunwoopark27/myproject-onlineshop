package com.sunwoo.project.Handler;

import java.sql.Date;
import com.sunwoo.project.domain.Order;
import com.sunwoo.util.Prompt;

public class OrderHandler {

  //주문
  static final int LENGTH = 100;

  Order[] orders = new Order[LENGTH];

  int size = 0;

  public void add(MemberHandler memberList, ProductHandler productList) {

    System.out.println("[주문 등록]");

    Order o = new Order();

    o.orderNumber = Prompt.promptInt("주문 번호: ");
    while(true) {
      String id = Prompt.promptString("회원 아이디(enter(취소)): ");
      if(id.equals("")) {
        System.out.println("주문 등록을 취소합니다.");
        System.out.println();
        return;
      }
      if(memberList.exist(id)) {
        o.memberId = id;
        break;
      }
      System.out.println("잘못된 회원 번호입니다.");
    }

    o.product = "";
    while(true) {
      String name = Prompt.promptString("상품명(enter(완료)): ");
      if(name.isEmpty()) {
        break;
      }
      if(productList.exist(name)) {
        if(o.product.length() != 0) {
          o.product += ", ";
        }
        o.product += name;
      }else {
        System.out.println("잘못된 상품명입니다.");
      }
    }
    o.request = Prompt.promptString("요청사항: ");
    o.orderDate = new Date(System.currentTimeMillis());

    this.orders[this.size++] = o;

    System.out.println();
  }

  public void list() {
    System.out.println("[주문 목록]");

    for(int i = 0; i < this.size; i++) {
      Order o = this.orders[i];

      System.out.printf("주문 번호: %d 회원 아이디: %s 상품명: %s\n주문 날짜: %s 요청사항: %s\n"
          , o.orderNumber, o.memberId, o.product, o.orderDate, o.request);
      System.out.println("----------------------------------------------------------");
    }

    System.out.println();
  }

}

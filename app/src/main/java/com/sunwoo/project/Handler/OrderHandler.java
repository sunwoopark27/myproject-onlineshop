package com.sunwoo.project.Handler;

import java.sql.Date;
import com.sunwoo.util.Prompt;

public class OrderHandler {

  static class Order{
    int memberNumber;
    int orderNumber;
    String product;  
    Date orderDate;   
    String request;
  }

  //주문
  static final int LENGTH = 100;

  static Order[] orders = new Order[LENGTH];

  static int size = 0;

  public static void add() {

    System.out.println("[주문 등록]");

    Order o = new Order();

    o.memberNumber = Prompt.promptInt("고객 번호: ");
    o.orderNumber = Prompt.promptInt("주문 번호: ");
    o.product = Prompt.promptString("상품명: ");
    o.orderDate = Prompt.promptDate("주문 날짜: ");
    o.request = Prompt.promptString("요청사항: ");

    orders[size++] = o;

    System.out.println();
  }

  public static void list() {
    System.out.println("[주문 목록]");

    for(int i = 0; i < size; i++) {
      Order o = orders[i];

      System.out.printf("%d, %d, %s, %s, %s\n", o.memberNumber, o.orderNumber, o.product, o.orderDate, o.request);
    }

    System.out.println();
  }
}

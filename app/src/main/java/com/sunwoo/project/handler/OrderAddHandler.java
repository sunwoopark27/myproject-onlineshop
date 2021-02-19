package com.sunwoo.project.handler;

import java.sql.Date;
import java.util.List;
import com.sunwoo.project.domain.Order;
import com.sunwoo.util.Prompt;

public class OrderAddHandler extends AbstractOrderHandler {

  private AbstractMemberHandler memberHandler;
  private ProductHandler productHandler;

  public OrderAddHandler(List<Order> orderList, AbstractMemberHandler memberHandler, ProductHandler productHandler){
    super(orderList);
    this.memberHandler = memberHandler;
    this.productHandler = productHandler;
  }

  @Override
  public void service() {

    System.out.println("[메인 > 주문 > 등록]");

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


}
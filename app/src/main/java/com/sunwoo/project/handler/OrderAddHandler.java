package com.sunwoo.project.handler;

import java.sql.Date;
import java.util.List;
import com.sunwoo.project.domain.Order;
import com.sunwoo.util.Prompt;

public class OrderAddHandler extends AbstractOrderHandler {

  private MemberValidatorHandler memberValidatorHandler;
  private ProductValidatorHandler productValidatorHandler;

  public OrderAddHandler(MemberValidatorHandler memberValidatorHandler, ProductValidatorHandler productValidatorHandler, List<Order> orderList){
    super(orderList);
    this.memberValidatorHandler = memberValidatorHandler;
    this.productValidatorHandler = productValidatorHandler;
  }

  @Override
  public void service() {

    System.out.println("[메인 > 주문 > 등록]");

    Order o = new Order();

    o.setNumber(Prompt.inputInt("주문 번호: "));

    o.setMemberId(memberValidatorHandler.inputMember("회원 아이디(enter(취소)): ")); 
    if(o.getMemberId() == null) {
      System.out.println("주문 등록을 취소합니다.");
      System.out.println();
      return;
    }

    o.setProducts(productValidatorHandler.inputProducts("상품명(enter(완료)): "));

    o.setRequest(Prompt.inputString("요청사항: "));
    o.setRegisteredDate(new Date(System.currentTimeMillis()));

    orderList.add(o);

    System.out.println();
  }


}
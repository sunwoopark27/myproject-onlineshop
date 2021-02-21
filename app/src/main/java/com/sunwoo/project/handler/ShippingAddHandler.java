package com.sunwoo.project.handler;

import java.util.List;
import com.sunwoo.project.domain.Shipping;
import com.sunwoo.util.Prompt;

public class ShippingAddHandler extends AbstractShippingHandler {

  MemberValidatorHandler memberValidatorHandler;
  OrderValidatorHandler orderValidatorHandler;

  public ShippingAddHandler(MemberValidatorHandler memberValidatorHandler, OrderValidatorHandler orderValidatorHandler, List<Shipping> shippingList) {
    super(shippingList);
    this.memberValidatorHandler = memberValidatorHandler;
    this.orderValidatorHandler = orderValidatorHandler;
  }

  @Override
  public void service() {
    System.out.println("[메인 > 배송 > 등록]");

    Shipping s = new Shipping();

    s.setNumber(Prompt.inputInt("배송 번호: "));

    s.setMemberId(memberValidatorHandler.inputMember("회원 아이디(enter(취소)): "));
    if(s.getMemberId() == null) {
      System.out.println("배송 등록을 취소합니다.");
      System.out.println();
      return;
    }

    s.setOrderNumber(orderValidatorHandler.inputOrderNumber("주문 번호(-1(취소)): "));
    if(s.getOrderNumber() == -1) {
      System.out.println("배송 등록을 취소합니다.");
      System.out.println();
      return;
    }

    s.setTrackingNumber(Prompt.inputInt("운송장 번호: "));
    s.setStatus(Prompt.inputInt("배송상태\n" + "0: 배송준비중\n" + "1: 배송중\n" + "2: 배송완료\n" + "> "));
    s.setManager(Prompt.inputString("배송 담당자: "));

    shippingList.add(s);

    System.out.println();
  }
}

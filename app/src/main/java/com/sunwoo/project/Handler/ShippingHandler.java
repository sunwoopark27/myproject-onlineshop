package com.sunwoo.project.Handler;

import com.sunwoo.project.domain.Shipping;
import com.sunwoo.util.Prompt;

public class ShippingHandler {

  //배송
  static final int LENGTH = 100;

  Shipping[] shippings = new Shipping[LENGTH];
  int size = 0;

  public void add(MemberHandler memberList) {
    System.out.println("[배송 등록]");

    Shipping s = new Shipping();

    while(true) {
      String id = Prompt.promptString("회원 아이디(enter(취소)): ");
      if(id.length() == 0) {
        System.out.println("배송 등록을 취소합니다.");
        return;
      }else if(memberList.exist(id)) {
        s.memberId = id;
        break;
      }
      System.out.println("잘못된 아이디 입니다.");
    }

    s.orderNumber = Prompt.promptInt("주문 번호: ");
    s.trackingNumber = Prompt.promptInt("운송장 번호: ");
    s.shippingStatus = Prompt.promptInt("배송상태\n" + "0: 배송준비중\n" + "1: 배송중\n" + "2: 배송완료\n" + "> ");
    s.shippingManager = Prompt.promptString("배송 담당자: ");

    this.shippings[this.size++] = s;

    System.out.println();
  }

  public void list() {
    System.out.println("[배송 목록]");

    for (int i = 0; i < this.size; i++) {

      Shipping s = this.shippings[i];

      String statusLabel = null;

      switch (s.shippingStatus) {
        case 0:
          statusLabel = "배송 준비중";
          break;
        case 1: 
          statusLabel = "배송중";
          break;
        case 2:
          statusLabel = "배송 완료";
          break;
      }

      System.out.printf("고객 아이디: %s 주문 번호: %d\n운송장 번호: %d 배송상태: %s\n담당자: %s\n"
          , s.memberId, s.orderNumber, s.trackingNumber, statusLabel, s.shippingManager);
      System.out.println("-----------------------------------------");
    }
    System.out.println();
  }
}

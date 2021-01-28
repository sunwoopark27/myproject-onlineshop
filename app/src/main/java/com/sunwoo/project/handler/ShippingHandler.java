package com.sunwoo.project.handler;

import com.sunwoo.project.domain.Shipping;
import com.sunwoo.util.Prompt;

public class ShippingHandler {

  //배송
  static final int LENGTH = 100;

  Shipping[] shippings = new Shipping[LENGTH];
  int size = 0;

  MemberHandler memberList;
  OrderHandler orderList;

  public ShippingHandler(MemberHandler memberHandler, OrderHandler orderHandler) {
    this.memberList = memberHandler;
    this.orderList = orderHandler;
  }

  public void add() {
    System.out.println("[배송 등록]");

    Shipping s = new Shipping();

    s.number = Prompt.promptInt("배송 번호: ");

    while(true) {
      String id = Prompt.promptString("회원 아이디(enter(취소)): ");
      if(id.length() == 0) {
        System.out.println("배송 등록을 취소합니다.");
        return;
      }else if(this.memberList.exist(id)) {
        s.memberId = id;
        break;
      }
      System.out.println("잘못된 아이디 입니다.");
    }

    int number = -1;
    while(true) {
      number = Prompt.promptInt("주문 번호(-1(취소)): ");
      if(number == -1) {
        System.out.println("배송 등록을 취소합니다.");
        System.out.println();
        return;
      }else if(this.orderList.exist(number)) {
        s.orderNumber = number;
        break;
      }
      System.out.println("잘못된 주문 번호 입니다.");
    }
    s.trackingNumber = Prompt.promptInt("운송장 번호: ");
    s.status = Prompt.promptInt("배송상태\n" + "0: 배송준비중\n" + "1: 배송중\n" + "2: 배송완료\n" + "> ");
    s.manager = Prompt.promptString("배송 담당자: ");

    this.shippings[this.size++] = s;

    System.out.println();
  }

  public void list() {
    System.out.println("[배송 목록]");

    for (int i = 0; i < this.size; i++) {

      Shipping s = this.shippings[i];

      String statusLabel = null;

      switch (s.status) {
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

      System.out.printf("배송 번호: %d 고객 아이디: %s 주문 번호: %d\n운송장 번호: %d 배송상태: %s\n담당자: %s\n"
          ,s.number, s.memberId, s.orderNumber, s.trackingNumber, statusLabel, s.manager);
      System.out.println("-----------------------------------------");
    }
    System.out.println();
  }
  public void detail() {
    System.out.println("[배송 상세보기]");

    Shipping shipping = findByNo(Prompt.promptInt("번호? "));

    if (shipping == null) {
      System.out.println("해당 번호의 배송이 없습니다.");
      System.out.println();
    }else {
      System.out.printf("고객 아이디: %s ", shipping.memberId);
      System.out.printf("주문 번호: %s\n", shipping.orderNumber);
      System.out.printf("운송장 번호: %s\n", shipping.trackingNumber);

      String statusLabel = null;

      switch (shipping.status) {
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

      System.out.printf("배송 상태: %s ", statusLabel);
      System.out.printf("담당자: %s\n", shipping.manager);
      System.out.println("-------------------------------------------------------------");
      return;

    }
  }

  public void update() {
    System.out.println("[배송 정보 수정]");

    Shipping shipping = findByNo(Prompt.promptInt("번호? "));
    if(shipping == null) {

      System.out.println("해당 번호의 배송이 없습니다.");
      System.out.println();

    }else {

      String memberId = "";
      while(true) {
        memberId = Prompt.promptString(String.format("고객아이디(%s)(enter(취소))? ",shipping.memberId));
        if(memberId.length() == 0) {
          System.out.println("배송 등록을 취소합니다.");
          System.out.println();
          return;
        }else if(this.memberList.exist(memberId)) {
          break;
        }
        System.out.println("잘못된 아이디 입니다.");
      }

      int orderNumber = -1;
      while(true) {
        orderNumber = Prompt.promptInt(String.format("주문 번호(%s)(-1(취소))? ",shipping.orderNumber));
        if(orderNumber == -1) {
          System.out.println("배송 등록을 취소합니다.");
          System.out.println();
          return;
        }else if(this.orderList.exist(orderNumber)) {
          shipping.orderNumber = orderNumber;
          break;
        }
        System.out.println("잘못된 주문 번호 입니다.");
      }

      int trackingNumber = Prompt.promptInt(String.format("운송장번호(%s)? ",shipping.trackingNumber));

      int status =  Prompt.promptInt(String.format("0: 배송준비중\n"+ "1: 배송중\n" + "2: 배송완료\n" + "배송상태(%s)? ",shipping.trackingNumber));

      String manager = Prompt.promptString(String.format("담당자(%s)? ",shipping.manager));


      String userChoice = Prompt.promptString("정말 수정하시겠습니까?(y/N) ");
      if(userChoice.equalsIgnoreCase("y")) {
        shipping.memberId = memberId;
        shipping.orderNumber = orderNumber;
        shipping.trackingNumber = trackingNumber;
        shipping.status = status;
        shipping.manager = manager;
        System.out.println("게시물 수정이 완료되었습니다.");
        System.out.println();
      }else {
        System.out.println("게시물 수정을 취소합니다.");
        System.out.println();
        return;
      }
    }
  }


  public void delete() {
    System.out.println("[배송 삭제]");

    int index = indexOf(Prompt.promptInt("번호? "));
    if(index == -1) {
      System.out.println("해당 번호의 배송이 없습니다.");
      System.out.println();

    }else {
      String userChoice = Prompt.promptString("정말 삭제하시겠습니까?(y/N) ");

      if(userChoice.equalsIgnoreCase("y")) {

        for(int x = index + 1; x < this.size; x++) {

          shippings[x - 1] = shippings[x];
        }
        this.shippings[--this.size] = null;
        System.out.println("배송 삭제가 완료되었습니다.");
        System.out.println();
        return;
      }else {

        System.out.println("배송 삭제를 취소하였습니다.");
        System.out.println();
        return;
      }
    }
  }


  int indexOf(int shippingNo) {
    for(int i = 0; i < this.size; i++) {
      Shipping shipping = this.shippings[i];
      if(shippingNo == shipping.number) {
        return i;
      }
    }
    return -1;
  }

  Shipping findByNo(int shippingNo) {
    int i = indexOf(shippingNo);
    if(i == -1) {
      return null;
    }else {
      return this.shippings[i];
    }
  }
}

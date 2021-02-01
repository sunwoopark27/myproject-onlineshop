package com.sunwoo.project.handler;

import com.sunwoo.project.domain.Shipping;
import com.sunwoo.util.Prompt;

public class ShippingHandler {

  MemberList memberList;
  OrderList orderList;

  public ShippingList shippingList = new ShippingList();

  public ShippingHandler(MemberList memberList, OrderList orderList) {
    this.memberList = memberList;
    this.orderList = orderList;
  }

  public void add() {
    System.out.println("[배송 등록]");

    Shipping s = new Shipping();

    s.number = Prompt.inputInt("배송 번호: ");

    s.memberId = inputMember("회원 아이디(enter(취소)): ");
    if(s.memberId == null) {
      System.out.println("배송 등록을 취소합니다.");
      System.out.println();
      return;
    }

    s.orderNumber = inputOrderNumber("주문 번호(-1(취소)): ");
    if(s.orderNumber == -1) {
      System.out.println("배송 등록을 취소합니다.");
      System.out.println();
      return;
    }

    s.trackingNumber = Prompt.inputInt("운송장 번호: ");
    s.status = Prompt.inputInt("배송상태\n" + "0: 배송준비중\n" + "1: 배송중\n" + "2: 배송완료\n" + "> ");
    s.manager = Prompt.inputString("배송 담당자: ");

    shippingList.add(s);

    System.out.println();
  }

  public void list() {
    System.out.println("[배송 목록]");

    Shipping[] shippings = shippingList.toArray();
    for(Shipping s : shippings) {

      String statusLabel = getStatusLabel(s.status);

      System.out.printf("배송 번호: %d 고객 아이디: %s 주문 번호: %d\n운송장 번호: %d 배송상태: %s\n담당자: %s\n"
          ,s.number, s.memberId, s.orderNumber, s.trackingNumber, statusLabel, s.manager);
      System.out.println("-----------------------------------------");

    }
    System.out.println();
  }

  public void detail() {
    System.out.println("[배송 상세보기]");

    Shipping shipping = shippingList.get(Prompt.inputInt("번호? "));

    if (shipping == null) {
      System.out.println("해당 번호의 배송이 없습니다.");
      System.out.println();
    }else {
      System.out.printf("고객 아이디: %s ", shipping.memberId);
      System.out.printf("주문 번호: %s\n", shipping.orderNumber);
      System.out.printf("운송장 번호: %s\n", shipping.trackingNumber);

      String statusLabel = getStatusLabel(shipping.status);

      System.out.printf("배송 상태: %s ", statusLabel);
      System.out.printf("담당자: %s\n", shipping.manager);
      System.out.println("-------------------------------------------------------------");
      return;

    }
  }

  public void update() {
    System.out.println("[배송 정보 수정]");

    Shipping shipping = shippingList.get(Prompt.inputInt("번호? "));
    if(shipping == null) {

      System.out.println("해당 번호의 배송이 없습니다.");
      System.out.println();

    }else {

      String memberId = inputMember(String.format("고객아이디(%s)(enter(취소))? ",shipping.memberId));
      if(memberId == null) {
        System.out.println("배송 등록을 취소합니다.");
        System.out.println();
      }

      int orderNumber = inputOrderNumber(String.format("주문 번호(%s)(-1(취소))? ",shipping.orderNumber));
      if(orderNumber == -1) {
        System.out.println("배송 등록을 취소합니다.");
        System.out.println();
        return;
      }

      int trackingNumber = Prompt.inputInt(String.format("운송장번호(%s)? ",shipping.trackingNumber));

      int status =  Prompt.inputInt(String.format
          ("0: 배송준비중\n"+ "1: 배송중\n" + "2: 배송완료\n" + "배송상태(%s)? ",getStatusLabel(shipping.status)));

      String manager = Prompt.inputString(String.format("담당자(%s)? ",shipping.manager));


      String userChoice = Prompt.inputString("정말 수정하시겠습니까?(y/N) ");
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

    int no = Prompt.inputInt("번호? ");
    Shipping shipping = shippingList.get(no);
    if(shipping == null) {
      System.out.println("해당 번호의 배송이 없습니다.");
      System.out.println();

    }else {
      String userChoice = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");

      if(userChoice.equalsIgnoreCase("y")) {

        shippingList.delete(no);
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

  String getStatusLabel(int status) {
    switch (status) {
      case 1:
        return "배송중";
      case 2: 
        return "배송 완료";
      default:
        return "배송 준비중";
    }
  }

  String inputMember(String promptTitle) {
    while(true) {
      String id = Prompt.inputString(promptTitle);
      if(id.length() == 0) {
        return null;
      }else if(this.memberList.exist(id)) {
        return id;
      }
      System.out.println("잘못된 아이디 입니다.");
    }
  }

  int inputOrderNumber(String promptTitle) {
    int number = -1;
    while(true) {
      number = Prompt.inputInt(promptTitle);
      if(number == -1) {
        return -1;
      }else if(this.orderList.exist(number)) {
        return number;
      }
      System.out.println("잘못된 주문 번호 입니다.");
    }
  }


}

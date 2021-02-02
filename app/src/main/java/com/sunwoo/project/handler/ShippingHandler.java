package com.sunwoo.project.handler;

import com.sunwoo.project.domain.Shipping;
import com.sunwoo.util.Prompt;

public class ShippingHandler {

  private MemberHandler memberHandler;
  private OrderHandler orderHandler;

  public ShippingList shippingList = new ShippingList();

  public ShippingHandler(MemberHandler memberHandler, OrderHandler orderHandler) {
    this.memberHandler = memberHandler;
    this.orderHandler = orderHandler;
  }

  public void add() {
    System.out.println("[배송 등록]");

    Shipping s = new Shipping();

    s.setNumber(Prompt.inputInt("배송 번호: "));

    s.setMemberId(memberHandler.inputMember("회원 아이디(enter(취소)): "));
    if(s.getMemberId() == null) {
      System.out.println("배송 등록을 취소합니다.");
      System.out.println();
      return;
    }

    s.setOrderNumber(orderHandler.inputOrderNumber("주문 번호(-1(취소)): "));
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

  public void list() {
    System.out.println("[배송 목록]");

    Shipping[] shippings = shippingList.toArray();
    for(Shipping s : shippings) {

      String statusLabel = getStatusLabel(s.getStatus());

      System.out.printf("배송 번호: %d 고객 아이디: %s 주문 번호: %d\n운송장 번호: %d 배송상태: %s\n담당자: %s\n"
          ,s.getNumber(), s.getMemberId(), s.getOrderNumber(), s.getTrackingNumber(), statusLabel, s.getManager());
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
      System.out.printf("고객 아이디: %s ", shipping.getMemberId());
      System.out.printf("주문 번호: %s\n", shipping.getOrderNumber());
      System.out.printf("운송장 번호: %s\n", shipping.getTrackingNumber());

      String statusLabel = getStatusLabel(shipping.getStatus());

      System.out.printf("배송 상태: %s ", statusLabel);
      System.out.printf("담당자: %s\n", shipping.getManager());
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

      String memberId = memberHandler.inputMember(String.format("고객아이디(%s)(enter(취소))? ",shipping.getMemberId()));
      if(memberId == null) {
        System.out.println("배송 등록을 취소합니다.");
        System.out.println();
      }

      int orderNumber = orderHandler.inputOrderNumber(String.format("주문 번호(%s)(-1(취소))? ",shipping.getOrderNumber()));
      if(orderNumber == -1) {
        System.out.println("배송 등록을 취소합니다.");
        System.out.println();
        return;
      }

      int trackingNumber = Prompt.inputInt(String.format("운송장번호(%s)? ",shipping.getTrackingNumber()));

      int status =  Prompt.inputInt(String.format
          ("0: 배송준비중\n"+ "1: 배송중\n" + "2: 배송완료\n" + "배송상태(%s)? ",getStatusLabel(shipping.getStatus())));

      String manager = Prompt.inputString(String.format("담당자(%s)? ",shipping.getManager()));


      String userChoice = Prompt.inputString("정말 수정하시겠습니까?(y/N) ");
      if(userChoice.equalsIgnoreCase("y")) {
        shipping.setMemberId(memberId);
        shipping.setOrderNumber(orderNumber);
        shipping.setTrackingNumber(trackingNumber);
        shipping.setStatus(status);
        shipping.setManager(manager);
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





}

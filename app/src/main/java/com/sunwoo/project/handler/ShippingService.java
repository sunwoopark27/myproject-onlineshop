package com.sunwoo.project.handler;

import java.util.ArrayList;
import com.sunwoo.project.domain.Shipping;

public class ShippingService {

  ArrayList<Shipping> shippingList = new ArrayList<>();
  private MemberValidatorHandler memberValidatorHandler;
  private OrderValidatorHandler orderValidatorHandler;

  public ShippingService(MemberValidatorHandler memberValidatorHandler, OrderValidatorHandler orderValidatorHandler) {
    this.memberValidatorHandler = memberValidatorHandler;
    this.orderValidatorHandler = orderValidatorHandler;
  }

  ShippingAddHandler shippingAddHandler = new ShippingAddHandler(memberValidatorHandler, orderValidatorHandler, shippingList);
  ShippingListHandler shippingListHandler = new ShippingListHandler(shippingList);
  ShippingDetailHandler shippingDetailHandler = new ShippingDetailHandler(shippingList);
  ShippingUpdateHandler shippingUpdateHandler = new ShippingUpdateHandler(memberValidatorHandler, orderValidatorHandler, shippingList);
  ShippingDeleteHandler shippingDeleteHandler = new ShippingDeleteHandler(shippingList);


  public void menu() throws CloneNotSupportedException {

    loop:
      while(true) {
        System.out.println("[메인 > 배송]");
        System.out.println("1. 등록");
        System.out.println("2. 목록");
        System.out.println("3. 상세 보기");
        System.out.println("4. 수정");
        System.out.println("5. 삭제");
        System.out.println("0. 이전 메뉴");
        System.out.println();

        String command = com.sunwoo.util.Prompt.inputString("명령> ");
        System.out.println();
        try {
          switch(command) {
            case "1" :
              shippingAddHandler.service();
              break;
            case "2" :
              shippingListHandler.service();
              break;
            case "3" :
              shippingDetailHandler.service();
              break;
            case "4" :
              shippingUpdateHandler.service();
              break;
            case "5" :
              shippingDeleteHandler.service();
              break;
            case "0" :
              System.out.println("메인으로 돌아갑니다.");
              System.out.println();
              break loop;
            default :
              System.out.println("잘못된 메뉴 번호 입니다.");
              System.out.println();

          }
        }catch(Exception e) {
          System.out.println("------------------------------------------------------------------------------");
          System.out.printf("명령어 실행 중 오류 발생: %s - %s\n", e.getClass().getName(), e.getMessage());
          System.out.println("------------------------------------------------------------------------------");
        }
        System.out.println();
      }
  }
}

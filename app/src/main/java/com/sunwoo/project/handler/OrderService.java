package com.sunwoo.project.handler;

import java.util.ArrayList;
import com.sunwoo.project.domain.Member;
import com.sunwoo.project.domain.Order;

public  class OrderService {

  private ArrayList<Order> orderList = new ArrayList<>();

  //멤버 리스트 가져와야돼!
  // 앱 클래스에서 서비스 이요앻 볼까? 거기에 리스트 있잖아. 

  OrderAddHandler orderAddHandler = new OrderAddHandler(orderList,);
  OrderListHandler orderListHandler = new OrderListHandler();
  OrderDetailHandler orderDetailHandler = new OrderDetailHandler();
  OrderUpdateHandler orderUpdateHandler = new OrderUpdateHandler();
  OrderDeleteHandler orderDeleteHandler = new OrderDeleteHandler();

  public void menu() {

    loop:
      while(true) {
        System.out.println("[메인 > 주문]");
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
              this.add();
              break;
            case "2" :
              this.list();
              break;
            case "3" :
              this.detail();
              break;
            case "4" :
              this.update();
              break;
            case "5" :
              this.delete();
              break;
            case "0" :
              System.out.println("메인으로 돌아갑니다.");
              System.out.println();
              break loop;
            default :
              System.out.println("잘못된 메뉴 번호 입니다.");
              System.out.println();

          }
        }catch(Exception e){
          System.out.println("------------------------------------------------------------------------------");
          System.out.printf("명령어 실행 중 오류 발생: %s - %s\n", e.getClass().getName(), e.getMessage());
          System.out.println("------------------------------------------------------------------------------");
        }
        System.out.println();
      }
  }

}
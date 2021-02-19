package com.sunwoo.project.handler;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import com.sunwoo.project.domain.Order;
import com.sunwoo.util.Prompt;

public class OrderDetailHandler {

  private AbstractMemberHandler memberHandler;
  private ProductHandler productHandler;

  private ArrayList<Order> orderList = new ArrayList<>();

  public OrderDetailHandler(AbstractMemberHandler memberHandler, ProductHandler productHandler){
    this.memberHandler = memberHandler;
    this.productHandler = productHandler;
  }

  public void service() throws CloneNotSupportedException {

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

  public void add() {

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

  public void list() throws CloneNotSupportedException {
    System.out.println("[메인 > 주문 > 목록]");

    Iterator<Order> iterator = orderList.iterator();
    while (iterator.hasNext()) {
      Order o = iterator.next();
      System.out.printf("주문 번호: %d 회원 아이디: %s\n주문 날짜: %s\n"
          , o.getNumber(), o.getMemberId(), o.getRegisteredDate());
      System.out.println("----------------------------------------------------------");

    }

    System.out.println();
  }


  public void detail() {
    System.out.println("[메인 > 주문 > 상세 보기]");

    Order order = findByNo(Prompt.inputInt("번호? "));

    if (order == null) {
      System.out.println("해당 번호의 주문이 없습니다.");
      System.out.println();
    }else {
      System.out.printf("회원 아이디: %s\n", order.getMemberId());
      System.out.printf("주문한 상품: %s\n", order.getProducts());
      System.out.printf("주문 날짜: %s\n", order.getRegisteredDate());
      System.out.printf("요청사항: %s\n", order.getRequest());
      System.out.printf("총 가격: %d원\n", order.getTotalPrice());
      System.out.println("-------------------------------------------------------------");
      return;

    }
  }

  public void update() {
    System.out.println("[메인 > 주문 > 수정]");

    Order order = findByNo(Prompt.inputInt("번호? "));
    if(order == null) {

      System.out.println("해당 번호의 주문이 없습니다.");
      System.out.println();

    }else {

      String products = productHandler.inputProducts(String.format("주문할 상품(%s)?(완료: 빈문자열)",order.getProducts()));

      String request = Prompt.inputString(String.format("요청사항(%s)? ",order.getRequest()));

      String userChoice = Prompt.inputString("정말 수정하시겠습니까?(y/N) ");
      if(userChoice.equalsIgnoreCase("y")) {
        order.setProducts(products);
        order.setRequest(request);
        order.setRegisteredDate(new Date(System.currentTimeMillis()));
        System.out.println("주문 수정이 완료되었습니다.");
        System.out.println();
      }else {
        System.out.println("주문 수정을 취소합니다.");
        System.out.println();
        return;
      }
    }
  }

  public void delete() {
    System.out.println("[메인 > 주문 > 삭제]");

    int no = Prompt.inputInt("번호? ");
    Order order = findByNo(no);
    if(order == null) {
      System.out.println("해당 번호의 주문이 없습니다.");
      System.out.println();

    }else {
      String userChoice = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");

      if(userChoice.equalsIgnoreCase("y")) {
        orderList.remove(order);
        System.out.println("주문 삭제를 완료하였습니다.");
        System.out.println();
      }else {
        orderList.remove(no);
        System.out.println("주문 삭제를 취소하였습니다.");
        System.out.println();
        return;
      }
    }
  }

  int inputOrderNumber(String promptTitle) {
    int number = -1;
    while(true) {
      number = Prompt.inputInt(promptTitle);
      if(number == -1) {
        return -1;
      }else if(findByNo(number) != null) {
        return number;
      }
      System.out.println("잘못된 주문 번호 입니다.");
    }
  }

  private Order findByNo(int orderNo) {
    Order[] list = orderList.toArray(new Order[orderList.size()]);
    for(Order o : list) {
      if(o.getNumber() == orderNo) {
        return o;
      }
    }
    return null;
  }


}
package com.sunwoo.project.handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import com.sunwoo.project.domain.Order;

public  class OrderService {

  private ArrayList<Order> orderList = new ArrayList<>();
  public ArrayList<Order> getOrderList() {
    return orderList;
  }
  private MemberValidatorHandler memberValidatorHandler;
  private ProductValidatorHandler productValidatorHandler;

  public OrderService(MemberValidatorHandler memberValidatorHandler, ProductValidatorHandler productValidatorHandler) {
    this.memberValidatorHandler = memberValidatorHandler;
    this.productValidatorHandler = productValidatorHandler;

    loadOrders();
  }

  public void menu() {

    HashMap<String,Command> commandMap = new HashMap<>();

    commandMap.put("1", new OrderAddHandler(memberValidatorHandler, productValidatorHandler, orderList));
    commandMap.put("2", new OrderListHandler(orderList));
    commandMap.put("3", new OrderDetailHandler(orderList));
    commandMap.put("4", new OrderUpdateHandler(productValidatorHandler, orderList));
    commandMap.put("5", new OrderDeleteHandler(orderList));

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
            case "0" :
              System.out.println("메인으로 돌아갑니다.");
              System.out.println();
              break loop;
            default :
              Command commandHandler = commandMap.get(command);
              if(commandHandler == null) {
                System.out.println("실행할 수 없는 메뉴 번호 입니다.");
              }else {
                commandHandler.service();
              }
          }
        }catch(Exception e){
          System.out.println("------------------------------------------------------------------------------");
          System.out.printf("명령어 실행 중 오류 발생: %s - %s\n", e.getClass().getName(), e.getMessage());
          System.out.println("------------------------------------------------------------------------------");
        }
        System.out.println();
      }
    saveOrders();
  }

  private void loadOrders() {
    try(BufferedReader in = new BufferedReader(new FileReader("orders.csv"))) {

      String csvStr = null;
      while ((csvStr = in.readLine()) != null) {
        try {
          orderList.add(Order.valueOfCsv(csvStr));
        } catch (Exception e) {
          break;
        }
      }
      System.out.println("주문 데이터 로딩!");
    } catch (Exception e) {
      System.out.println("주문 데이터 로딩 중 오류 발생!");
    }
  }

  private void saveOrders() {
    try(BufferedWriter out = new BufferedWriter(new FileWriter("orders.csv"))) {

      for (Order o : orderList) {
        out.write(o.toCsvString());
      }
      System.out.println("주문 데이터 저장");

    } catch (Exception e) {
      System.out.println("주문 데이터 파일로 저장하는 중에 오류 발생!");
    }
  }
}
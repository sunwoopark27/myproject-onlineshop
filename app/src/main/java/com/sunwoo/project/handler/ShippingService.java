package com.sunwoo.project.handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import com.sunwoo.project.domain.Shipping;

public class ShippingService {

  ArrayList<Shipping> shippingList = new ArrayList<>();

  private MemberValidatorHandler memberValidatorHandler;
  private OrderValidatorHandler orderValidatorHandler;

  public ShippingService(MemberValidatorHandler memberValidatorHandler, OrderValidatorHandler orderValidatorHandler) {
    this.memberValidatorHandler = memberValidatorHandler;
    this.orderValidatorHandler = orderValidatorHandler;

    loadShippings();
  }

  public void menu() {

    HashMap<String,Command> commandMap = new HashMap<>();

    commandMap.put("1", new ShippingAddHandler(memberValidatorHandler, orderValidatorHandler, shippingList));
    commandMap.put("2", new ShippingListHandler(shippingList));
    commandMap.put("3", new ShippingDetailHandler(shippingList));
    commandMap.put("4", new ShippingUpdateHandler(memberValidatorHandler, orderValidatorHandler, shippingList));
    commandMap.put("5", new ShippingDeleteHandler(shippingList));

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
        }catch(Exception e) {
          System.out.println("------------------------------------------------------------------------------");
          System.out.printf("명령어 실행 중 오류 발생: %s - %s\n", e.getClass().getName(), e.getMessage());
          System.out.println("------------------------------------------------------------------------------");
        }
        System.out.println();
      }
    saveShippings();
  }

  private void loadShippings() {

    try(BufferedReader in = new BufferedReader(new FileReader("shippings.csv"))) {

      String csvStr = null;
      while ((csvStr = in.readLine()) != null) {
        try {
          shippingList.add(Shipping.valueOfCsv(csvStr));
        } catch (Exception e) {
          break;
        }
      }
      System.out.println("배송 데이터 로딩!");
    } catch (Exception e) {
      System.out.println("배송 데이터 로딩 중 오류 발생!");
    }
  }

  private void saveShippings() {
    try(BufferedWriter out = new BufferedWriter(new FileWriter("shippings.csv"))) {

      for (Shipping s : shippingList) {
        out.write(s.toCsvString() + "\n");
      }
      System.out.println("배송 데이터 저장");

    } catch (Exception e) {
      System.out.println("배송 데이터 파일로 저장하는 중에 오류 발생!");
    }
  }
}

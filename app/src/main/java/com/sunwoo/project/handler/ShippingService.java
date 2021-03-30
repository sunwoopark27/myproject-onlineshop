package com.sunwoo.project.handler;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import com.sunwoo.project.domain.Shipping;

public class ShippingService {

  ArrayList<Shipping> shippingList = new ArrayList<>();
  private MemberValidatorHandler memberValidatorHandler;
  private OrderValidatorHandler orderValidatorHandler;

  public ShippingService(MemberValidatorHandler memberValidatorHandler, OrderValidatorHandler orderValidatorHandler) {
    this.memberValidatorHandler = memberValidatorHandler;
    this.orderValidatorHandler = orderValidatorHandler;
  }

  public void menu() {

    loadShippings();

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

    try(Scanner in = new Scanner(new FileInputStream("shippings.csv"))) {

      while (true) {
        try {
          String record = in.nextLine();
          String[] fields = record.split(",");
          Shipping s = new Shipping();
          s.setMemberId(fields[0]);
          s.setOrderNumber(Integer.parseInt(fields[1]));
          s.setTrackingNumber(Integer.parseInt(fields[2]));
          s.setStatus(Integer.parseInt(fields[3]));
          s.setManager(fields[4]);
        } catch (Exception e) {
          break;
        }
      }
    } catch (Exception e) {
      System.out.println("배송 데이터 로딩 중 오류 발생!");
    }
  }

  private void saveShippings() {
    try(FileWriter out = new FileWriter("shippings.csv")) {

      for (Shipping s : shippingList) {
        out.write(String.format("%d,%s,%d,%d,%d,%s",
            s.getNumber(),
            s.getMemberId(),
            s.getOrderNumber(),
            s.getTrackingNumber(),
            s.getStatus(),
            s.getManager()
            ));
      }

      System.out.println("배송 데이터 저장!");

    } catch (Exception e) {
      System.out.println("배송 데이터 파일로 저장하는 중에 오류 발생!");
    }
  }
}

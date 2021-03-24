package com.sunwoo.project.handler;

import java.io.FileInputStream;
import java.io.FileOutputStream;
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

    try(FileInputStream in = new FileInputStream("shippings.data")) {

      int size = in.read() << 8 | in.read();

      for (int i = 0; i < size; i++) {
        Shipping shipping = new Shipping();

        byte[] bytes = new byte[30000];

        int len = in.read() << 8 | in.read();
        in.read(bytes, 0, len);
        shipping.setMemberId(new String(bytes,0,len,"UTF-8"));

        int value = in.read() << 24;
        value += in.read() << 16;
        value += in.read() << 8;
        value += in.read();
        shipping.setOrderNumber(value);

        value = in.read() << 24;
        value += in.read() << 16;
        value += in.read() << 8;
        value += in.read();
        shipping.setTrackingNumber(value);

        value = in.read() << 24;
        value += in.read() << 16;
        value += in.read() << 8;
        value += in.read();
        shipping.setStatus(value);

        len = in.read() << 8 | in.read();
        in.read(bytes, 0, len);
        shipping.setManager(new String(bytes,0,len,"UTF-8"));
      }

    } catch (Exception e) {
      System.out.println("배송 데이터 로딩 중 오류 발생!");
    }

  }

  private void saveShippings() {
    try(FileOutputStream out = new FileOutputStream("shippings.data")) {
      out.write(shippingList.size() >> 8);
      out.write(shippingList.size());

      for (Shipping s : shippingList) {
        out.write(s.getNumber() >> 24);
        out.write(s.getNumber() >> 16);
        out.write(s.getNumber() >> 8);
        out.write(s.getNumber());

        byte[] buf = s.getMemberId().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        out.write(buf);

        out.write(s.getOrderNumber() >> 24);
        out.write(s.getOrderNumber() >> 16);
        out.write(s.getOrderNumber() >> 8);
        out.write(s.getOrderNumber());

        out.write(s.getTrackingNumber() >> 24);
        out.write(s.getTrackingNumber() >> 16);
        out.write(s.getTrackingNumber() >> 8);
        out.write(s.getTrackingNumber());

        out.write(s.getStatus() >> 24);
        out.write(s.getStatus() >> 16);
        out.write(s.getStatus() >> 8);
        out.write(s.getStatus());

        buf = s.getManager().getBytes("UTF-8");
        out.write(buf.length);
        out.write(buf.length);
        out.write(buf);
      }

      System.out.println("배송 데이터 저장!");

    } catch (Exception e) {

      System.out.println("배송 데이터 파일로 저장하는 중에 오류 발생!");

    }

  }
}

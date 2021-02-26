package com.sunwoo.project.handler;

import java.util.HashMap;
import java.util.LinkedList;
import com.sunwoo.project.domain.Product;
import com.sunwoo.util.Prompt;

public class ProductService {

  LinkedList<Product> productList = new LinkedList<>();

  public LinkedList<Product> getProductList() {
    return productList;
  }

  public void menu() {

    HashMap<String,Command> commandMap = new HashMap<>();

    commandMap.put("1", new ProductAddHandler(productList));
    commandMap.put("2", new ProductListHandler(productList));
    commandMap.put("3", new ProductDetailHandler(productList));
    commandMap.put("4", new ProductUpdateHandler(productList));
    commandMap.put("5", new ProductDeleteHandler(productList));

    loop:
      while(true) {
        System.out.println("[메인 > 상품]");
        System.out.println("1. 등록");
        System.out.println("2. 목록");
        System.out.println("3. 상세 보기");
        System.out.println("4. 수정");
        System.out.println("5. 삭제");
        System.out.println("0. 이전 메뉴");
        System.out.println();

        String command = Prompt.inputString("명령> ");
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
  }
}


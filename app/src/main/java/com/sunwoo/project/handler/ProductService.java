package com.sunwoo.project.handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.LinkedList;
import com.sunwoo.project.domain.Product;
import com.sunwoo.util.Prompt;

public class ProductService {

  static LinkedList<Product> productList = new LinkedList<>();

  public LinkedList<Product> getProductList() {
    return productList;
  }

  public ProductService() { // menu()를 호출하지 않아도 상품정보가 로딩될 수 있도록 생성자에서 load
    loadProducts();
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
    saveProducts();
  }

  static void loadProducts() {
    try(BufferedReader in = new BufferedReader(new FileReader("products.csv"))){
      String csvStr = null;
      while((csvStr = in.readLine()) != null) {
        try {
          productList.add(Product.valueOfCsv(csvStr));
          System.out.println("상품 데이터 로딩!");
        } catch (Exception e) {
          break;
        }
      }
    }catch (Exception e) {
      System.out.println("상품 데이터 로딩 중 오류 발생!");
    }
  }

  static void saveProducts() {
    try(BufferedWriter out = new BufferedWriter(new FileWriter("products.csv"))) {

      for (Product p : productList) {
        out.write(p.toCsvString());
      }

      System.out.println("상품 데이터 저장!");
    } catch (Exception e) {
      System.out.println("상품 데이터 파일로 저장 중 오류 발생!");
    }
  }

}


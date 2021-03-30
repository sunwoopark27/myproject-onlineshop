package com.sunwoo.project.handler;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import com.sunwoo.project.domain.Product;
import com.sunwoo.util.Prompt;

public class ProductService {

  static LinkedList<Product> productList = new LinkedList<>();

  public LinkedList<Product> getProductList() {
    return productList;
  }

  public void menu() {

    loadProducts();

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
    try(FileInputStream in = new FileInputStream("products.csv")){

      int size = in.read() << 8 | in.read();

      for(int i = 0; i < size; i++) {

        Product product = new Product();

        byte[] bytes = new byte[30000];

        int value = in.read() << 24;
        value += in.read() << 16;
        value += in.read() << 8;
        value += in.read();
        product.setNumber(value);

        int len = in.read() << 8 | in.read();
        in.read(bytes, 0, len);
        product.setName(new String(bytes, 0, len, "UTF-8"));

        value = in.read() << 24;
        value += in.read() << 16;
        value += in.read() << 8;
        value += in.read();
        product.setPrice(value);

        len = in.read() << 8 | in.read();
        in.read(bytes, 0, len);
        product.setPhoto(new String(bytes, 0, len, "UTF-8"));

        productList.add(product);
        System.out.println("상품 데이터 로딩!");
      }
    } catch (Exception e) {
      System.out.println("상품 데이터 로딩 중 오류 발생!");
    }
  }

  static void saveProducts() {
    try(FileOutputStream out = new FileOutputStream("products.csv")) {

      out.write(productList.size() >> 8);
      out.write(productList.size());

      for (Product product : productList) {
        out.write(product.getNumber() >> 24);
        out.write(product.getNumber() >> 16);
        out.write(product.getNumber() >> 8);
        out.write(product.getNumber());

        byte[] bytes = product.getName().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        out.write(product.getPrice() >> 24);
        out.write(product.getPrice() >> 16);
        out.write(product.getPrice() >> 8);
        out.write(product.getPrice());

        bytes = product.getPhoto().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);
      }

      System.out.println("상품 데이터 저장!");
    } catch (Exception e) {
      System.out.println("상품 데이터 파일로 저장 중 오류 발생!");
    }
  }

}


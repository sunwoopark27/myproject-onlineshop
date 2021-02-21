package com.sunwoo.project.handler;

import java.util.LinkedList;
import com.sunwoo.project.domain.Product;
import com.sunwoo.util.Prompt;

public class ProductService {

  LinkedList<Product> productList = new LinkedList<>();

  public LinkedList<Product> getProductList() {
    return productList;
  }

  ProductAddHandler productAddHandler = new ProductAddHandler(productList);
  ProductListHandler productListHandler = new ProductListHandler(productList);
  ProductDetailHandler productDetailHandler = new ProductDetailHandler(productList);
  ProductUpdateHandler productUpdateHandler = new ProductUpdateHandler(productList);
  ProductDeleteHandler productDeleteHandler = new ProductDeleteHandler(productList);

  public void menu() {

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
            case "1" :
              productAddHandler.service();
              break;
            case "2" :
              productListHandler.service();
              break;
            case "3" :
              productDetailHandler.service();
              break;
            case "4" :
              productUpdateHandler.service();
              break;
            case "5" :
              productDeleteHandler.service();
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


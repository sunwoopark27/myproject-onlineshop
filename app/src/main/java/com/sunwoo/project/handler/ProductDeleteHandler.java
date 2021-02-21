package com.sunwoo.project.handler;

import java.util.List;
import com.sunwoo.project.domain.Product;
import com.sunwoo.util.Prompt;

public class ProductDeleteHandler extends AbstractProductHandler {

  public ProductDeleteHandler(List<Product> productList) {
    super(productList);
    this.productList = productList;
  }

  @Override
  public void service() {
    System.out.println("[메인 > 상품 > 삭제]");

    int no = Prompt.inputInt("번호? ");
    Product product = findByNo(no);
    if(product == null) {
      System.out.println("해당 번호의 상품이 없습니다.");
      System.out.println();

    }else {
      String userChoice = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");

      if(userChoice.equalsIgnoreCase("y")) {
        productList.remove(product);

        System.out.println("상품 삭제가 완료되었습니다.");
        System.out.println();
        return;
      }else {

        System.out.println("상품 삭제를 취소하였습니다.");
        System.out.println();
        return;
      }
    }
  }
}


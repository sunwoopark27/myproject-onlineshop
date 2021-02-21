package com.sunwoo.project.handler;

import java.util.List;
import com.sunwoo.project.domain.Shipping;
import com.sunwoo.util.Prompt;

public class ShippingDeleteHandler extends AbstractShippingHandler {

  public ShippingDeleteHandler(List<Shipping> shippingList) {
    super(shippingList);
  }

  @Override
  public void service() {
    System.out.println("[메인 > 배송 > 삭제]");

    int no = Prompt.inputInt("번호? ");
    Shipping shipping = findByNo(no);
    if(shipping == null) {
      System.out.println("해당 번호의 배송이 없습니다.");
      System.out.println();

    }else {
      String userChoice = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");

      if(userChoice.equalsIgnoreCase("y")) {

        shippingList.remove(shipping);
        System.out.println("배송 삭제가 완료되었습니다.");
        System.out.println();
        return;
      }else {

        System.out.println("배송 삭제를 취소하였습니다.");
        System.out.println();
        return;
      }
    }
  }
}

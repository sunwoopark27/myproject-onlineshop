package com.sunwoo.project.handler;

import java.util.Iterator;
import java.util.List;
import com.sunwoo.project.domain.Shipping;

public class ShippingListHandler extends AbstractShippingHandler {

  public ShippingListHandler(List<Shipping> shippingList) {
    super(shippingList);
  }

  @Override
  public void service() {
    System.out.println("[메인 > 배송 > 목록]");

    Iterator<Shipping> iterator = shippingList.iterator();

    while (iterator.hasNext()) {
      Shipping s = iterator.next();

      System.out.printf("배송 번호: %d 고객 아이디: %s\n운송장 번호: %d\n"
          ,s.getNumber(), s.getMemberId(), s.getTrackingNumber());
      System.out.println("-----------------------------------------");

    }
    System.out.println();
  }
}

package com.sunwoo.project.handler;

import java.util.List;
import com.sunwoo.project.domain.Shipping;

public abstract class AbstractShippingHandler implements Command {

  protected List<Shipping> shippingList;

  public AbstractShippingHandler(List<Shipping> shippingList) {
    this.shippingList = shippingList;
  }

  protected String getStatusLabel(int status) {
    switch (status) {
      case 1:
        return "배송중";
      case 2: 
        return "배송 완료";
      default:
        return "배송 준비중";
    }
  }

  protected Shipping findByNo(int shippingNo) {
    Shipping[] list = shippingList.toArray(new Shipping[shippingList.size()]);
    for(Shipping s : list) {
      if(s.getNumber() == shippingNo) {
        return s;
      }
    }
    return null;
  }

}

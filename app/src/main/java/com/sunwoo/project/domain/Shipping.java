package com.sunwoo.project.domain;

public class Shipping {
  private int number;
  private String memberId;
  private int orderNumber;
  private int trackingNumber;
  private int status;
  private String manager;

  public int getNumber() {
    return number;
  }
  public void setNumber(int number) {
    this.number = number;
  }
  public String getMemberId() {
    return memberId;
  }
  public void setMemberId(String memberId) {
    this.memberId = memberId;
  }
  public int getOrderNumber() {
    return orderNumber;
  }
  public void setOrderNumber(int orderNumber) {
    this.orderNumber = orderNumber;
  }
  public int getTrackingNumber() {
    return trackingNumber;
  }
  public void setTrackingNumber(int trackingNumber) {
    this.trackingNumber = trackingNumber;
  }
  public int getStatus() {
    return status;
  }
  public void setStatus(int status) {
    this.status = status;
  }
  public String getManager() {
    return manager;
  }
  public void setManager(String manager) {
    this.manager = manager;
  }



}

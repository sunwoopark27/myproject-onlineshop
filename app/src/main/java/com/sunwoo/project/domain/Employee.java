package com.sunwoo.project.domain;

import java.sql.Date;

public class Employee {
  private int number;
  private String name;
  private String id;
  private String password;
  private String tel;
  private String address;
  private String email;
  private Date employmentDate; 

  @Override
  public String toString() {
    return "Employee [number=" + number + ", name=" + name + ", id=" + id + ", password=" + password
        + ", tel=" + tel + ", address=" + address + ", email=" + email + ", employmentDate="
        + employmentDate + "]";
  }

  public int getNumber() {
    return number;
  }
  public void setNumber(int number) {
    this.number = number;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public Date getEmploymentDate() {
    return employmentDate;
  }
  public void setEmploymentDate(Date employmentDate) {
    this.employmentDate = employmentDate;
  }


}

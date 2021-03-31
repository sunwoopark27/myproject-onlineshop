package com.sunwoo.project.domain;

import java.sql.Date;
import com.sunwoo.util.CsvObject;

public class Member implements CsvObject {

  private int number;//
  private String name;//
  private String id;//
  private String password;//
  private String tel;
  private String address;
  private String email;
  private Date joinDate;

  @Override
  public String toCsvString() {
    return String.format("%d,%s,%s,%s,%s,%s,%s,%s\n",
        this.getNumber(),
        this.getName(),
        this.getId(),
        this.getPassword(),
        this.getTel(),
        this.getAddress(),
        this.getEmail(),
        this.getJoinDate());
  }

  public static Member valueOfCsv(String csv){
    String[] fields = csv.split(",");
    Member m = new Member();
    m.setNumber(Integer.parseInt(fields[0]));
    m.setName(fields[1]);
    m.setId(fields[2]);
    m.setPassword(fields[3]);
    m.setTel(fields[4]);
    m.setAddress(fields[5]);
    m.setEmail(fields[6]);
    m.setJoinDate(Date.valueOf(fields[7]));

    return m;
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + number;
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Member other = (Member) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (number != other.number)
      return false;
    if (password == null) {
      if (other.password != null)
        return false;
    } else if (!password.equals(other.password))
      return false;
    return true;
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
  public Date getJoinDate() {
    return joinDate;
  }
  public void setJoinDate(Date joinDate) {
    this.joinDate = joinDate;
  }


}

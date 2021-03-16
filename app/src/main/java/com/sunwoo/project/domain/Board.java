package com.sunwoo.project.domain;

import java.io.Serializable;
import java.sql.Date;

public class Board implements Serializable {

  private static final long serialVersionUID = 1L;

  private int number;//
  private String title;//
  private String content;//
  private String writer;//
  private Date registeredDate;//
  private int viewCount;
  private int like;

  public String toCsvString() {
    return String.format("%d,%s,%s,%s,%s,%d\n",
        this.getNumber(),
        this.getTitle(),
        this.getContent(),
        this.getWriter(),
        this.getRegisteredDate(),
        this.getViewCount());
  }

  // 다음과 같이 인스턴스를 생성해주는 메서드를 
  // "factory method"라 부른다.
  // 팩토리 메서드 패턴
  // - 인스턴스 생성 과정이 복잡할 때 
  //   인스턴스 생성을 대신 해주는 메서드를 만들어
  //   그 메서드를 통해 객체를 생성하는 프로그래밍 방식이다.
  public static Board valueOfCsv(String csv) {
    String[] fields = csv.split(",");
    Board b = new Board();
    b.setNumber(Integer.parseInt(fields[0]));
    b.setTitle(fields[1]);
    b.setContent(fields[2]);
    b.setWriter(fields[3]);
    b.setRegisteredDate(Date.valueOf(fields[4]));
    b.setViewCount(Integer.parseInt(fields[5]));
    return b;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((content == null) ? 0 : content.hashCode());
    result = prime * result + number;
    result = prime * result + ((registeredDate == null) ? 0 : registeredDate.hashCode());
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    result = prime * result + ((writer == null) ? 0 : writer.hashCode());
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
    Board other = (Board) obj;
    if (content == null) {
      if (other.content != null)
        return false;
    } else if (!content.equals(other.content))
      return false;
    if (number != other.number)
      return false;
    if (registeredDate == null) {
      if (other.registeredDate != null)
        return false;
    } else if (!registeredDate.equals(other.registeredDate))
      return false;
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals(other.title))
      return false;
    if (writer == null) {
      if (other.writer != null)
        return false;
    } else if (!writer.equals(other.writer))
      return false;
    return true;
  }

  public int getNumber() {
    return number;
  }
  public void setNumber(int number) {
    this.number = number;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getWriter() {
    return writer;
  }
  public void setWriter(String writer) {
    this.writer = writer;
  }
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }
  public int getViewCount() {
    return viewCount;
  }
  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }
  public int getLike() {
    return like;
  }
  public void setLike(int like) {
    this.like = like;
  }


}
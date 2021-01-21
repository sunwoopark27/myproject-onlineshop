package com.sunwoo.project.Handler;

import java.sql.Date;
import com.sunwoo.util.Prompt;

public class MemberHandler {

  static class Member {
    int memberNumber;
    String memberName;
    String memberId;
    String password;
    String memberPhone;
    String address;
    String email;
    Date joinDate;
  }

  //회원
  static final int LENGTH = 100;

  static Member[] members = new Member[LENGTH];

  static int size = 0;

  public static void add(){
    System.out.println("[회원 등록]");

    Member m = new Member();

    m.memberNumber = Prompt.promptInt("회원 번호: ");
    m.memberName = Prompt.promptString("이름: ");
    m.memberId = Prompt.promptString("아이디: ");
    m.password = Prompt.promptString("비밀번호: ");
    m.memberPhone = Prompt.promptString("전화번호: ");
    m.address = Prompt.promptString("주소: ");
    m.email = Prompt.promptString("메일: ");
    m.joinDate = new Date(System.currentTimeMillis());

    members[size++] = m;

    System.out.println();

  }

  public static void list() {
    System.out.println("[회원 목록]");

    for(int i = 0; i < size; i++) {
      Member m = members[i];

      System.out.printf("회원 번호: %d 회원 아이디: %s 이름: %s\n전화번호: %s 주소: %s 메일: %s\n가입 날짜: %s\n",m.memberNumber, m.memberId,m.memberName, m.memberPhone, m.address, m.email, m.joinDate);
      System.out.println("--------------------------------------------------");

    }

    System.out.println();
  }

  static boolean exist(String name){
    for(int i = 0; i < size; i++) {
      if(name.equals(members[i].memberName)) {
        return true;
      }
    }
    return false;
  }
}

package com.sunwoo.project;

import java.sql.Date;

public class MemberHandler {

  //회원
  static final int MEMBER_LENGTH = 3;
  static int[] memberNumber = new int[MEMBER_LENGTH];
  static String[] memberName = new String[MEMBER_LENGTH];
  static String[] memberPhone = new String[MEMBER_LENGTH];
  static String[] password = new String[MEMBER_LENGTH];
  static String[] address = new String[MEMBER_LENGTH];
  static String[] mail = new String[MEMBER_LENGTH];
  static Date[] joinDate = new Date[MEMBER_LENGTH];
  static int size = 0;

  static void addMember(){
    System.out.println("[회원 등록]");

    memberNumber[size] = Prompt.promptInt("회원 번호: ");
    memberName[size] = Prompt.promptString("이름: ");
    memberPhone[size] = Prompt.promptString("전화번호: ");
    password[size] = Prompt.promptString("비밀번호: ");
    address[size] = Prompt.promptString("주소: ");
    mail[size] = Prompt.promptString("메일: ");
    joinDate[size] = new Date(System.currentTimeMillis());
    size++;

    System.out.println();

  }

  static void listMember() {
    System.out.println("[회원 목록]");

    for(int i = 0; i < size; i++) {
      System.out.printf("%d, %s, %s, %s, %s, %s\n",memberNumber[i], memberName[i], memberPhone[i], address[i], mail[i], joinDate[i]);

    }

    System.out.println();
  }
}

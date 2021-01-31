package com.sunwoo.project.handler;

import java.sql.Date;
import com.sunwoo.project.domain.Member;
import com.sunwoo.util.Prompt;

public class MemberHandler {

  Node first;
  Node last;
  int size = 0;

  public void add(){
    System.out.println("[회원 등록]");

    Member m = new Member();

    m.number = Prompt.promptInt("회원 번호: ");
    m.name = Prompt.promptString("이름: ");
    m.id = Prompt.promptString("아이디: ");
    m.password = Prompt.promptString("비밀번호: ");
    m.tel = Prompt.promptString("전화번호: ");
    m.address = Prompt.promptString("주소: ");
    m.email = Prompt.promptString("메일: ");
    m.joinDate = new Date(System.currentTimeMillis());

    Node node = new Node(m);

    if(first == null) {
      first = node;
      last = node;
    }else {
      node.prev = last;
      last.next = node;
      last = node;
    }
    this.size++;

    System.out.println();

  }

  public void list() {
    System.out.println("[회원 목록]");

    Node cursor = first; 

    while(cursor != null) {
      Member m = cursor.member;

      System.out.printf("회원 번호: %d 회원 아이디: %s 이름: %s\n전화번호: %s 주소: %s 메일: %s\n가입 날짜: %s\n"
          ,m.number, m.id,m.name, m.tel, m.address, m.email, m.joinDate);
      System.out.println("--------------------------------------------------");

      cursor = cursor.next;
    }

    System.out.println();
  }

  boolean exist(String name){
    Node cursor = first;
    while(cursor != null) {
      if(name.equals(cursor.member.name)) {
        return true;
      }
      cursor = cursor.next;
    }
    return false;
  }



  public void detail() {
    System.out.println("[회원 정보]");

    Member member = findByNo(Prompt.promptInt("번호? "));

    if (member == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      System.out.println();

    }else {
      System.out.printf("이름: %s\n", member.name);
      System.out.printf("아이디: %s\n", member.id);
      System.out.printf("전화번호: %s\n", member.tel);
      System.out.printf("주소: %s\n", member.address);
      System.out.printf("메일: %s\n", member.email);
      System.out.printf("가입 날짜: %s\n", member.joinDate);
      System.out.println("-------------------------------------------------------------");
      return;

    }
  }

  public void update() {
    System.out.println("[회원 정보 수정]");

    Member member = findByNo(Prompt.promptInt("번호? "));
    if(member == null) {

      System.out.println("해당 번호의 게시글이 없습니다.");
      System.out.println();

    }else {

      String name = Prompt.promptString(String.format("이름(%s)? ",member.name));
      String id = Prompt.promptString(String.format("아이디(%s)? ",member.id));
      String tel = Prompt.promptString(String.format("전화번호(%s)? ",member.tel));
      String address = Prompt.promptString(String.format("주소(%s)? ",member.address));
      String email = Prompt.promptString(String.format("메일(%s)? ",member.email));


      String userChoice = Prompt.promptString("정말 수정하시겠습니까?(y: 비밀번호 입력) ");
      if(userChoice.equals(member.password)) {
        member.name = name;
        member.id = id;
        member.tel = tel;
        member.address = address;
        member.email = email;
        System.out.println("회원 정보 수정이 완료되었습니다.");
        System.out.println();
      }else {
        System.out.println("잘못된 비밀번호입니다. 회원 정보 수정을 취소합니다.");
        System.out.println();
        return;
      }
    }
  }


  public void delete() {
    System.out.println("[회원 정보 삭제]");

    Member member = findByNo(Prompt.promptInt("번호? "));
    if(member == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      System.out.println();

    }else {
      String userChoice = Prompt.promptString("정말 삭제하시겠습니까?(y/N) ");

      if(userChoice.equalsIgnoreCase("y")) {
        Node cursor = first;
        while(cursor != null) {
          if(cursor.member == member) {
            if(first == last) {
              first = null;
              last = null;
            }else if(cursor == first) {
              first = cursor.next;
              cursor.prev = null;
            }else if(cursor == last) {
              last = cursor.prev;
              cursor.next = null;
            }else {
              cursor.prev.next = cursor.next;
              if(cursor.next != null) {
                cursor.next.prev = cursor.prev;
              }
            }
            this.size--;
            break;
          }
          cursor = cursor.next;
        }
      }else {

        System.out.println("회원 정보 삭제를 취소하였습니다.");
        System.out.println();
        return;
      }
    }
  }

  Member findByNo(int memberNo) {
    Node cursor = first;
    while(cursor != null) {
      if(cursor.member.number == memberNo) {
        return cursor.member;
      }
      cursor = cursor.next;
    }
    return null;
  }

  static class Node{
    Member member;
    Node next;
    Node prev;

    Node(Member m){
      this.member = m;
    }
  }
}

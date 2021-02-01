package com.sunwoo.project.handler;

import java.sql.Date;
import com.sunwoo.project.domain.Member;
import com.sunwoo.util.Prompt;

public class MemberHandler {

  public MemberList memberList = new MemberList();

  public void add(){
    System.out.println("[회원 등록]");

    Member m = new Member();

    m.number = Prompt.inputInt("회원 번호: ");
    m.name = Prompt.inputString("이름: ");
    m.id = Prompt.inputString("아이디: ");
    m.password = Prompt.inputString("비밀번호: ");
    m.tel = Prompt.inputString("전화번호: ");
    m.address = Prompt.inputString("주소: ");
    m.email = Prompt.inputString("메일: ");
    m.joinDate = new Date(System.currentTimeMillis());

    memberList.add(m);
    System.out.println();
    System.out.println("회원 등록이 완료되었습니다.");
    System.out.println();

  }

  public void list() {
    System.out.println("[회원 목록]");

    Member[] members = memberList.toArray();

    for(Member m : members) {

      System.out.printf("회원 번호: %d 회원 아이디: %s 이름: %s\n전화번호: %s 주소: %s 메일: %s\n가입 날짜: %s\n"
          ,m.number, m.id,m.name, m.tel, m.address, m.email, m.joinDate);
      System.out.println("--------------------------------------------------");


    }

    System.out.println();
  }


  public void detail() {
    System.out.println("[회원 정보]");

    Member member = memberList.get(Prompt.inputInt("번호? "));

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

    Member member = memberList.get(Prompt.inputInt("번호? "));
    if(member == null) {

      System.out.println("해당 번호의 게시글이 없습니다.");
      System.out.println();

    }else {

      String name = Prompt.inputString(String.format("이름(%s)? ",member.name));
      String id = Prompt.inputString(String.format("아이디(%s)? ",member.id));
      String tel = Prompt.inputString(String.format("전화번호(%s)? ",member.tel));
      String address = Prompt.inputString(String.format("주소(%s)? ",member.address));
      String email = Prompt.inputString(String.format("메일(%s)? ",member.email));


      String userChoice = Prompt.inputString("정말 수정하시겠습니까?(y: 비밀번호 입력) ");
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

    int no = Prompt.inputInt("번호? ");
    Member member = memberList.get(no);
    if(member == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      System.out.println();

    }else {
      String userChoice = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");

      if(userChoice.equalsIgnoreCase("y")) {

        memberList.delete(no);

        System.out.println("회원 정보 삭제를 완료하였습니다.");
        System.out.println();
      }else {

        System.out.println("회원 정보 삭제를 취소하였습니다.");
        System.out.println();
        return;
      }
    }
  }

}

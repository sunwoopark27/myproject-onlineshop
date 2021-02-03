package com.sunwoo.project.handler;

import java.sql.Date;
import com.sunwoo.project.domain.Member;
import com.sunwoo.util.List;
import com.sunwoo.util.Prompt;

public class MemberHandler {

  private List memberList = new List();

  public List getMemberList() {
    return this.memberList;
  }

  public void add(){
    System.out.println("[회원 등록]");

    Member m = new Member();

    m.setNumber(Prompt.inputInt("회원 번호: "));
    m.setName(Prompt.inputString("이름: "));
    m.setId(Prompt.inputString("아이디: "));
    m.setPassword(Prompt.inputString("비밀번호: "));
    m.setTel(Prompt.inputString("전화번호: "));
    m.setAddress(Prompt.inputString("주소: "));
    m.setEmail(Prompt.inputString("메일: "));
    m.setJoinDate(new Date(System.currentTimeMillis()));

    memberList.add(m);
    System.out.println();
    System.out.println("회원 등록이 완료되었습니다.");
    System.out.println();

  }

  public void list() {
    System.out.println("[회원 목록]");

    Object[] list = memberList.toArray();

    for(Object obj : list) {
      Member m = (Member)obj;

      System.out.printf("번호: %d 아이디: %s 이름: %s\n가입 날짜: %s\n"
          ,m.getNumber(), m.getId(), m.getName(), m.getJoinDate());
      System.out.println("--------------------------------------------------");

    }

    System.out.println();
  }


  public void detail() {
    System.out.println("[회원 정보]");

    Member member = findByNo(Prompt.inputInt("번호? "));

    if (member == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      System.out.println();

    }else {
      System.out.printf("이름: %s\n", member.getName());
      System.out.printf("아이디: %s\n", member.getId());
      System.out.printf("전화번호: %s\n", member.getTel());
      System.out.printf("주소: %s\n", member.getAddress());
      System.out.printf("메일: %s\n", member.getEmail());
      System.out.printf("가입 날짜: %s\n", member.getJoinDate());
      System.out.println("-------------------------------------------------------------");
      return;

    }
  }

  public void update() {
    System.out.println("[회원 정보 수정]");

    Member member = findByNo(Prompt.inputInt("번호? "));
    if(member == null) {

      System.out.println("해당 번호의 게시글이 없습니다.");
      System.out.println();

    }else {

      String name = Prompt.inputString(String.format("이름(%s)? ",member.getName()));
      String id = Prompt.inputString(String.format("아이디(%s)? ",member.getId()));
      String tel = Prompt.inputString(String.format("전화번호(%s)? ",member.getTel()));
      String address = Prompt.inputString(String.format("주소(%s)? ",member.getAddress()));
      String email = Prompt.inputString(String.format("메일(%s)? ",member.getEmail()));


      String userChoice = Prompt.inputString("정말 수정하시겠습니까?(y: 비밀번호 입력) ");
      if(userChoice.equals(member.getPassword())) {
        member.setName(name);
        member.setId(id);
        member.setTel(tel);
        member.setAddress(address);
        member.setEmail(email);
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
    Member member = findByNo(no);
    if(member == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      System.out.println();

    }else {
      String userChoice = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");

      if(userChoice.equalsIgnoreCase("y")) {

        memberList.delete(member);

        System.out.println("회원 정보 삭제를 완료하였습니다.");
        System.out.println();
      }else {

        System.out.println("회원 정보 삭제를 취소하였습니다.");
        System.out.println();
        return;
      }
    }
  }

  String inputMemberId(){
    while(true) {
      String id = Prompt.inputString("회원 아이디(enter(취소)): ");
      if(id.equals("")) {
        return null;
      }
      if(findById(id) != null) {
        return id;
      }
      System.out.println("등록된 회원이 아닙니다.");
    }
  }

  String inputMember(String promptTitle) {
    while(true) {
      String id = Prompt.inputString(promptTitle);
      if(id.length() == 0) {
        return null;
      }else if(findById(id) != null) {
        return id;
      }
      System.out.println("잘못된 아이디 입니다.");
    }
  }

  private Member findByNo(int memberNo) {
    Object[] list = memberList.toArray();
    for(Object obj : list) {
      Member m = (Member)obj;
      if(m.getNumber() == memberNo) {
        return m;
      }
    }
    return null;
  }

  private Member findById(String id) {
    Object[] list = memberList.toArray();
    for(Object obj : list) {
      Member m = (Member)obj;
      if(m.getId().equals(id)) {
        return m;
      }
    }
    return null;
  }

}

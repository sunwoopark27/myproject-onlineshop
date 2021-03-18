package com.sunwoo.project.handler;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedList;
import com.sunwoo.project.domain.Member;
import com.sunwoo.util.Prompt;

public class MemberService {

  static LinkedList<Member> memberList = new LinkedList<>();
  public LinkedList<Member> getMemberList() {
    return memberList;
  }

  //  MemberValidatorHandler memberValidatorHandler = new MemberValidatorHandler(memberList);
  //  public MemberValidatorHandler getMemberValidatorHandler() {
  //    return memberValidatorHandler;
  //  }

  public void menu() {

    loadMembers();

    HashMap<String,Command> commandMap = new HashMap<>();

    commandMap.put("1", new MemberAddHandler(memberList));
    commandMap.put("2", new MemberListHandler(memberList));
    commandMap.put("3", new MemberDetailHandler(memberList));
    commandMap.put("4", new MemberUpdateHandler(memberList));
    commandMap.put("5", new MemberDeleteHandler(memberList));

    loop:
      while(true) {
        System.out.println("[메인 > 회원]");
        System.out.println("1. 등록");
        System.out.println("2. 목록");
        System.out.println("3. 상세 보기");
        System.out.println("4. 수정");
        System.out.println("5. 삭제");
        System.out.println("0. 이전 메뉴");
        System.out.println();

        String command = com.sunwoo.util.Prompt.inputString("명령> ");
        System.out.println();
        try {
          switch(command) {
            case "0" :
              System.out.println("메인으로 돌아갑니다.");
              System.out.println();
              break loop;
            default :
              Command commandHandler = commandMap.get(command);

              if(commandHandler == null) {
                System.out.println("실행할 수 없는 메뉴 번호 입니다.");
              }else {
                commandHandler.service();
              }

          }
        }catch(Exception e) {
          System.out.println("-----------------------------------------------------------------------------");
          System.out.printf("명령어 실행 중 오류 발생: %s - %s\n",e.getClass().getName(), e.getMessage());
          System.out.println("-----------------------------------------------------------------------------");
        }
        System.out.println();
      }
    saveMembers();
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

  protected Member findByNo(int memberNo) {
    Member[] list = memberList.toArray(new Member[memberList.size()]);
    for(Member m : list) {
      if(m.getNumber() == memberNo) {
        return m;
      }
    }
    return null;
  }

  private Member findById(String id) {
    Member[] list = memberList.toArray(new Member[memberList.size()]);
    for(Member m : list) {
      if(m.getId().equals(id)) {
        return m;
      }
    }
    return null;
  }

  static void loadMembers() {

    try(FileInputStream in = new FileInputStream("members.data")) {
      int size = in.read() << 8 | in.read();

      for(int i = 0; i < size; i++) {
        Member member = new Member();

        int value = in.read() << 24;
        value += in.read() << 16;
        value += in.read() << 8;
        value += in.read();
        member.setNumber(value);

        // 문자열을 읽을 바이트 배열
        byte[] bytes = new byte[30000];

        int len = in.read() << 8 | in.read();
        in.read(bytes, 0, len);
        member.setName(new String(bytes, 0, len, "UTF-8"));

        len = in.read() << 8 | in.read();
        in.read(bytes, 0, len);
        member.setId(new String(bytes, 0, len, "UTF-8"));

        len = in.read() << 8 | in.read();
        in.read(bytes, 0, len);
        member.setPassword(new String(bytes, 0, len, "UTF-8"));

        len = in.read() << 8 | in.read();
        in.read(bytes, 0, len);
        member.setTel(new String(bytes, 0, len, "UTF-8"));

        len = in.read() << 8 | in.read();
        in.read(bytes, 0, len);
        member.setAddress(new String(bytes, 0, len, "UTF-8"));

        len = in.read() << 8 | in.read();
        in.read(bytes, 0, len);
        member.setEmail(new String(bytes, 0, len, "UTF-8"));

        len = in.read() << 8 | in.read();
        in.read(bytes, 0, len);
        member.setJoinDate(Date.valueOf(new String(bytes, 0, len, "UTF-8")));

        memberList.add(member);
      }

      System.out.println("멤버 데이터 로딩!");

    } catch (Exception e) {

      System.out.println("멤버 데이터 로딩 중 오류 발생!");

    }

  }

  static void saveMembers() {

    try(FileOutputStream out = new FileOutputStream("members.data")) {

      out.write(memberList.size() >> 8);
      out.write(memberList.size());

      for (Member member : memberList) {
        out.write(member.getNumber() >> 24);
        out.write(member.getNumber() >> 16);
        out.write(member.getNumber() >> 8);
        out.write(member.getNumber());


        byte[] bytes = member.getName().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        bytes = member.getId().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        bytes = member.getPassword().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        bytes = member.getTel().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        bytes = member.getAddress().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        bytes = member.getEmail().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        bytes = member.getJoinDate().toString().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

      }
      System.out.println("회원 데이터 저장!");

    } catch(Exception e) {

      System.out.println("회원 데이터 파일로 저장 중 오류 발생!");

    }

  }
}

package com.sunwoo.project.handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.LinkedList;
import com.sunwoo.project.domain.Member;

public class MemberService {

  static LinkedList<Member> memberList = new LinkedList<>();
  public LinkedList<Member> getMemberList() {
    return memberList;
  }

  MemberValidatorHandler memberValidatorHandler = new MemberValidatorHandler(memberList);
  public MemberValidatorHandler getMemberValidatorHandler() {
    return memberValidatorHandler;
  }
  public MemberService() { // menu()를 호출하지 않아도 멤버정보가 로딩될 수 있도록 생성자에서 load
    loadMembers();
  }

  public void menu() {

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

  static void loadMembers() {

    try(BufferedReader in = new BufferedReader(new FileReader("members.csv"))) {
      String csvString = null;
      while((csvString = in.readLine()) != null) {
        try {
          memberList.add(Member.valueOfCsv(csvString));
        } catch(Exception e){
          break;
        }
      }
      System.out.println("멤버 데이터 로딩!");
    } catch (Exception e) {
      System.out.println("멤버 데이터 로딩 중 오류 발생!");
    }
  }

  static void saveMembers() {

    try(BufferedWriter out = new BufferedWriter(new FileWriter("members.csv"))) {

      for (Member m : memberList) {
        out.write(m.toCsvString());
      }
      System.out.println("회원 데이터 저장!");

    } catch(Exception e) {
      System.out.println("회원 데이터 파일로 저장 중 오류 발생!");
    }

  }
}

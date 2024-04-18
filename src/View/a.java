package View;

import java.util.Scanner;

import Controller.Controller;
import Model.DTO;

public class a {

   public static void main(String[] args) {
      Scanner s = new Scanner(System.in);
      System.out.println("[게임]");
      int index = 0;
      String answer = null;
      boolean launch = true;
      int cnt = 3;
      int sum = 0;

      System.out.println();
      while (launch) {
         System.out.println("1. 회원가입 2. 로그인");
         int select = s.nextInt();

         Controller con;

         switch (select) {
         default:
            System.out.println("다시 선택해주세요.");
            launch = false;
            break;
         case 1:
            System.out.println("==== 회원가입 ====");
            System.out.println("사용 할 ID :");
            String id = s.next();
            System.out.println("사용 할 PW :");
            String pw = s.next();
            System.out.println("이름 :");
            String name = s.next();
            System.out.println("나이 :");
            int age = s.nextInt();
            System.out.println("사용 할 닉네임 :");
            String nickname = s.next();

            DTO dto = new DTO(id, pw, name, age, nickname);

            con = new Controller();
            con.sign_up(dto);
            break;

         case 2:
            System.out.print("ID : ");
            id = s.next();
            System.out.print("PW : ");
            pw = s.next();

            dto = new DTO(id, pw);

            con = new Controller();

            System.out.println(con.login(dto));

            if (con.login(dto).equals("로그인 성공")) {

               System.out.println("==== 게임 룰 ====");
               System.out.println("기회는 총 세 번!!"); 
               System.out.println("게임시작을 누르시고 드라마 혹은 영화 카테고리를 선택해주세요");
               System.out.println("선택하시면 문제가 출제가 됩니다.");
               System.out.println("노래를 듣고 해당 노래가 나오는 드라마(영화) 제목을 맞춰주세요.");
               
               System.out.println("[1]게임시작  [2]게임종료  [3]랭킹보기");
               int select2 = s.nextInt();

               while (launch) {
                  if (select2 == 1) {
                     System.out.println("[1]드라마  [2]영화");
                     int select3 = s.nextInt();

                     if (select3 == 1) { // 드라마 선택
                        s.nextLine();
                        con = new Controller();
                        con.drama_music_play(index);
                        System.out.println("======== " + (index + 1) + "번 문제" + " ========");

                        while (launch) {
                           System.out.println("노래를 듣고 드라마 제목을 맞춰주세요." + "(" + con.dramaPoints(index) + "점)");
                           System.out.println("드라마 ost 재생 중");
//                           s.nextLine();
                           System.out.print("정답 : ");
                           answer = s.nextLine();
                           String q1 = con.questionsDrama(index);

                           if (answer.equals(q1)) {
                              con.musicStop();
//                              System.out.println("정답입니다");
                              System.out.println(" ");
                              sum += Integer.parseInt(con.dramaPoints(index));
                              con = new Controller();
                              con.sumDrama(dto, sum);
                              if (index != 9) {
                                 index++;
                                 con.drama_music_play(index);
                                 System.out.println("======== " + (index + 1) + "번 문제" + " ========");
                              } else {
                                 System.out.println("======== 총 획득 점수 ========");
                                 dto = new DTO(id);
                                 con = new Controller();
                                 con.userRanking(dto);
                                 System.out.println("===========================");
                                 System.out.println();
                                 System.out.println("======== 랭킹 순위 ========");
                                 con.ranking();
                                 con.musicStop();
                                 System.out.println("게임종료");
                                 launch = false;
                                 break;

                              }
                           } else {
                              cnt--;
                              if (cnt >= 0) {
                                 System.out.println("땡! 기회가 " + cnt + "번 남았습니다.");
                                 if (cnt == 0) {
                                    con.musicStop();
                                    System.out.println("실패! 게임이 종료됩니다.");
                                    System.out.println("======== 총 획득 점수 ========");
//                                    System.out.println( dto.get_id());
                                   
                                    con = new Controller();
                                    con.userRanking(dto);
                                    System.out.println("===========================");
                                    System.out.println();
                                    System.out.println("======== 랭킹 순위 ========");
                                    con.ranking();
                                    launch = false;
                                    break;
                                 }
                              }
                           }
                        }
//                        System.out.println("다시 선택해주세요.");
                     } else if (select == 2) { // 영화 선택
                        s.nextLine();
                        con = new Controller();
                        con.movie_music_play(index);
                        System.out.println("======== " + (index + 1) + "번 문제"+ " ========");

                        while (launch) {
                           System.out.println("노래를 듣고 영화 제목을 맞춰주세요." + "(" + con.moviePoints(index) + "점)");
                           System.out.println("영화 ost 재생 중");
                           System.out.print("정답 : ");
                           answer = s.nextLine();
                           String q2 = con.questionsMovie(index);

                           if (answer.equals(q2)) {
                              con.musicStop();
                              System.out.println("정답입니다");
                              System.out.println(" ");
                              
                              sum += Integer.parseInt(con.moviePoints(index));
                              con = new Controller();
                              con.sumMovie(dto, sum);

                              if (index != 9) {
                                 index++;
                                 con.movie_music_play(index);
                                 System.out.println("======== " + (index + 1) + "번 문제 ========");
                              } else {
                                 System.out.println("======== 총 획득 점수 ========");
                                 con = new Controller();
                                 con.userRanking(dto);
                                 System.out.println("===========================");
                                 System.out.println();
                                 System.out.println("======== 랭킹 순위 ========");
                                 con.ranking();
                                 con.musicStop();
                                 System.out.println("게임종료");
                                 launch = false;
                                 break;
                              }
                           } else {
                               cnt--;
                               if (cnt >= 0) {
                                  System.out.println("땡! 기회가 " + cnt + "번 남았습니다.");
                                  if (cnt == 0) {
                                     con.musicStop();
                                     System.out.println("실패! 게임이 종료됩니다.");
                                     System.out.println("======== 총 획득 점수 ========");
                                     con = new Controller();
                                     con.userRanking(dto);
                                     System.out.println("===========================");
                                     System.out.println();
                                     System.out.println("======== 랭킹 순위 ========");
                                     con.ranking();
                                     launch = false;
                                     break;
                                  }
                               }
                            }
                        }
                     } 
                  }else if(select2 == 2) {
                     System.out.println("게임이 종료됩니다.");
                     launch = false;
                     break;
                  }else if(select2 == 3) {
                     System.out.println("======== 나의 순위 ========");
//                     dto = new DTO(id);
                     con = new Controller();
                      con.userRanking(dto);
                      System.out.println("===========================");
                      System.out.println();
                      System.out.println("======== 랭킹 순위 ========");
                      con.ranking();
                      launch = false;
                      break;
                  }
                  
               }
            }
         }
      }
   }
}
package Controller;

import java.util.ArrayList;

import Model.DAO;
import Model.DTO;
import Model.PointsDTO;
import Model.musicvo;
import javazoom.jl.player.MP3Player;



public class test {

   MP3Player mp3;
      DAO dao;
      ArrayList<musicvo> musicList = new ArrayList<>();
      
      //생성자
      public Controller() {
         mp3=new MP3Player();
         dao = new Model.DAO();
         
         musicList = dao.get_DramaList();
      }

      public void musicPlay(int index) {
            mp3.play(musicList.get(index).get_path());
            
            }


   public void sign_up(DTO dto) {
      DAO dao = new DAO();

      int cnt = dao.sign_up(dto);

      if (cnt > 0) {
         System.out.println("회원가입 성공");
      } else {
         System.out.println("회원가입 실패");
      }

   }

   public String login(DTO dto) {
      DAO dao = new DAO();

      dao.login(dto);

      dto = dao.login(dto);

      if (dto != null) {
         return "로그인 성공";
//         System.out.println("로그인 성공"); 
      } else {
//         System.out.println("로그인 실패");
         return "로그인 실패";
      }

   }
   
   // 드라마 선택
   public String questionsDrama(int index) {
         
//      List<String> list = Arrays.asList("a");
      DAO dao = new DAO();
      ArrayList<DTO> questionsDrama = dao.questionsDrama();
//      System.out.println(questionsDrama.get(index).getPoints() + "점");
//      System.out.println("드라마 ost 재생 중");
      
      System.out.println(questionsDrama.get(index).getD_m_title());
      return questionsDrama.get(index).getD_m_title();
      
//      for(int i = 0; i < list.size(); i++) {
//         if(!list.contains(questionsDrama))
//            list.add(questionsDrama);
//      }
      
//      System.out.println(new HashSet<>(Arrays.asList(dao.questionsDrama())));
      
//      for(int i = 0; i < questionsDrama.size(); i++) {
//         for(int j = 0; j < questionsDrama.size(); j++) {
//            if(i == j) {
//               
//            }else if(questionsDrama.get(j).equals(questionsDrama.get(i))) {
//               questionsDrama.remove(j);
//            }
//         }
//      }
//      HashSet<String> drama = new HashSet<String>();
//      System.out.println(questionsDrama.get(index).getMusic_route());
//      System.out.println(questionsDrama.size());
//      System.out.println(index);
//      index++;
//      if(index >= questionsDrama.size()) {
//         index = 0;
//      }
//      stopMusic();
//      questionsDrama(index);
//      return index;
      }

   // 영화 선택
   public String questionsMovie(int index) {
      DAO dao = new DAO();
      ArrayList<DTO> questionsMovie = dao.questionsMovie();
      System.out.println(questionsMovie.get(index).getMusic_route());
      System.out.println("영화 ost 재생 중");
      return questionsMovie.get(index).getMusic_route();
   }
   
   public void userRanking(DTO dto) {
         DAO dao = new DAO();
         dto = dao.userRanking(dto);
         
         if(dto != null) {
            System.out.print(dto.get_nickname() + " 님" + "\t");
            System.out.print("총점 : " + dto.getScore());
            System.out.println();
         }else {
            System.out.println("실패");
         }
      }

      public void ranking() {
         DAO dao = new DAO();
         int num = 0;

         ArrayList<DTO> ranking = dao.ranking();
         for (int i = 0; i < ranking.size(); i++) {
            num++;
            System.out.print(num + ". " + ranking.get(i).getNickname() + "님 \t");
            System.out.print(ranking.get(i).getScore() + "님" + "\t");
            System.out.println();
         }
      }

   public String dramaPoints(int index) {
      DAO dao = new DAO();
      
      ArrayList<DTO> dramaPoints = dao.dramaPoints();
      // System.out.println(dramaPoints.get(index).getPoints());
      return dramaPoints.get(index).get_id();
   }
      
      
      
   
}
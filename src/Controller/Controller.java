package Controller;

import java.util.ArrayList;

import Model.DAO;
import Model.DTO;
import Model.musicvo;
import javazoom.jl.player.MP3Player;

public class Controller {

   MP3Player mp3;
   DAO dao;
   ArrayList<musicvo> musicList = new ArrayList<>();

   // 생성자
   public Controller() {
      mp3 = new MP3Player();
      dao = new Model.DAO();
   }

   public void drama_music_play(int index) {
      musicList = dao.get_DramaList();
      mp3.play(musicList.get(index).get_path());
   }

   public void movie_music_play(int index) {
      musicList = dao.get_MovieList();
      mp3.play(musicList.get(index).get_path());
   }

   public void musicStop() {
      mp3.stop();
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
      } else {
         return "로그인 실패";
      }

   }

   // 드라마 선택
   public String questionsDrama(int index) {

      DAO dao = new DAO();
      ArrayList<DTO> questionsDrama = dao.questionsDrama();
      return questionsDrama.get(index).getD_m_title();
   }

   // 영화 선택
   public String questionsMovie(int index) {

      DAO dao = new DAO();
      ArrayList<DTO> questionsMovie = dao.questionsMovie();
      return questionsMovie.get(index).getD_m_title();
   }

   public void userRanking(DTO dto) {
	      DAO dao = new DAO();
	      dto = dao.userRanking(dto);
//	      System.out.println("안녕 ");

	      if (dto != null) {
	         System.out.print(dto.getNickname() + " 님" + "\t");
	         System.out.print("총점 : " + dto.getScore());
	         System.out.println();
	      } else {
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

   // public void sumDrama(DTO dto, int sum) {
//             DAO dao = new DAO();
//               int cnt = dao.sumDrama(sum, dto);
//               
//               if(cnt > 0) {
//                  System.out.println("성공");
//               }else {
//                  System.out.println("실패");
//               }
   //
//            }

   public String moviePoints(int index) {
      DAO dao = new DAO();

      ArrayList<DTO> moviePoints = dao.moviePoints();
      // System.out.println(dramaPoints.get(index).getPoints());
      return moviePoints.get(index).get_id();
   }

// 드라마총합
   public void sumDrama(DTO dto, int sum) {
      DAO dao = new DAO();
      int cnt = dao.sumDrama(dto, sum);

      if (cnt > 0) {
         System.out.println("성공");
      } else {
         System.out.println("실패");
      }

   }

   // 영화총합
   public void sumMovie(DTO dto, int sum) {
      DAO dao = new DAO();
      int cnt = dao.sumMovie(dto, sum);

      if (cnt > 0) {
         System.out.println("성공");
      } else {
         System.out.println("실패");
      }

   }

//   public void dramaHints(int index) {
//      DAO dao = new DAO();
//      ArrayList<DTO> dramaHints = dao.dramaHints();
//
//      System.out.println(dramaHints.get(index).get_id());
//
//   }

}
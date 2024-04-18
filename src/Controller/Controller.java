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

   public Controller() { // 생성자
      mp3 = new MP3Player();
      dao = new Model.DAO();
   }

   public void drama_music_play(int index) { // 드라마 OST 재생
      musicList = dao.get_DramaList();
      mp3.play(musicList.get(index).get_path());
   }

   public void movie_music_play(int index) { // 영화 OST 출력
      musicList = dao.get_MovieList();
      mp3.play(musicList.get(index).get_path());
   }

   public void musicStop() { // OST 종료
      mp3.stop();
   }

   public void sign_up(DTO dto) { // 회원가입
      DAO dao = new DAO();
      int cnt = dao.sign_up(dto);

      if (cnt > 0) {
    	  System.out.println("회원가입 성공");
      }else{
    	  System.out.println("회원가입 실패");
      }
   }

   public String login(DTO dto) { // 로그인
      DAO dao = new DAO();
      dao.login(dto);
      dto = dao.login(dto);

      	if (dto != null) {
      		return "로그인 성공";
      	}else{
      		return "로그인 실패";
      	}
   }

   public String questionsDrama(int index) { // 드라마 문제
      DAO dao = new DAO();
      ArrayList<DTO> questionsDrama = dao.questionsDrama();
      return questionsDrama.get(index).getD_m_title();
   }

   public String questionsMovie(int index) { // 영화 문제
      DAO dao = new DAO();
      ArrayList<DTO> questionsMovie = dao.questionsMovie();
      return questionsMovie.get(index).getD_m_title();
   }

   public void userRanking(DTO dto) { // 유저 닉네임, 점수 출력
      DAO dao = new DAO();
      dto = dao.userRanking(dto);

      if (dto != null) {
         System.out.print(dto.get_nickname() + " 님" + "\t");
         System.out.print("총점 : " + dto.getScore());
         System.out.println();
      }else{
    	 System.out.println("실패");
      }
   }

   public void ranking() { // 상위 10명 랭킹 출력
      DAO dao = new DAO();
      int num = 0;
      
      ArrayList<DTO> ranking = dao.ranking();
      for (int i = 0; i < ranking.size(); i++) {
    	  num++;
    	  System.out.print(num + ". " + ranking.get(i).getNickname() + "님 \t");
    	  System.out.print(" \n \t" + ranking.get(i).getScore() + "점" + "\t");
    	  System.out.println();
      	}
   }

   public String dramaPoints(int index) { // 드라마 점수
      DAO dao = new DAO();
      ArrayList<DTO> dramaPoints = dao.dramaPoints();
      return dramaPoints.get(index).get_id();
   }

   public String moviePoints(int index) { // 영화 점수
      DAO dao = new DAO();
      ArrayList<DTO> moviePoints = dao.moviePoints();
      return moviePoints.get(index).get_id();
   }

   // 드라마총합
   public void sumDrama(DTO dto, int sum) { // 드라마 점수 총합 계산
      DAO dao = new DAO();
      int cnt = dao.sumDrama(dto, sum);

      if (cnt > 0) {
          System.out.println("정답입니다");
      } else {
         System.out.println("실패");
      }

   }

   // 영화총합
   public void sumMovie(DTO dto, int sum) { // 영화 점수 총합
      DAO dao = new DAO();
      int cnt = dao.sumDrama(dto, sum);
      
      	if (cnt > 0) {
      		System.out.println("성공");
      	}else{
      		System.out.println("실패");
      	}
   }

   public void dramaHints(int index) { // 드라마 힌트
      DAO dao = new DAO();
      ArrayList<DTO> dramaHints = dao.dramaHints();
      System.out.println(dramaHints.get(index).get_id());
   }

   public void movieHints(int index) { // 영화 힌트
	      DAO dao = new DAO();
	      ArrayList<DTO> movieHints = dao.movieHints();
	      System.out.println(movieHints.get(index).get_id());
	   }

}
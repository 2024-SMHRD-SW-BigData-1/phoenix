package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.DTO;

public class DAO {
   Connection conn = null;
   PreparedStatement psmt = null;
   ResultSet rs = null;
   int cnt = 0;

   public ArrayList<musicvo> get_DramaList() {

      ArrayList<musicvo> Drama_list = new ArrayList<>();

      Drama_list.add(new musicvo("눈물의 여왕", "C:/Users/smhrd/Desktop/mini_music_drama/부석순 자꾸만 웃게 돼 눈물의여왕.mp3"));
      Drama_list.add(new musicvo("신사와 아가씨", "C:/Users/smhrd/Desktop/mini_music_drama/임영웅 사랑은 늘 도망가 신사와 아가씨.mp3"));
      Drama_list.add(new musicvo("도깨비", "C:/Users/smhrd/Desktop/mini_music_drama/에일리 첫눈처럼 너에게 가겠다 도깨비.mp3"));
      Drama_list.add(new musicvo("아내의 유혹", "C:/Users/smhrd/Desktop/mini_music_drama/차수경 용서 못해 아내의유혹.mp3"));
      Drama_list.add(new musicvo("시크릿가든", "C:/Users/smhrd/Desktop/mini_music_drama/김범수 나타나 시크릿 가든.mp3"));
      Drama_list.add(
            new musicvo("스카이캐슬", "C:/Users/smhrd/Desktop/mini_music_drama/하진 We All Lie We all lie 스카이캐슬.mp3"));
      Drama_list.add(new musicvo("미생", "C:/Users/smhrd/Desktop/mini_music_drama/한희정 Tomorrow 미생.mp3"));
      Drama_list
            .add(new musicvo("시그널", "C:/Users/smhrd/Desktop/mini_music_drama/정차식 나는 너를 I will forget you 시그널.mp3"));
      Drama_list.add(new musicvo("미안하다 사랑하다", "C:/Users/smhrd/Desktop/mini_music_drama/박효신 눈의 꽃 미안하다사랑한다.mp3"));
      Drama_list.add(new musicvo("연애시대", "C:/Users/smhrd/Desktop/mini_music_drama/스윗소로우 아무리 생각해도 난 너를 연애시대.mp3"));

      return Drama_list;
   }

   public ArrayList<musicvo> get_MovieList() {

      ArrayList<musicvo> Drama_list = new ArrayList<>();

      Drama_list.add(new musicvo("클래식",
            "C:/Users/smhrd/Desktop/mini_music_drama/자전거탄풍경 너에게 난 나에게 넌 Me to You You to Me 클래식.mp3"));
      Drama_list.add(
            new musicvo("겨울왕국", "C:/Users/smhrd/Desktop/mini_music_drama/Idina Menzel 이디나 멘젤 Let It Go 겨울왕국.mp3"));
      Drama_list.add(new musicvo("타이타닉",
            "C:/Users/smhrd/Desktop/mini_music_drama/Céline Dion My Heart Will Go On 타이타닉.mp3"));
      Drama_list
            .add(new musicvo("웰컴 투 동막골", "C:/Users/smhrd/Desktop/mini_music_drama/A Waltz of Sleigh 웰컴 투 동막골.mp3"));
      Drama_list.add(new musicvo("비긴어게인",
            "C:/Users/smhrd/Desktop/mini_music_drama/너 같은 사람이 필요해 Adam Levine No One Else Like You 비긴어게인.mp3"));
      Drama_list.add(new musicvo("범죄와의 전쟁", "C:/Users/smhrd/Desktop/mini_music_drama/장기하와 얼굴들 풍문으로 들었소 범죄와의 전쟁.mp3"));
      Drama_list.add(
            new musicvo("몬스터 주식회사", "C:/Users/smhrd/Desktop/mini_music_drama/If I Didn t Have You 몬스터 주식회사.mp3"));
      Drama_list.add(new musicvo("살인의 추억", "C:/Users/smhrd/Desktop/mini_music_drama/우울한 편지 유재하 살인의 추억.mp3"));
      Drama_list.add(new musicvo("과속스캔들", "C:/Users/smhrd/Desktop/mini_music_drama/박보영 아마도 그건 과속스캔틀OST.mp3"));
      Drama_list
            .add(new musicvo("하울의 움직이는 성", "C:/Users/smhrd/Desktop/mini_music_drama/인생의 회전목마 하울의 움직이는 성 OST.mp3"));

      return Drama_list;
   }

   public void Dbopen() {

      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         String id = "campus_24SW_BIG_p1_4";
         String pw = "smhrd4";
         String url = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1524:xe";

         conn = DriverManager.getConnection(url, id, pw);

      } catch (ClassNotFoundException e) {
         System.out.println("동적로딩 실패");
         e.printStackTrace();
      } catch (SQLException e) {
         System.out.println("권한확인 실패");
         e.printStackTrace();
      }
   }

   public void Dbclose() { 
      try {
         if (rs != null)
            rs.close();
         if (psmt != null)
            psmt.close();
         if (conn != null)
            conn.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public int sign_up(DTO dto) { // 회원가입

      Dbopen();

      try {
         String sql = "INSERT INTO USER_INFO VALUES(?, ?, ?, ?, ?)";
         psmt = conn.prepareStatement(sql);

         psmt.setString(1, dto.get_id());
         psmt.setString(2, dto.get_pw());
         psmt.setString(3, dto.getName());
         psmt.setInt(4, dto.getAge());
         psmt.setString(5, dto.get_nickname());

         cnt = psmt.executeUpdate();

      } catch (SQLException e) {
         System.out.println("SQL 실행 에러");
         e.printStackTrace();
      } finally {
         Dbclose();
      }
      return cnt;
   }

   public DTO login(DTO dto) { // 로그인

      Dbopen();

      DTO login_dto = null;

      try {
         String sql = "SELECT * FROM USER_INFO WHERE USER_ID=? and USER_PW=?";
         psmt = conn.prepareStatement(sql);

         psmt.setString(1, dto.get_id());
         psmt.setString(2, dto.get_pw());

         rs = psmt.executeQuery();

         if (rs.next()) {
            String id = rs.getString(1); // id
            String pw = rs.getString(2); // pw
            String name = rs.getString(3); // name
            int age = rs.getInt(4); // age
            String nickname = rs.getString(5); // nickname

            login_dto = new DTO(id, pw, name, age, nickname);

         }

      } catch (SQLException e) {
         System.out.println("데이터 베이스 오류");
         e.printStackTrace();
      } finally {
         Dbclose();
      }
      return login_dto;
   }

   public ArrayList<DTO> questionsDrama() { // 드라마 문제
      Dbopen();

      ArrayList<DTO> questionsDrama = new ArrayList<DTO>();

      String sql = "SELECT * FROM QUESTION WHERE D_M_CD = 1";
      try {
         psmt = conn.prepareStatement(sql);

         rs = psmt.executeQuery(sql);
         while (rs.next()) {
            String d_m_title = rs.getString("D_M_TITLE");
            String music_route = rs.getString("MUSIC_ROUTE");
            String hint1 = rs.getString("HINT1");
            String hint2 = rs.getString("HINT2");
            String points = rs.getString("POINTS");
            String level_rate = rs.getString("LEVEL_RATE");
            int d_m_cd = rs.getInt("D_M_CD");

            DTO dto = new DTO(d_m_title, music_route, hint1, hint2, points, level_rate, d_m_cd);
            questionsDrama.add(dto);
         }

      } catch (SQLException e) {
         System.out.println("데이터 베이스 오류");
         e.printStackTrace();
      } finally {
         Dbclose();
      }
      return questionsDrama;
   }

   public ArrayList<DTO> questionsMovie() { // 영화 문제
      Dbopen();
      ArrayList<DTO> questionsMovie = new ArrayList<DTO>();

      String sql = "SELECT * FROM QUESTION WHERE D_M_CD = 2";

      try {
         psmt = conn.prepareStatement(sql);

         rs = psmt.executeQuery();

         while (rs.next()) {
            String d_m_title = rs.getString("D_M_TITLE");
            String music_route = rs.getString("MUSIC_ROUTE");
            String hint1 = rs.getString("HINT1");
            String hint2 = rs.getString("HINT2");
            String points = rs.getString("POINTS");
            String level_rate = rs.getString("LEVEL_RATE");
            int d_m_cd = rs.getInt("D_M_CD");

            DTO dto = new DTO(d_m_title, music_route, hint1, hint2, points, level_rate, d_m_cd);
            questionsMovie.add(dto);
         }

      } catch (SQLException e) {
         System.out.println("데이터 베이스 오류");
         e.printStackTrace();
      } finally {
         Dbclose();
      }

      return questionsMovie;
   }

   public DTO userRanking(DTO dto) { // 유저 닉네임, 점수 출력
      Dbopen();
      String sql = "SELECT NICKNAME, SCORE FROM USER_INFO WHERE user_id = ?";
      
      try {

         psmt = conn.prepareStatement(sql);
         psmt.setString(1, dto.get_id());

         rs = psmt.executeQuery();

         if (rs.next()) {
            String nickname = rs.getString("nickname");
            int score = rs.getInt("score");

            dto = new DTO(nickname, score);
         }

      } catch (SQLException e) {
         System.out.println("데이터 베이스 오류");
         e.printStackTrace();
      } finally {
         Dbclose();
      }
      return dto;
   }

   public ArrayList<DTO> ranking() { // 상위 10명 랭킹 출력
      Dbopen();

      ArrayList<DTO> ranking = new ArrayList<DTO>();

       String sql = "SELECT NICKNAME, SCORE, RANK() OVER(ORDER BY SCORE DESC) AS 순위 FROM USER_INFO WHERE SCORE IS NOT NULL ORDER BY SCORE DESC";
      try {

         psmt = conn.prepareStatement(sql);
         rs = psmt.executeQuery();

         while (rs.next()) {
            String nickname = rs.getString("nickname");
            int score = rs.getInt("score");

            DTO dto = new DTO(nickname, score);
            ranking.add(dto);
         }

      } catch (SQLException e) {
         System.out.println("데이터 베이스 오류");
         e.printStackTrace();
      } finally {
         Dbclose();
      }
      return ranking;
   }

   public ArrayList<DTO> dramaPoints() { // 드라마 점수
      Dbopen();
      ArrayList<DTO> dramaPoints = new ArrayList<DTO>();

      String sql = "SELECT POINTS, HINT1 FROM QUESTION WHERE D_M_CD = 1";

      try {
         psmt = conn.prepareStatement(sql);
         rs = psmt.executeQuery();

         while (rs.next()) {
            String points = rs.getString("points");
            String hint1 = rs.getString("hint1");
            DTO dto = new DTO(points, hint1);
            dramaPoints.add(dto);
         }

      } catch (SQLException e) {
         System.out.println("데이터 베이스 오류");
         e.printStackTrace();
      } finally {
         Dbclose();
      }
      return dramaPoints;
   }

   public ArrayList<DTO> moviePoints() { // 영화 점수
      Dbopen();
      ArrayList<DTO> moviePoints = new ArrayList<DTO>();

      String sql = "SELECT POINTS, HINT1 FROM QUESTION WHERE D_M_CD = 2";

      try {
         psmt = conn.prepareStatement(sql);
         rs = psmt.executeQuery();

         while (rs.next()) {
            String points = rs.getString("points");
            String hint1 = rs.getString("hint1");
            DTO dto = new DTO(points, hint1);
            moviePoints.add(dto);
         }

      } catch (SQLException e) {
         System.out.println("데이터 베이스 오류");
         e.printStackTrace();
      } finally {
         Dbclose();
      }
      return moviePoints;
   }

   public int sumDrama(DTO dto, int sum) { // 드라마 점수 총합
      Dbopen();
      int cnt = 0;
      String sql = "UPDATE USER_INFO SET SCORE = ? WHERE USER_ID = ?";
      try {
         psmt = conn.prepareStatement(sql);
         psmt.setInt(1, sum);
         psmt.setString(2, dto.get_id());

         cnt = psmt.executeUpdate();

      } catch (SQLException e) {
         System.out.println("데이터 베이스 오류");
         e.printStackTrace();
      } finally {
         Dbclose();
      }
      return cnt;
   }

   public ArrayList<DTO> dramaHints() { // 드라마 힌트
      Dbopen();

      ArrayList<DTO> dramaHints = new ArrayList<DTO>();
      String sql = "SELECT HINT1 FROM QUESTION WHERE D_M_CD = 1";

      try {
         psmt = conn.prepareStatement(sql);
         rs = psmt.executeQuery();

         while (rs.next()) {
            String hint1 = rs.getString("HINT1");
            DTO dto = new DTO(hint1);
            dramaHints.add(dto);
         }

      } catch (SQLException e) {
         System.out.println("데이터 베이스 오류");
         e.printStackTrace();
      }

      return dramaHints;
   }
   
   public ArrayList<DTO> movieHints() { // 영화 힌트
	      Dbopen();

	      ArrayList<DTO> movieHints = new ArrayList<DTO>();
	      String sql = "SELECT HINT1 FROM QUESTION WHERE D_M_CD = 2";

	      try {
	         psmt = conn.prepareStatement(sql);
	         rs = psmt.executeQuery();

	         while (rs.next()) {
	            String hint1 = rs.getString("HINT1");
	            DTO dto = new DTO(hint1);
	            movieHints.add(dto);
	         }

	      } catch (SQLException e) {
	         System.out.println("데이터 베이스 오류");
	         e.printStackTrace();
	      }

	      return movieHints;
	   }
}
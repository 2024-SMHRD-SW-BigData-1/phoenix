package Model;

public class DTO {

	// 로그인, 회원가입
	private String id;
	private String pw;
	private String name;
	private int age;
	private String nickname;

	public DTO(String id, String pw, String name, int age, String nickname) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.age = age;
		this.nickname = nickname;
	}

	public DTO(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}

	public DTO(String id) {
		this.id = id;
	}

	public String get_id() {
		return id;
	}

	public String get_pw() {
		return pw;
	}

	public String get_nickname() {
		return nickname;
	}

	public void set_id(String id) {
		this.id = id;
	}

	public void set_pw(String pw) {
		this.pw = pw;
	}

	public void set_nickname(String nickname) {
		this.nickname = nickname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	// ==========================================================================================

	// 문제 출제
	private String d_m_title;
	private String music_route;
	private String hint1;
	private String hint2;
	private String points;
	private String level_rate;
	private int d_m_cd;

	public DTO(String d_m_title, String music_route, String hint1, String hint2, String points, String level_rate,
			int d_m_cd) {
		this.d_m_title = d_m_title;
		this.music_route = music_route;
		this.hint1 = hint1;
		this.hint2 = hint2;
		this.points = points;
		this.level_rate = level_rate;
		this.d_m_cd = d_m_cd;
	}

	public DTO(String nickname, int score) {
		this.nickname = nickname;

	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getD_m_title() {
		return d_m_title;
	}

	public void setD_m_title(String d_m_title) {
		this.d_m_title = d_m_title;
	}

	public String getMusic_route() {
		return music_route;
	}

	public void setMusic_route(String music_route) {
		this.music_route = music_route;
	}

	public String getHint1() {
		return hint1;
	}

	public void setHint1(String hint1) {
		this.hint1 = hint1;
	}

	public String getHint2() {
		return hint2;
	}

	public void setHint2(String hint2) {
		this.hint2 = hint2;
	}

	public String getScore() {
		return points;
	}

	public void setScore(String points) {
		this.points = points;
	}

	public String getLevel_rate() {
		return level_rate;
	}

	public void setLevel_rate(String level_rate) {
		this.level_rate = level_rate;
	}

	public int getD_m_cd() {
		return d_m_cd;
	}

	public void setD_m_cd(int d_m_cd) {
		this.d_m_cd = d_m_cd;
	}

	// ========================================================================================

}
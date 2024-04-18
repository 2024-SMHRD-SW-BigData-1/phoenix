package Controller;

import java.util.ArrayList;

import Model.music_dao;
import Model.musicvo;
import javazoom.jl.player.MP3Player;

public class music_ctrl {
	MP3Player mp3;
	music_dao dao;
	ArrayList<musicvo> musicList = new ArrayList<>();
	
    //생성자
	public music_ctrl() {
		mp3=new MP3Player();
		dao = new music_dao();
		
		
	}
    //노래 멈추는 기능
	public void musicStop() {
			mp3.stop();
	}
	
   //음악을 재생하는 코드
	public void musicPlay(int index) {
		
	}
}


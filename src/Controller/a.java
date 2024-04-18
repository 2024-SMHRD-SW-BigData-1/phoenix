package Controller;

import java.util.ArrayList;

import Model.DAO;
import Model.musicvo;
import javazoom.jl.player.MP3Player;

public class a {
	
	MP3Player mp3;
	DAO dao;
	ArrayList<musicvo> musicList = new ArrayList<>();
	
    //생성자
	public a() {
		mp3=new MP3Player();
		dao = new Model.DAO();
		
		musicList = dao.get_DramaList();
	}
    //노래 멈추는 기능
	public void musicStop() {
		if(mp3.isPlaying()) {
			mp3.stop();
		}
	}

   //노래 목록을 가지고 오는 기능
	public ArrayList<musicvo> showList() {
		return musicList;
	}

   //음악 샐항한 후 노래 제목을 return 하는 코드
	public String musicPlay(int index) {
		mp3.play(musicList.get(index).get_path());
		return musicList.get(index).get_title();
	}
	
	//선택한 번호에 따라 index 번호를 증감한 후 index를 return 하는 코드
	public int nextNprePlay(int index, int choice) {
		musicStop();
		
		if(choice == 3) {
			index++;
		}else if(choice ==4) {
			index--;
		}
		
		if (index >= musicList.size()) {
			index = 0;
		}else if(index <0) {
			index = musicList.size()-1;
		}
		
		
		return index;

	}

}



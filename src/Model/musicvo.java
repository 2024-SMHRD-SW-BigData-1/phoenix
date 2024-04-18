package Model;

public class musicvo {

	private String path;
	private String title;

	public musicvo(String title, String path) {
		this.title = title;
		this.path = path;
	}

	public String get_path() {
		return path;
	}

	public String get_title() {
		return title;
	}

}

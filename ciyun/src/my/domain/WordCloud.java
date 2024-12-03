package my.domain;

public class WordCloud {
	private String ismobile;
	private int num;
	public String getIsmobile() {
		return ismobile;
	}
	public void setIsmobile(String ismobile) {
		this.ismobile = ismobile;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "WordCloud [ismobile=" + ismobile + ", num=" + num + "]";
	}
	public WordCloud() {
		
	}
}

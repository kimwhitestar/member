package study.database;

public class LoginVO {
	private int idx;
	private String mid;
	private String pwd;
	private String name;
	private int point;
	private String lastDate;
	private int vCount;
	
	//Alt s r Alt a Alt r
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	public int getvCount() {
		return vCount;
	}
	public void setvCount(int vCount) {
		this.vCount = vCount;
	}

	//Alt s s s enter enter
	@Override
	public String toString() {
		return "LoginVO [idx=" + idx + ", mid=" + mid + ", pwd=" + pwd + ", name=" + name + ", point=" + point
				+ ", lastDate=" + lastDate + ", vCount=" + vCount + "]";
	}
}

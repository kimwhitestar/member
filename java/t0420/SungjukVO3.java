package t0420;

import java.util.Arrays;

public class SungjukVO3 {
	private String name;
	private String gender;
	private String no;
	private String hakyun;
	private int kor;
	private int eng;
	private int mat;
	private int tot;
	private double avg;
	private String grade;
	
	public SungjukVO3() {}
	
	public SungjukVO3(String name, String gender, String no, String hakyun, int kor, int eng, int mat, int tot, double avg,
			String grade) {
		super();
		this.name = name;
		this.gender = gender;
		this.no = no;
		this.hakyun = hakyun;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
		this.tot = tot;
		this.avg = avg;
		this.grade = grade;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getHakyun() {
		return hakyun;
	}
	public void setHakyun(String hakyun) {
		this.hakyun = hakyun;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMat() {
		return mat;
	}
	public void setMat(int mat) {
		this.mat = mat;
	}
	public int getTot() {
		return tot;
	}
	public void setTot(int tot) {
		this.tot = tot;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "SungjukVO3 [name=" + name + ", gender=" + gender + ", no=" + no + ", hakyun=" + hakyun + ", kor=" + kor
				+ ", eng=" + eng + ", mat=" + mat + ", tot=" + tot + ", avg=" + avg + ", grade=" + grade + "]";
	}
}
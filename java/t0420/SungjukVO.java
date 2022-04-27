package t0420;

import java.util.Arrays;

public class SungjukVO {
	private String name;
	private String gender;
	private String no;
	private String hakyun;
	private String[] language;
	private String[] sports;
	private String strLanguage;
	private String strSports;
	private String otherSports;
	private int kor;
	private int eng;
	private int mat;
	private int tot;
	private double avg;
	private String grade;
	
	public SungjukVO() {}
	
	public SungjukVO(String name, String gender, String no, String hakyun, String[] language, String[] sports,
			String strLanguage, String strSports, String otherSports, int kor, int eng, int mat, int tot, double avg,
			String grade) {
		super();
		this.name = name;
		this.gender = gender;
		this.no = no;
		this.hakyun = hakyun;
		this.language = language;
		this.sports = sports;
		this.strLanguage = strLanguage;
		this.strSports = strSports;
		this.otherSports = otherSports;
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
	public String[] getLanguage() {
		return language;
	}
	public void setLanguage(String[] language) {
		this.language = language;
	}
	public String[] getSports() {
		return sports;
	}
	public void setSports(String[] sports) {
		this.sports = sports;
	}
	public String getOtherSports() {
		return otherSports;
	}
	public void setOtherSports(String otherSports) {
		this.otherSports = otherSports;
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
	public String getStrLanguage() {
		return strLanguage;
	}
	public void setStrLanguage(String strLanguage) {
		this.strLanguage = strLanguage;
	}
	public String getStrSports() {
		return strSports;
	}
	public void setStrSports(String strSports) {
		this.strSports = strSports;
	}

	@Override
	public String toString() {
		return "SungjukVO [name=" + name + ", gender=" + gender + ", no=" + no + ", hakyun=" + hakyun + ", language="
				+ Arrays.toString(language) + ", sports=" + Arrays.toString(sports) + ", strLanguage=" + strLanguage
				+ ", strSports=" + strSports + ", otherSports=" + otherSports + ", kor=" + kor + ", eng=" + eng + ", mat=" + mat
				+ ", tot=" + tot + ", avg=" + avg + ", grade=" + grade + "]";
	}
	
}

package unsw.enrolment;

public interface Ass extends Subject {
	public void setMark(boolean avgNotSum, int mark, int max);
	public int getMark();
	public int getMax();
}

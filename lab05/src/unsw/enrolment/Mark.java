package unsw.enrolment;

public interface Mark {

	public int getMark();
	public int getMax();
	public boolean updateMark(String assessment, int mark, int max);
	public String getAssessment();
	public Mark removeMark(String label);
}

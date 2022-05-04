package unsw.enrolment;

public class Grade {
    private int mark;
    private String grade;
    
    public Grade() {
    	this.mark = 0;
    	this.grade = "N/A";
    }
    
    public int getGrade() {
    	return this.mark;
    }
    
    public void setMark(int mark) {
    	this.mark = mark;
    	if (this.mark < 50) this.grade = "FL";
    	else if (this.mark >= 50 && this.mark < 65) this.grade = "PS";
    	else if (this.mark >= 65 && this.mark < 75) this.grade = "CR";
    	else if (this.mark >= 75 && this.mark < 85) this.grade = "DN";
    	else this.grade = "HD";
    }
}

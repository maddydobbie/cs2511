package unsw.enrolment;

public class Enrolment {

    private CourseOffering offering;
    private Grade grade;
    private Student student;

    public Enrolment(CourseOffering offering, Student student) {
        this.offering = offering;
        this.student = student;
        this.grade = new Grade();
    }

    public Course getCourse() {
        return offering.getCourse();
    }

    public String getTerm() {
        return offering.getTerm();
    }
    
    
    public boolean hasPassed() {
    	if (this.grade.getGrade() < 50) return false;
    	return true;
    }
    
    public void setMark(int mark) {
    	this.grade.setMark(mark);
    }

}

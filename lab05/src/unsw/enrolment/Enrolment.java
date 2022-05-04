package unsw.enrolment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Enrolment implements Observer {

    private CourseOffering offering;
    private Grade grade;
    private Student student;
    private List<Session> sessions;
    private List<Ass> assessments;
    private CalculatedMark marks;

    public Enrolment(CourseOffering offering, Student student, Session... sessions) {
        this.offering = offering;
        this.student = student;
        this.grade = null; // Student has not completed course yet.
        student.addEnrolment(this);
        offering.addEnrolment(this);
        this.sessions = new ArrayList<>();
        for (Session session : sessions) {
            this.sessions.add(session);
        }
        this.assessments = new ArrayList<>();
        this.marks = new CalculatedMark("course", new SumCalculator());
    }

    public Course getCourse() {
        return offering.getCourse();
    }

    public String getTerm() {
        return offering.getTerm();
    }

    public boolean hasPassed() {
        return grade != null && grade.isPassing();
    }
    
    @Override
	public void update(String name, int mark, int max) {
    	System.out.println("hey x");
		String filename = offering.getCourse().getCourseCode() + "-" + offering.getTerm() + "-" + student.getZID();
		try {
			File file = new File(filename);
			PrintStream out = new PrintStream(new FileOutputStream(file, true));
			out.println(LocalDateTime.now() + " -- " + name + " = " + mark + "/" + max);
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
    
    public void addAss(Ass ass) {
    	this.assessments.add(ass);
    }

//    Whole course marks can no longer be assigned this way.
//    public void assignMark(int mark) {
//        grade = new Grade(mark);
//    }
    
    public void calculateMark() {
    	int sum = 0;
    	for (Ass curr : this.assessments) {
    		sum += curr.getMark();
    	}
    	this.grade = new Grade(sum);
    }


}

package unsw.enrolment;
import java.util.ArrayList;
import java.util.List;

public class Student {

    private String zid;
    private ArrayList<Enrolment> enrolments;

	public Student(String zid) {
        this.zid = zid;
        enrolments = new ArrayList<>();
    }

	public String getZID() {
		return zid;
	}

	public ArrayList<Enrolment> getEnrolments() {
		return enrolments;
	}
	
	public void addEnrolment(Enrolment enrol) {
		List<Course> prereqs = enrol.getCourse().getPrereqs();
		boolean flag = true;
		for (Course currPrereq : prereqs) {
			//each prereq, check studnet has passed by finding it in this.enrolments and finding it has passing grade
			//else set flag to false and break
			for (Enrolment currEnrol : this.enrolments) {
				if ((currEnrol.getCourse() == currPrereq)) {
					if (!currEnrol.hasPassed())
						flag = false;
						break;
				}
			}
			if (flag == false) break;
		}
		if (flag == true) {
			this.enrolments.add(enrol);
			System.out.println(this.zid + " has successfully been enrolled in " + enrol.getCourse().getCourseCode());
		} else System.out.println(this.zid + " has not been enrolled in " + enrol.getCourse().getCourseCode());
	}
	
	public Enrolment getEnrolment(String code) {
		for (Enrolment curr : this.enrolments) {
			if (curr.getCourse().getCourseCode() == code)
				return curr;
		}
		return null;
	}
}

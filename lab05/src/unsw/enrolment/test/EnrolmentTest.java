package unsw.enrolment.test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import unsw.enrolment.Assessment;
import unsw.enrolment.Course;
import unsw.enrolment.CourseOffering;
import unsw.enrolment.Enrolment;
import unsw.enrolment.Lecture;
import unsw.enrolment.Student;
import unsw.enrolment.Subass;
import unsw.enrolment.Tutorial;

public class EnrolmentTest {

    public static void main(String[] args) {

        // Create courses
        Course comp1511 = new Course("COMP1511", "Programming Fundamentals");
        Course comp1531 = new Course("COMP1531", "Software Engineering Fundamentals");
        comp1531.addPrereq(comp1511);
        Course comp2521 = new Course("COMP2521", "Data Structures and Algorithms");
        comp2521.addPrereq(comp1511);

        CourseOffering comp1511Offering = new CourseOffering(comp1511, "19T1");
        CourseOffering comp1531Offering = new CourseOffering(comp1531, "19T1");
        CourseOffering comp2521Offering = new CourseOffering(comp2521, "19T2");

        // Create some sessions for the courses
        Lecture lecture1511 = new Lecture(comp1511Offering, "Rex Vowels", DayOfWeek.TUESDAY, LocalTime.of(12, 0),LocalTime.of(14, 0), "Andrew Taylor");
        comp1511Offering.addSession(lecture1511);
        Lecture lecture1531 = new Lecture(comp1531Offering, "CLB 5", DayOfWeek.MONDAY, LocalTime.of(9, 0),LocalTime.of(11, 0), "Aarthi Natarajan");
        comp1531Offering.addSession(lecture1531);        
        Lecture lecture2521 = new Lecture(comp2521Offering, "Clancy Auditorium", DayOfWeek.FRIDAY, LocalTime.of(15, 0),LocalTime.of(17, 0), "Ashesh Mahidadia");
        comp2521Offering.addSession(lecture2521);
        
        Tutorial tute1531 = new Tutorial(comp1531Offering, "Quad 1041", DayOfWeek.WEDNESDAY, LocalTime.of(18, 0), LocalTime.of(19,0), "Hugh Chan");
        comp1531Offering.addSession(tute1531);
        
        // Create a student
        Student student = new Student("z5555555");

        // Enrol the student in COMP1511 for T1 (this should succeed)
        Enrolment comp1511Enrolment = comp1511Offering.enrol(student, lecture1511);
        if (comp1511Enrolment == null)
            System.out.println("Can't enrol in COMP1511");
        else {
        	student.addEnrolment(comp1511Enrolment);
        	System.out.println("Enrolled in COMP1511");
        }

        // Enrol the student in COMP1531 for T1 (this should fail as they
        // have not met the prereq)
        Enrolment comp1531Enrolment = comp1531Offering.enrol(student, lecture1531, tute1531);

        if (comp1531Enrolment == null)
            System.out.println("Can't enrol in COMP1531");
        else {
        	student.addEnrolment(comp1531Enrolment);
        	System.out.println("Enrolled in COMP1531");
        }

        // Give the student a passing grade for COMP1511
//       comp1511Enrolment.assignMark(65);

        // Assign marks for comp1511
        // Create assessments and sub-assessments
        
        Subass exam = new Subass("exam");
        Assessment prac = new Assessment("prac");
        Subass ass1 = new Subass("ass1");
        prac.addSubass(ass1);
        Assessment ass2 = new Assessment("ass2");
        prac.addSubass(ass2);
        Subass mile1 = new Subass("milestone1");
        ass2.addSubass(mile1);
        Subass mile2 = new Subass("milestone2");
        ass2.addSubass(mile1);
        comp1511Enrolment.addAss(exam);
        comp1511Enrolment.addAss(prac);
        
        ass1.regObserver(comp1511Enrolment);
        mile1.regObserver(comp1511Enrolment);
        mile2.regObserver(comp1511Enrolment);
        ass2.regObserver(comp1511Enrolment);
        exam.regObserver(comp1511Enrolment);
        prac.regObserver(comp1511Enrolment);
        
        ass1.setMark(true, 11, 20);

        // TODO Give the student a passing mark for milestone 1 of assignment 2
        mile1.setMark(true, 15, 20);

        // TODO Give the student a passing mark for milestone 2 of assignment 2
        mile2.setMark(true, 31, 40);
        
        // TODO Give the student an assignment 2 mark which is the average of
        // milestone 1 and 2
        ass2.setMark(true, 0, 0);
        
        // TODO Give the student a prac mark which is the sum of assignment 1
        // and 2
        prac.setMark(false, 0, 0);
        
        // TODO Give the student a passing exam mark.
        exam.setMark(true, 30, 50);
        
        comp1511Enrolment.calculateMark();
        
        // Enrol the student in 2521 (this should succeed as they have met
        // the prereqs)
        Enrolment comp2521Enrolment = comp2521Offering.enrol(student, lecture2521);

        if (comp2521Enrolment == null)
            System.out.println("Can't enrol in COMP2521");
        else {
        	student.addEnrolment(comp2521Enrolment);
        	System.out.println("Enrolled in COMP2521");
        }
    }
}

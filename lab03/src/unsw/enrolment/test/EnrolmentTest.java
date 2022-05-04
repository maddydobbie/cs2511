package unsw.enrolment.test;

import java.time.LocalTime;
import java.time.DayOfWeek;

import unsw.enrolment.Course;
import unsw.enrolment.CourseOffering;
import unsw.enrolment.Enrolment;
import unsw.enrolment.Lecture;
import unsw.enrolment.Session;
import unsw.enrolment.Student;

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

        // TODO Create some sessions for the courses
        Session s1 = new Session("CLB3", DayOfWeek.TUESDAY, LocalTime.of(10, 00), LocalTime.of(12,00));
        comp1511Offering.addSession(s1);
        Session s2 = new Session("Physics Theatre", DayOfWeek.THURSDAY, LocalTime.of(14,00), LocalTime.of(16,00));
        comp1531Offering.addSession(s2);
        Session s3 = new Session("Rex Vowels", DayOfWeek.FRIDAY, LocalTime.of(10,00), LocalTime.of(11,00));
        comp2521Offering.addSession(s3);
        
        // TODO Create a student
        Student sisil = new Student("z5113599");
        
        // TODO Enrol the student in COMP1511 for T1 (this should succeed)
        Enrolment enrol1511 = new Enrolment(comp1511Offering, sisil);
        sisil.addEnrolment(enrol1511);
        
        // TODO Enrol the student in COMP1531 for T1 (this should fail as they
        // have not met the prereq)
        Enrolment enrol1531 = new Enrolment(comp1531Offering, sisil);
        sisil.addEnrolment(enrol1531);

        // TODO Give the student a passing grade for COMP1511
        sisil.getEnrolment("COMP1511").setMark(75);

        // TODO Enrol the student in 2521 (this should succeed as they have met
        // the prereqs)
        Enrolment enrol2521 = new Enrolment(comp2521Offering, sisil);
        sisil.addEnrolment(enrol2521);

    }
}

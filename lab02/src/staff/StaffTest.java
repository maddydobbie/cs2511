package staff;

/**
 * @author z5112961
 *
 */

public class StaffTest {
	public static void main(String[] args) {
		StaffMember newStaff = new StaffMember("Maddy", (float) 100);
		Lecturer newLecturer = new Lecturer("Sisil", (float) 50, "CSE", 'A');
	
		printStaffDetails(newStaff);
		printStaffDetails(newLecturer);
		
		StaffMember staff1, staff2;
		StaffMember lecturer1, lecturer2;
		
		staff1 = new StaffMember("Test Member", (float) 1);
		if (!staff1.equals(staff1))
			System.out.println("TEST 1 FAILED");
		
		staff2 = new StaffMember("Test Member", (float) 1);
		if (!staff1.equals(staff2))
            System.out.println("TEST 2 FAILED");
        if (!staff2.equals(staff1))
            System.out.println("TEST 3 FAILED");
        
        lecturer1 = new Lecturer("Test Member", (float) 1, "CSE", 'A');
        if (!lecturer1.equals(lecturer1))
            System.out.println("TEST 4 FAILED");

        lecturer2 = new Lecturer("Test Member", (float) 1, "CSE", 'A');
        if (!lecturer1.equals(lecturer2))
            System.out.println("TEST 5 FAILED");
        if (!lecturer2.equals(lecturer1))
            System.out.println("TEST 6 FAILED");

        if (lecturer1.equals(staff1))
            System.out.println("TEST 7 FAILED");
        if (staff1.equals(lecturer1))
            System.out.println("TEST 8 FAILED");

        if (staff1.equals(null))
            System.out.println("TEST 9 FAILED");

        if (lecturer1.equals(null))
            System.out.println("TEST 10 FAILED");
	}
	
	private static void printStaffDetails(StaffMember staff) {
		System.out.println(staff.toString());
	}
}

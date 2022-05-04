package staff;

import java.time.LocalDate;
import java.util.Date;

public class Lecturer extends StaffMember {
	
	private String school;
	private char status;
	
	public Lecturer(String name, float salary, String school, char status) {
		super(name, salary);
		this.school = school;
		this.status = status;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Lecturer [school=" + school + ", status=" + status + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lecturer other = (Lecturer) obj;
		if (school == null) {
			if (other.school != null)
				return false;
		} else if (!school.equals(other.school))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	
}
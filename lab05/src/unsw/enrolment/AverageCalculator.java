package unsw.enrolment;

import java.util.List;

public class AverageCalculator implements Calculator {

	@Override
	public int calcMark(List<Mark> marks) {
		int sum = 0;
		for (int i = 0; i < marks.size(); i++) {
			sum = sum + marks.get(i).getMark();
		}
		int avg = sum / marks.size();
		return avg;
	}

	@Override
	public int calcMax(List<Mark> marks) {
		int sum = 0;
		for (int i = 0; i < marks.size(); i++) {
			sum = sum + marks.get(i).getMax();
		}
		int avg = sum / marks.size();
		return avg;
	}

}

package unsw.enrolment;

import java.util.List;

public class SumCalculator implements Calculator{

	@Override
	public int calcMark(List<Mark> marks) {
		int sum = 0;
		for (int i = 0; i < marks.size(); i++) {
			sum = sum + marks.get(i).getMark();
		}
		return sum;
	}

	@Override
	public int calcMax(List<Mark> marks) {
		int sum = 0;
		for (int i = 0; i < marks.size(); i++) {
			sum = sum + marks.get(i).getMax();
		}
		return sum;
	}

}

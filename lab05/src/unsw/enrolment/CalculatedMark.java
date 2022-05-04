package unsw.enrolment;

import java.util.ArrayList;
import java.util.List;

public class CalculatedMark implements Mark {
	
	private String assessment;
	private List<Mark> submarks;
	private Calculator calculator;
	
	public CalculatedMark(String assessment, Calculator calculator) {
		this.assessment = assessment;
		this.submarks = new ArrayList<>();
		this.calculator = calculator;
	}

	@Override
	public int getMark() {
		return calculator.calcMark(submarks);	
	}

	@Override
	public int getMax() {
		return calculator.calcMax(submarks);
	}

	@Override
	public boolean updateMark(String assessment, int mark, int max) {
		for (Mark currMark : submarks) {
			if (currMark.updateMark(assessment, mark, max))
				return true;
		}
		return false;
	}

	public void addSubmark(ManualMark mark) {
		this.submarks.add(mark);
	}

	@Override
	public String getAssessment() {
		return this.assessment;
	}

	@Override
	public Mark removeMark(String label) {
		Mark r = null;
		for (Mark curr : this.submarks) {
			if (curr.getAssessment() == label) {
				r = curr;
				this.submarks.remove(curr);
			}
		}
		return r;
	}


}

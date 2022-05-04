package unsw.enrolment;

import java.util.ArrayList;
import java.util.List;

public class ManualMark implements Mark, Subject {
	
	private String assessment;
	private int mark;
	private int max;
	private List<Observer> observers;
	
	public ManualMark(String assessment, int mark, int max) {
		this.assessment = assessment;
		this.mark = mark;
		this.max = max;
		this.observers = new ArrayList<>();
	}

	@Override
	public int getMark() {
		return this.mark;
	}

	@Override
	public int getMax() {
		return this.max;
	}

	@Override
	public boolean updateMark(String assessment, int mark, int max) {
		if (this.assessment.equals(assessment)) {
			this.mark = mark;
			this.max = max;
			return true;
		} else return false;
	}

	@Override
	public void regObserver(Observer observer) {
		this.observers.add(observer);
	}

	@Override
	public void unregObserver(Observer observer) {
		this.observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for (Observer curr : this.observers)
			curr.update(assessment, mark, max);
	}

	@Override
	public String getAssessment() {
		return this.assessment;
	}

	@Override
	public Mark removeMark(String label) {
		return null;
	}
}

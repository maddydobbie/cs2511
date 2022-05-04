package unsw.enrolment;

import java.util.ArrayList;
import java.util.List;

public class Assessment implements Ass {

	private String name;
	private int mark;
	private int max;
	private List<Ass> subasses;
	private List<Observer> observers;
	
	public Assessment(String name) {
		this.name = name;
		this.mark = -1;
		this.max = -1;
		this.subasses = new ArrayList<>();
		this.observers = new ArrayList<>();
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
			curr.update(this.name, this.mark, this.max);
		
	}

	@Override
	public void setMark(boolean avgNotSum, int mark, int max) {
		mark = 0;
		max = 0;
		if (this.subasses.isEmpty()) return;
		else {
			for (Ass currAss : this.subasses) {
				if (currAss.getMark() == -1) return;
				else {
					mark += currAss.getMark();
					max += currAss.getMax();
				}
			}
		}
		if (avgNotSum) {
			this.mark = mark/this.subasses.size();
			this.max = max/this.subasses.size();
		} else {
			this.mark = mark;
			this.max = max;
		}
		notifyObservers();
	}

	@Override
	public int getMark() {
		return this.mark;
	}

	@Override
	public int getMax() {
		return this.max;
	}
	
	public void addSubass(Ass subass) {
		this.subasses.add(subass);
	}
}

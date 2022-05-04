package unsw.enrolment;

import java.util.ArrayList;
import java.util.List;

public class Subass implements Ass {
	
	private String name;
	private int mark;
	private int max;
	private List<Observer> observers;
	
	public Subass(String name) {
		this.name = name;
		this.mark = -1;
		this.max = -1;
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
		for (Observer curr : this.observers) curr.update(this.name, this.mark, this.max);
	}
	@Override
	public void setMark(boolean avgNotSum, int mark, int max) {
		this.mark = mark;
		this.max = max;
	}
	@Override
	public int getMark() {
		return this.mark;
	}
	@Override
	public int getMax() {
		return this.max;
	}
}

package unsw.enrolment;

public interface Subject {
	public void regObserver(Observer observer);
	public void unregObserver(Observer observer);
	public void notifyObservers();
}

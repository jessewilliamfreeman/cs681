package edu.umb.cs681.hw01;

import java.util.LinkedList;

public abstract class Observable {
	private LinkedList<Observer> observers;
	private boolean changed;
	
	Observable() {
		this.observers = new LinkedList<Observer>();
		this.changed = false;
	}
	
	void addObserver(Observer o) {
		if (!this.observers.contains(o) ) {
			this.observers.add(o);
		} 
	}
	
	protected void clearChanged() {
		this.changed = false;
	}
	
	int countObservers() {
		return this.observers.size();
	}
	
	void deleteObserver(Observer o) {
		this.observers.remove(o);
	}
	
	void deleteObservers() {
		this.observers = new LinkedList<Observer>();
	}
	
	boolean hasChanged() {
		return this.changed;
	}
	
	void notifyObservers() {
		this.notifyObservers(null);
	}
	
	void notifyObservers(Object arg) {
		if (this.hasChanged()) {
			for (Observer o: this.observers) {
				o.update(this, arg);
			}
			this.clearChanged();
		}
	}
	
	protected void setChanged() {
		this.changed = true;
	}
	
}

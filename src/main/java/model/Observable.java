package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    private List<Observer> observers;

    public Observable() {
        observers = new ArrayList<>();
    }

    public void add(Observer observer) {
        observers.add(observer);
    }

    public void remove(Observer observer) {
        observers.remove(observer);
    }

    public void notify(int arg) {
        for (Observer observer : observers)
            observer.handle(arg);
    }

    public static void notify(int arg, Observer... observers) {
        for (Observer observer : observers)
            observer.handle(arg);
    }
}

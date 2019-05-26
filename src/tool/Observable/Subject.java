package tool.Observable;

import java.util.ArrayList;
import java.util.List;

public class Subject<T> {
    private List<Observer<T>> observers = new ArrayList<>();
    private T state;

    public T getValue() {
        return state;
    }

    public void next(T state) {
        this.state = state;
        this.notifyObservers();
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(getValue());
        }
    }

    public void attach(Observer<T> observer) {
        observers.add(observer);
    }

}

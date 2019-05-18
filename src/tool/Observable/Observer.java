package tool.Observable;

public abstract class Observer<T> {
    protected Subject<T> subject;
    public abstract void update();
}

package renderer;

public class Point<T> {
    public T x;
    public T y;

    /**
     * A point with an x and a y value
     * @param x
     * @param y
     */
    public Point(T x, T y) {
        this.x = x;
        this.y = y;
    }
}

package renderer.vectors;

/**
 * The bounding box of an object, based on the start and end points
 */
public class BoundingBox {

    private Float x1;
    private Float x2;
    private Float y1;
    private Float y2;

    public BoundingBox(Float x1, Float y1, Float x2, Float y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public Float getWidth() {
        return Math.abs(x1 - x2);
    }

    public Float getHeight() {
        return Math.abs(y1 - y2);
    }

    public Float getTopX() {
        return Math.min(x1, x2);
    }

    public Float getTopY() {
        return Math.min(y1, y2);
    }

}

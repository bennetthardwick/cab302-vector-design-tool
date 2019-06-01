package tool.Events;

import renderer.vectors.VectorType;

import java.awt.*;
import java.io.File;

/**
 * A class representing transformations of the renderer.
 * Such as "undo", "set fill", etc.
 */
public class UpdateEvent {

    public UpdateEventType type;
    public String document;
    public Color color;
    public File file;

    public VectorType vector;

    public UpdateEvent(
            UpdateEventType type
    ) {
        this.type = type;
    }

    public UpdateEvent(
            UpdateEventType type,
            String document
    ) {
        this.type = type;
        this.document = document;
    }

    public UpdateEvent(
            UpdateEventType type,
            File file
    ) {
        this.type = type;
        this.file = file;
    }

    public UpdateEvent(
            UpdateEventType type,
            Color color
    ) {
        this.type = type;
        this.color = color;
    }

    public UpdateEvent(
            UpdateEventType type,
            VectorType vector
    ) {
        this.type = type;
        this.vector = vector;
    }

}

package renderer.__tests__;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import renderer.Parser;
import renderer.Renderer;
import renderer.errors.InvalidActionStringException;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RendererTest {

    private Renderer renderer;
    private ArrayList<String> actions;

    @BeforeEach
    void setup() {
        actions = new ArrayList<>();
        actions.add("RECTANGLE 0.25 0.25 0.75 0.75");
        actions.add("FILL #ff0000");
        actions.add("ELLIPSE 0.25 0.25 0.75 0.75");
        actions.add("PEN #ff00ff");
        actions.add("LINE 0.25 0.25 0.75 0.75");

        renderer = new Renderer();
        renderer.loadDocument(String.join("\n", actions));
    }

    @Test
    void loadDocument() {
        ArrayList<String> output = new ArrayList();
        output.add("RECTANGLE 0.1 0.1 0.9 0.9");
        output.add("LINE 0.1 0.1 0.9 0.9");
        assertNotEquals(renderer.serialize(), output);
        renderer.loadDocument(String.join("\n", output));
        assertEquals(renderer.serialize(), output);
    }

    @Test
    void serialize() {
        assertEquals(renderer.serialize(), actions);
    }

    @Test
    void undo() {
        var output = renderer.serialize();
        var oldSize = output.size();
        renderer.undo();
        assertEquals(renderer.serialize().size(), oldSize - 1);
    }

}
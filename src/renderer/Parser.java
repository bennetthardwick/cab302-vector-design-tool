package renderer;

import renderer.actions.*;
import renderer.errors.InvalidActionStringException;
import renderer.errors.InvalidActionTypeStringException;
import renderer.errors.InvalidVectorArgumentsException;
import renderer.vectors.VectorType;

import java.util.ArrayList;

/**
 * The class that converts the string representation of acions into actions.
 * @see Action
 */
public class Parser {
    /**
     * Create a list of actions from a "vec" document string
     * @param document the vec document
     * @return the list of actions
     * @throws InvalidActionStringException
     */
    public static ArrayList<Action> loadDocument(String document) throws InvalidActionStringException {
        var actions = new ArrayList<Action>();

        String lines[] = document.split("\\r?\\n");

        for (String line : lines) {
            actions.add(Parser.createActionWithArguments(line));
        }

        return actions;
    }

    /**
     * Create a single Action from a "vec" string
     * @param actionString the single action, e.g. "PLOT 0.5 0.5"
     * @return an Action
     * @throws InvalidActionStringException
     */
    public static Action createActionWithArguments(String actionString) throws InvalidActionStringException {
        String[] parts = actionString.split(" ", 2);

        if (parts.length == 2) {
            String type = parts[0];
            String valueString = parts[1];
            try {
                return Parser.createActionFromType(type, valueString);
            } catch (InvalidActionTypeStringException e) { }
        }

        throw new InvalidActionStringException(actionString);
    }

    /**
     * Create an action based on type and arguments
     * @param actionTypeString type, e.g. "PLOT"
     * @param arguments arguments, e.g. "0.5, 0.5"
     * @return an Action
     * @throws InvalidActionTypeStringException
     */
    public static Action createActionFromType(String actionTypeString, String arguments) throws InvalidActionTypeStringException {

        ActionType actionType;

        try {
            actionType = ActionType.valueOf(actionTypeString);
        } catch (IllegalArgumentException e) {
            actionType = ActionType.VECTOR;
        }

        if (actionType == ActionType.VECTOR) {
            try {
                VectorType type = VectorType.valueOf(actionTypeString);
                return new VectorAction(type, arguments);
            } catch (IllegalArgumentException e) {
                throw new InvalidActionTypeStringException(actionTypeString);
            } catch (InvalidVectorArgumentsException e) {
                throw new InvalidActionTypeStringException(actionTypeString);
            }
        }

        switch (actionType) {
            case FILL:
                return new Fill(arguments);
            case PEN:
                return new Pen(arguments);
            default:
                throw new InvalidActionTypeStringException(actionTypeString);
        }
    }

}

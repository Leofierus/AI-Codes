import java.util.HashSet;
import java.util.LinkedList;

class Test {

    HashSet<State> uniqueStates;

    void letsRoll() {
        int jug1 = 5;
        int jug2 = 4;
        int amtNeeded = 2;

        State initialState = new State(0, 0);
        State finalState = new State(amtNeeded, 0);
        State finalPath = null;

        uniqueStates = new HashSet<>();
        LinkedList<State> queue = new LinkedList<>();
        queue.add(initialState);

        while (!queue.isEmpty()) {
            State currState = queue.poll();
            if (currState.equals(finalState)) {
                finalPath = currState;
                break;
            }
            if (currState.x == 0) {
                State nextState = new State(jug1, currState.y, currState);
                checkUniqueStates(uniqueStates, nextState, queue);
            }
            if (currState.y == 0) {
                State nextState = new State(currState.x, jug2, currState);
                checkUniqueStates(uniqueStates, nextState, queue);
            }
            if (currState.x > 0) {
                State nextState = new State(0, currState.y, currState);
                checkUniqueStates(uniqueStates, nextState, queue);
            }
            if (currState.y > 0) {
                State nextState = new State(currState.x, 0, currState);
                checkUniqueStates(uniqueStates, nextState, queue);
            }
            if (currState.x > 0 && currState.y < jug2) {
                int amtToTransfer = Math.min(currState.x, jug2 - currState.y);
                State nextState = new State(currState.x - amtToTransfer, currState.y
                        + amtToTransfer,
                        currState);
                checkUniqueStates(uniqueStates, nextState, queue);
            }
            if (currState.y > 0 && currState.x < jug1) {
                int amtToTransfer = Math.min(currState.y, jug1 - currState.x);
                State nextState = new State(currState.x + amtToTransfer, currState.y
                        - amtToTransfer,
                        currState);
                checkUniqueStates(uniqueStates, nextState, queue);
            }
        }
        if (finalPath != null) {
            System.out.println("J1  J2");
            System.out.println(finalPath);
        }
        else{
            System.out.println("Not Possible");

        }
    }
    void checkUniqueStates(HashSet<State> uniqueStates, State toCheck,
                           LinkedList<State> queue) {
        if (!uniqueStates.contains(toCheck)) {
            uniqueStates.add(toCheck);
            queue.add(toCheck);
        }
    }
    public static void main(String[] args) {
        new Test().letsRoll();
    }
}
import org.example.InputHandling.InputHandler;
import org.junit.Test;

import static org.junit.Assert.*;

public class InputHandlerTest {

    @Test
    public void handleInput_FileOptionNotGivenAsInput_False(){
        String[] args = new String[] {"-sbd", "desc"};
        InputHandler inputHandler = new InputHandler();
        boolean out = inputHandler.handleInput(args);
        assertFalse(out);
    }

    @Test
    public void handleInput_FileOptionGivenAsInput_True(){
        String[] args = new String[] {"-i", "file.txt"};
        InputHandler inputHandler = new InputHandler();
        boolean out = inputHandler.handleInput(args);
        assertTrue(out);
    }

    @Test
    public void handleInput_NoInputGiven_False(){
        String[] args = new String[] {};
        InputHandler inputHandler = new InputHandler();
        boolean out = inputHandler.handleInput(args);
        assertFalse(out);
    }

    @Test
    public void handleInput_InvalidOptionGiven_False(){
        String[] args = new String[] {"-invalidCommand"};
        InputHandler inputHandler = new InputHandler();
        boolean out = inputHandler.handleInput(args);
        assertFalse(out);
    }

    @Test
    public void handleInput_MoreThanOneFilePathGiven_False(){
        String[] args = new String[] {"-i", "dafa.txt", "daf.txt"};
        InputHandler inputHandler = new InputHandler();
        boolean out = inputHandler.handleInput(args);
        assertFalse(out);
    }

    @Test
    public void handleInput_OptionOrderFileSortFilter_True(){
        String[] args = new String[] {"-i", "dafa.txt", "-sbd", "asc", "-f", "male"};
        InputHandler inputHandler = new InputHandler();
        boolean out = inputHandler.handleInput(args);
        assertTrue(out);
    }

    @Test
    public void handleInput_OptionOrderSortFilterFile_True(){
        String[] args = new String[] { "-sbd", "asc", "-f", "female", "-i", "dafa.txt"};
        InputHandler inputHandler = new InputHandler();
        boolean out = inputHandler.handleInput(args);
        assertTrue(out);
    }

    @Test
    public void handleInput_OptionOrderFilterFileSort_True(){
        String[] args = new String[] {"-f", "male", "-i", "dafa.txt", "-sbd", "asc"};
        InputHandler inputHandler = new InputHandler();
        boolean out = inputHandler.handleInput(args);
        assertTrue(out);
    }

    @Test
    public void handleInput_NoInputFilePathProvided_False(){
        String[] args = new String[] {"-i"};
        InputHandler inputHandler = new InputHandler();
        boolean out = inputHandler.handleInput(args);
        assertFalse(out);
    }
}

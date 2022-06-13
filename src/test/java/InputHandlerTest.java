import org.example.InputHandling.InputHandler;
import org.junit.Test;

import static org.junit.Assert.*;

public class InputHandlerTest {

    @Test
    public void handleInput_InputInvalidOption_ThrowsNotValidOptionError(){
        String[] args = new String[] {"--wrongInput"};
        InputHandler inputHandler = new InputHandler();
        boolean out = inputHandler.handleInput(args);
        assertFalse(out);
    }

    @Test
    public void handleInput_InputMultipleSameOption_TakesLastOptionValue(){
        String[] args = new String[] {"--wrongInput"};
        InputHandler inputHandler = new InputHandler();
        boolean out = inputHandler.handleInput(args);
        assertFalse(out);
    }

    @Test
    public void handleInput_InputOptionsMultipleValues_ThrowsError(){
        String[] args = new String[] {"-i", "path1", "path2", "path3"};
        InputHandler inputHandler = new InputHandler();
        boolean out = inputHandler.handleInput(args);
        assertFalse(out);
    }
}

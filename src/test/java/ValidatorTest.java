import org.example.IdValidation.Validator;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ValidatorTest {

    @Test
    public void validateId_IdShouldBeAddedToValidIdsList_IsAdded() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<String> ids = new ArrayList<>();
        ids.add("34409157850"); // its a valid id
        Validator validator = new Validator(ids);

        Method method = Validator.class.getDeclaredMethod("validateId", String.class);
        method.setAccessible(true);

        method.invoke(validator, "34409157850");

        assertEquals(validator.getValidIds().get(0).getId(), 34409157850L);
    }

    @Test
    public void validateId_IdShouldBeAddedToInvalidIdsList_IsAdded() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<String> ids = new ArrayList<>();
        ids.add("34409157"); // it's an invalid id
        Validator validator = new Validator(ids);

        Method method = Validator.class.getDeclaredMethod("validateId", String.class);
        method.setAccessible(true);

        method.invoke(validator, "34409157");

        assertEquals(validator.getInvalidIds().get(0).getId(), 34409157L);
    }

    @Test
    public void validateId_IdShouldNotBeAddedToAnyList_IsNotAdded() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<String> ids = new ArrayList<>();
        ids.add("34409150d7"); // wrong format id
        Validator validator = new Validator(ids);

        Method method = Validator.class.getDeclaredMethod("validateId", String.class);
        method.setAccessible(true);

        method.invoke(validator, "34409150d7");

        assertTrue(validator.getValidIds().isEmpty());
        assertTrue(validator.getInvalidIds().isEmpty());
    }
}

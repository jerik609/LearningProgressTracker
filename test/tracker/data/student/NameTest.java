package tracker.data.student;

import org.junit.jupiter.api.Test;
import tracker.data.student.Name;

import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {
    @Test
    public void hyphenApostropheValidationTest() {
        final String[] invalidCases = new String[]{
                "in--between", "in''between",
                "in-'between", "in'-between",
                "bordering'", "bordering-",
                "-bordering", "'bordering"
        };
        final var pattern = Pattern.compile(Name.INVALID_HYPHEN_APOSTROPHE_REGEX);
        for (var item : invalidCases) {
            System.out.println("processing: " + item);
            assertFalse(pattern.matcher(item).matches());
        }
    }

    @Test
    public void subItemValidationTest() {
        final String[] validCases = new String[]{
                "Hello", "Ma", "HHello", "aaM"
        };
        final var pattern = Pattern.compile(Name.VALID_SUB_ITEM_REGEX);
        for (var item : validCases) {
            System.out.println("processing: " + item);
            assertTrue(pattern.matcher(item).matches());
        }
    }

    @Test
    public void parseNameTest1() {
        final var splitName = List.of("Jean-Claude","Van","Damme");
        final var name = Name.buildFrom(splitName);
        assertNotNull(name);
        assertEquals(splitName.get(0), name.getFirstname());
        assertEquals(splitName.get(1) + " " + splitName.get(2), name.getSurname());
    }

    @Test
    public void parseNameTest2() {
        final var splitName = List.of("Bob", "O'Neill");
        final var name = Name.buildFrom(splitName);
        assertNotNull(name);
        assertEquals(splitName.get(0), name.getFirstname());
        assertEquals(splitName.get(1), name.getSurname());
    }

    @Test
    public void parseNameTest3() {
        final var splitName = List.of("John", "D.");
        final var name = Name.buildFrom(splitName);
        assertNull(name);
    }
}

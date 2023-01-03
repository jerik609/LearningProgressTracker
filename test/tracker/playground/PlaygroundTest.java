package tracker.playground;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// per method - a new instance of test class is created before each test method call (default behavior)
// per class - a new instance of test class is created ... well ... just once for the whole test class methods' execution
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class PlaygroundTest {

    static int counter;

    PlaygroundTest() {
        System.out.println("Test class ctor");
    }

    @BeforeAll // these "all" are static, because they are called before the actual test class construction
    static void moo() {
        counter = 0;
        System.out.println("before all");
    }

    @AfterAll
    static void boo() {
        System.out.println("after all");
    }

    @BeforeEach
    void setUp() {
        System.out.println("before each");
    }

    @AfterEach
    void tearDown() {

        System.out.println("after each");
    }

    @Test
    void isBig() {
        System.out.println("is big biggity big");
    }

    @Test
    void hasManyToys() {
        System.out.println("toyz toyz toyz");
    }

    @ParameterizedTest(name = "hello")
    @NullSource
    @ValueSource(ints = {1, 3, 5, -3, 15, Integer.MAX_VALUE}) // six numbers
    void test1(Integer number) {
        System.out.println(number);
    }

    @ParameterizedTest(name = "val and square = {0}, {1}")
    @MethodSource("mooProvider")
    void test2(int val, int squared) {
        System.out.println("VAL " + val + " AND ITS SQUARE " + squared);
    }

    public static List<Arguments> mooProvider() {
        return IntStream.rangeClosed(60, 74).boxed().map(value -> Arguments.arguments(value, value*value)).collect(Collectors.toList());
    }

    @RepeatedTest(value = 3)
    void isNextToMe() {
        counter++;
        System.out.println("very close! " + counter);
        var deque = new ArrayDeque<String>();

        deque.pop();
        deque.add("x");
        var theMap = Map.of("key1", "value1", "key2", "value2", "key3", "value3");

    }
}
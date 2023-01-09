package tracker.playground;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Sorting {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("hello", "hellno", "abcd", "zyx", "yello"));
        Comparator<String> compie = Comparator.naturalOrder();
        list.sort(compie.reversed());
        list.sort(Comparator.comparing((String s) -> s).reversed());
    }
}

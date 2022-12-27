package tracker.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {

    @Test
    @DisplayName("Accepts various names")
    void buildFrom() {
        //var name = Name.buildFrom("Jean--Moo", "Van de B''oo");
        var pattern = Pattern.compile("(?!.*moo).*");
        var matcher = pattern.matcher("maoo m45oo moso");

        System.out.println(matcher.matches());


    }
}
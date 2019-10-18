package uj.java.pwj2019.w02;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.of;
import static org.assertj.core.api.Assertions.assertThat;

class SpreadsheetTest {

    @ParameterizedTest
    @MethodSource("spreadsheetInput")
    void reverse(String[][] input, String[][] expected) {
        String[][] result = new Spreadsheet().calculate(input);
        assertThat(result).isEqualTo(expected);
    }

    static Stream<Arguments> spreadsheetInput() {
        return Stream.of(
            of(readSpreadsheet("sample.txt"), readSpreadsheet("sample-result.txt"))
        );
    }

    static String[][] readSpreadsheet(String name) {
        InputStream input = SpreadsheetTest.class.getResourceAsStream(name);
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        List<String> lines = br.lines().collect(Collectors.toList());
        String[][] result = new String[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            result[i] = lines.get(i).split(",");
        }
        return result;
    }

}

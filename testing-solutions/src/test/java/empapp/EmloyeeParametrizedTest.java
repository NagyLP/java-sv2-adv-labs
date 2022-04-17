package empapp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

public class EmloyeeParametrizedTest {


    @ParameterizedTest(name = "getAge()-Year of birth: {0}, age {1}")
//    @ValueSource(ints = {1970, 1980, 1990})
//    @MethodSource("createAges")
//    @ArgumentsSource(AgeProvider.class)
//    @CsvSource({
//            " 1970, 52",
//            " 1980, 42",
//            " 1990, 32"
//    })
    @CsvFileSource(resources = "/ages.csv", numLinesToSkip = 1)
    void testGetAge(int yearOfBirth, int age) {
        Employee employee = new Employee("John Doe", yearOfBirth);
        assertEquals(age, employee.getAge(2022));
    }


//        public static Stream<Arguments> createAges() {
//            return Stream.of(
//                    Arguments.arguments(1970, 52),
//                    Arguments.arguments(1980, 42),
//                    Arguments.arguments(1990, 32));
//        }
//    static class AgeProvider implements ArgumentsProvider {
//        @Override
//        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
//            return Stream.of(
//                    Arguments.arguments(1970, 52),
//                    Arguments.arguments(1980, 42),
//                    Arguments.arguments(1990, 32));
//        }
//   }

}

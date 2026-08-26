package school.sptech.ex5.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class BatalharProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // vida (inicial), ataques, curas, vida (esperada)
        return Stream.of(
                Arguments.of(30, new Integer[]{10, 10, 10, 5}, new Integer[]{0, 0, 0, 0}, 0),
                Arguments.of(50, new Integer[]{60}, new Integer[]{0}, 0),
                Arguments.of(100, new Integer[]{10, 20, 30}, new Integer[]{0, 0, 0}, 40),
                Arguments.of(20, new Integer[]{20, 0, 5}, new Integer[]{0, 0, 0}, 0),
                Arguments.of(10, new Integer[]{-5, 10}, new Integer[]{0, 0}, 0),
                Arguments.of(0, new Integer[]{10}, new Integer[]{0}, 0),
                Arguments.of(10, new Integer[]{3, 3}, new Integer[]{5, 0}, 9),
                Arguments.of(10, new Integer[]{10, 5}, new Integer[]{5, 0}, 0)
        );
    }
}

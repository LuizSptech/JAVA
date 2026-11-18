package school.sptech;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class OrdenacaoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
            Arguments.of(new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 3, 4, 5}),
            Arguments.of(new int[]{5, 4, 3, 2, 1}, new int[]{1, 2, 3, 4, 5}),
            Arguments.of(new int[]{4, 2, 4, 1, 3, 2}, new int[]{1, 2, 2, 3, 4, 4}),
            Arguments.of(new int[]{3, -1, 0, -5, 2}, new int[]{-5, -1, 0, 2, 3}),
            Arguments.of(new int[]{7, 7, 7, 7, 7}, new int[]{7, 7, 7, 7, 7}),
            Arguments.of(new int[]{42}, new int[]{42}),
            Arguments.of(new int[]{}, new int[]{}),
            Arguments.of(new int[]{1, 3, 2, 4, 5}, new int[]{1, 2, 3, 4, 5}),
            Arguments.of(new int[]{1000, 5000, 100, 50, 9000}, new int[]{50, 100, 1000, 5000, 9000}),
            Arguments.of(new int[]{9, 1, 6, 3, 8, 2}, new int[]{1, 2, 3, 6, 8, 9}),
            Arguments.of(new int[]{2, 1}, new int[]{1, 2}),
            Arguments.of(new int[]{10, 0, -10, 5, -5}, new int[]{-10, -5, 0, 5, 10}),
            Arguments.of(new int[]{8, 3, 7, 4, 9, 2, 6, 1, 5}, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}),
            Arguments.of(new int[]{9, 8, 9, 8, 7, 7}, new int[]{7, 7, 8, 8, 9, 9}),
            Arguments.of(new int[]{50, 20, 30, 10, 40}, new int[]{10, 20, 30, 40, 50}),
            Arguments.of(new int[]{0, 0, -1, 1, 0}, new int[]{-1, 0, 0, 0, 1}),
            Arguments.of(new int[]{2, 3, 1, 3, 2, 1}, new int[]{1, 1, 2, 2, 3, 3}),
            Arguments.of(new int[]{-3, -2, -1, 0, 1, 2, 3}, new int[]{-3, -2, -1, 0, 1, 2, 3}),
            Arguments.of(new int[]{3, 1, 4, 1, 5, 9, 2, 6, 5}, new int[]{1, 1, 2, 3, 4, 5, 5, 6, 9}),
            Arguments.of(new int[]{Integer.MAX_VALUE, 0, Integer.MIN_VALUE}, new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE})
        );
    }
}

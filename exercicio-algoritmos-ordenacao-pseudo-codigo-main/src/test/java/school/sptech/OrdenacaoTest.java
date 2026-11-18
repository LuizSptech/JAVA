package school.sptech;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.lang.reflect.Method;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

class OrdenacaoTest {

    @ParameterizedTest
    @DisplayName("Deve ordenar corretamente usando BubbleSort")
    @ArgumentsSource(OrdenacaoProvider.class)
    void testBubbleSort(int[] array, int[] expected) throws Exception {
        testarOrdenacao(BubbleSort.class, "bubbleSort", array, expected);
    }

    @ParameterizedTest
    @DisplayName("Deve ordenar corretamente usando SelectionSort")
    @ArgumentsSource(OrdenacaoProvider.class)
    void testSelectionSort(int[] array, int[] expected) throws Exception {
        testarOrdenacao(SelectionSort.class, "selectionSort", array, expected);
    }

    @ParameterizedTest
    @DisplayName("Deve ordenar corretamente usando SelectionSortOtimizado")
    @ArgumentsSource(OrdenacaoProvider.class)
    void testSelectionSortOtimizado(int[] array, int[] expected) throws Exception {
        testarOrdenacao(SelectionSortOtimizado.class, "selectionSortOtimizado", array, expected);
    }

    @ParameterizedTest
    @DisplayName("Deve ordenar corretamente usando InsertionSort")
    @ArgumentsSource(OrdenacaoProvider.class)
    void testInsertionSort(int[] array, int[] expected) throws Exception {
        testarOrdenacao(InsertionSort.class, "insertionSort", array, expected);
    }

    private void testarOrdenacao(Class<?> clazz, String methodName, int[] array, int[] expected)
          throws Exception {
        int[] copia = Arrays.copyOf(array, array.length);
        int[] original = Arrays.copyOf(array, array.length);

        Method method = clazz.getDeclaredMethod(methodName, int[].class);
        method.trySetAccessible();

        Object instance = clazz.getDeclaredConstructor().newInstance();
        method.invoke(instance, copia);
        assertArrayEquals(
              expected,
              copia,
              () -> String.format(
                    """
                          ❌ Erro em %s
                          ▸ Original: %s
                          ▸ Esperado: %s
                          ▸ Obtido:  %s
                          """,
                    clazz.getSimpleName(),
                    Arrays.toString(original),
                    Arrays.toString(expected),
                    Arrays.toString(copia)
              )
        );
    }
}

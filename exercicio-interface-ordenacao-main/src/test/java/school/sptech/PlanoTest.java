package school.sptech;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import school.sptech.util.EncapsulamentoUtil;

@DisplayName("Plano")
public class PlanoTest {

    @Nested
    @DisplayName("Plano 1. Classe abstrata")
    class AbstrataTest {

        @Test
        @DisplayName("1. Deve ser uma classe abstrata")
        void test1() {
            assertTrue(Modifier.isAbstract(Plano.class.getModifiers()), "Plano deve ser uma classe abstrata");
        }

        @Test
        @DisplayName("2. calcularMensalidade deve ser abstrato")
        void test2() throws ReflectiveOperationException {
            Method metodo = Plano.class.getDeclaredMethod("calcularMensalidade");

            assertTrue(Modifier.isAbstract(metodo.getModifiers()), "calcularMensalidade deve ser abstrato");
        }
    }

    @Nested
    @DisplayName("Plano 2. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            EncapsulamentoUtil.assertPossuiAtributos(Plano.class, "nome", "precoBase");
        }

        @Test
        @DisplayName("2. Atributos Privados")
        void test2() {
            EncapsulamentoUtil.assertAtributosPrivados(Plano.class);
        }

        @Test
        @DisplayName("3. Atributos devem possuir apenas getters")
        void test3() {
            EncapsulamentoUtil.assertApenasGetters(Plano.class);
        }

        @Test
        @DisplayName("4. Deve possuir o construtor (String nome, Double precoBase)")
        void test4() {
            assertDoesNotThrow(() -> Plano.class.getDeclaredConstructor(String.class, Double.class),
                  "Plano deve possuir o construtor (String nome, Double precoBase)");
        }
    }
}

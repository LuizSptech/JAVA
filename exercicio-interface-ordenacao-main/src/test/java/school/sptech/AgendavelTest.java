package school.sptech;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Modifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import school.sptech.util.EncapsulamentoUtil;

@DisplayName("Agendavel")
public class AgendavelTest {

    @Nested
    @DisplayName("Agendavel 1. Interface")
    class InterfaceTest {

        @Test
        @DisplayName("1. Deve ser uma interface")
        void test1() {
            assertTrue(Agendavel.class.isInterface(), "Agendavel deve ser uma interface");
        }
    }

    @Nested
    @DisplayName("Agendavel 2. Métodos")
    class MetodosTest {

        @Test
        @DisplayName("1. Validar Métodos")
        void test1() {
            assertAll(
                  () -> EncapsulamentoUtil.assertPossuiMetodo(Agendavel.class, "getNome"),
                  () -> EncapsulamentoUtil.assertPossuiMetodo(Agendavel.class, "reservarVaga"),
                  () -> EncapsulamentoUtil.assertPossuiMetodo(Agendavel.class, "getVagasDisponiveis"),
                  () -> EncapsulamentoUtil.assertPossuiMetodo(Agendavel.class, "getCapacidade")
            );
        }

        @Test
        @DisplayName("2. Os métodos devem ser abstratos e com o retorno correto")
        void test2() {
            Class<Agendavel> clazz = Agendavel.class;

            assertAll(
                  () -> assertTrue(Modifier.isAbstract(clazz.getDeclaredMethod("getNome").getModifiers()),
                        "getNome deve ser abstrato"),
                  () -> assertTrue(Modifier.isAbstract(clazz.getDeclaredMethod("reservarVaga").getModifiers()),
                        "reservarVaga deve ser abstrato"),
                  () -> assertTrue(Modifier.isAbstract(clazz.getDeclaredMethod("getVagasDisponiveis").getModifiers()),
                        "getVagasDisponiveis deve ser abstrato"),
                  () -> assertEquals(String.class, clazz.getDeclaredMethod("getNome").getReturnType(),
                        "getNome deve retornar String"),
                  () -> assertEquals(void.class, clazz.getDeclaredMethod("reservarVaga").getReturnType(),
                        "reservarVaga deve ser void"),
                  () -> assertEquals(Integer.class, clazz.getDeclaredMethod("getVagasDisponiveis").getReturnType(),
                        "getVagasDisponiveis deve retornar Integer"),
                  () -> assertTrue(Modifier.isAbstract(clazz.getDeclaredMethod("getCapacidade").getModifiers()),
                        "getCapacidade deve ser abstrato"),
                  () -> assertEquals(Integer.class, clazz.getDeclaredMethod("getCapacidade").getReturnType(),
                        "getCapacidade deve retornar Integer")
            );
        }
    }
}

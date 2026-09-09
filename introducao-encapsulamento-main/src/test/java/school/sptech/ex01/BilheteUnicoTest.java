package school.sptech.ex01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static school.sptech.util.ReflectionUtil.assertAllFieldsPrivate;
import static school.sptech.util.ReflectionUtil.assertGetter;
import static school.sptech.util.ReflectionUtil.assertPrivateField;
import static school.sptech.util.ReflectionUtil.assertSetter;

@DisplayName("BilheteUnico")
class BilheteUnicoTest {

    private final Class<BilheteUnico> clazz = BilheteUnico.class;

    @Nested
    @DisplayName("Atributos")
    class AtributosTest {

        @Test
        @DisplayName("Deve conter os atributos com os nomes e tipos corretos")
        void validarAtributos() {
            assertPrivateField(clazz, "titular", String.class);
            assertPrivateField(clazz, "ano", Integer.class);
            assertPrivateField(clazz, "estudante", Boolean.class);
            assertPrivateField(clazz, "saldo", Double.class);
            assertPrivateField(clazz, "bloqueado", Boolean.class);
        }
    }

    @Nested
    @DisplayName("Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("Todos os atributos devem ser privados")
        void atributosPrivados() {
            assertAllFieldsPrivate(clazz);
        }

        @Test
        @DisplayName("Todos os atributos devem possuir getter e setter públicos")
        void gettersESetters() {
            assertGetter(clazz, "titular", String.class);
            assertGetter(clazz, "ano", Integer.class);
            assertGetter(clazz, "estudante", Boolean.class);
            assertGetter(clazz, "saldo", Double.class);
            assertGetter(clazz, "bloqueado", Boolean.class);

            assertSetter(clazz, "titular", String.class);
            assertSetter(clazz, "ano", Integer.class);
            assertSetter(clazz, "estudante", Boolean.class);
            assertSetter(clazz, "saldo", Double.class);
            assertSetter(clazz, "bloqueado", Boolean.class);
        }
    }
}

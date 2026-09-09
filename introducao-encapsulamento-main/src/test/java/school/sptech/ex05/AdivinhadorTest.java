package school.sptech.ex05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static school.sptech.util.ReflectionUtil.assertAllFieldsPrivate;
import static school.sptech.util.ReflectionUtil.assertGetter;
import static school.sptech.util.ReflectionUtil.assertMethodReturnType;
import static school.sptech.util.ReflectionUtil.assertNoSetter;
import static school.sptech.util.ReflectionUtil.assertPrivateField;
import static school.sptech.util.ReflectionUtil.assertPublicMethod;

/**
 * Para o Adivinhador validamos apenas a estrutura e a assinatura do metodo: como
 * adivinharNumero trabalha com numeros aleatorios, nao ha cenarios de comportamento
 * nem invocacao (e, portanto, tambem nao ha cenario de parametro null).
 */
@DisplayName("Adivinhador")
class AdivinhadorTest {

    private final Class<Adivinhador> clazz = Adivinhador.class;

    @Nested
    @DisplayName("Atributos")
    class AtributosTest {

        @Test
        @DisplayName("Deve conter o atributo 'tentativas' do tipo Integer e privado")
        void validarAtributos() {
            assertPrivateField(clazz, "tentativas", Integer.class);
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
        @DisplayName("Deve possuir apenas o getter publico de 'tentativas' (sem setter)")
        void getterSemSetter() {
            assertGetter(clazz, "tentativas", Integer.class);
            assertNoSetter(clazz, "tentativas", Integer.class);
        }
    }

    @Nested
    @DisplayName("adivinharNumero(Integer)")
    class AdivinharNumeroTest {

        @Test
        @DisplayName("Deve ser publico, receber um Integer e ter retorno void")
        void validarAssinatura() {
            assertPublicMethod(clazz, "adivinharNumero", Integer.class);
            assertMethodReturnType(clazz, "adivinharNumero", void.class, Integer.class);
        }
    }
}

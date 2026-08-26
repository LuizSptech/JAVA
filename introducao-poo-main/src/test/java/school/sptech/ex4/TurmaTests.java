package school.sptech.ex4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.ex4.provider.CalcularMediaTurmaProvider;
import school.sptech.ex4.provider.ContarAprovadosProvider;
import school.sptech.ex4.provider.EncontrarMaiorNotaProvider;
import school.sptech.ex4.provider.EncontrarNotaMaisProximaDaMediaProvider;
import school.sptech.ex4.provider.MatricularAlunoProvider;
import school.sptech.ex4.provider.ValidarQuantidadeNotasProvider;
import school.sptech.util.ObjectFieldBuilder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static school.sptech.util.ReflectionUtil.assertField;
import static school.sptech.util.ReflectionUtil.assertMethod;
import static school.sptech.util.ReflectionUtil.getField;

@DisplayName("Atividade 4 - Turma")
class TurmaTests {

    @Nested
    @DisplayName("Estrutura")
    class EstruturaTest {

        @Test
        @DisplayName("Deve conter os atributos com os nomes e tipos corretos")
        void validarAtributos() {
            Class<Turma> clazz = Turma.class;

            assertField(clazz, "turma", String.class);
            assertField(clazz, "capacidadeMaxima", Integer.class);
            assertField(clazz, "quantidadeAlunosMatriculados", Integer.class);
        }

        @Test
        @DisplayName("Deve conter os métodos com as assinaturas corretas")
        void validarMetodos() {
            Class<Turma> clazz = Turma.class;

            assertMethod(clazz, "matricularAluno", Integer.class);
            assertMethod(clazz, "encontrarMaiorNota", Double[].class);
            assertMethod(clazz, "calcularMediaTurma", Double[].class);
            assertMethod(clazz, "contarAprovados", Double[].class);
            assertMethod(clazz, "validarQuantidadeNotas", Double[].class);
            assertMethod(clazz, "encontrarNotaMaisProximaDaMedia", Double[].class);
        }
    }

    @Nested
    @DisplayName("matricularAluno()")
    class MatricularAlunoTest {

        @ParameterizedTest
        @DisplayName("Deve matricular o aluno e atualizar quantidadeAlunosMatriculados corretamente")
        @ArgumentsSource(MatricularAlunoProvider.class)
        void testMatricularAluno(Integer quantidadeAlunosMatriculados, Integer capacidadeMaxima, Integer quantidade,
              Integer quantidadeAlunosMatriculadosEsperada) throws ReflectiveOperationException {

            Class<Turma> clazz = Turma.class;
            Method method = clazz.getDeclaredMethod("matricularAluno", Integer.class);

            method.setAccessible(true);

            Turma obj = new ObjectFieldBuilder<>(Turma.class)
                  .with("quantidadeAlunosMatriculados", quantidadeAlunosMatriculados)
                  .with("capacidadeMaxima", capacidadeMaxima)
                  .build();

            method.invoke(obj, quantidade);

            assertEquals(quantidadeAlunosMatriculadosEsperada, getField(obj, "quantidadeAlunosMatriculados"),
                  "matricularAluno(" + quantidade + ") não atualizou quantidadeAlunosMatriculados corretamente");
        }
    }

    @Nested
    @DisplayName("encontrarMaiorNota()")
    class EncontrarMaiorNotaTest {

        @ParameterizedTest
        @DisplayName("Deve encontrar a maior nota do vetor corretamente")
        @ArgumentsSource(EncontrarMaiorNotaProvider.class)
        void testEncontrarMaiorNota(Double[] notas, Double valorEsperado) throws ReflectiveOperationException {

            Class<Turma> clazz = Turma.class;
            Method method = clazz.getDeclaredMethod("encontrarMaiorNota", Double[].class);

            method.setAccessible(true);

            Turma obj = new Turma();

            Double maiorNota = (Double) method.invoke(obj, (Object) notas);

            assertEquals(valorEsperado, maiorNota, 0.01,
                  "encontrarMaiorNota() deveria retornar " + valorEsperado + ", mas retornou " + maiorNota);
        }
    }

    @Nested
    @DisplayName("calcularMediaTurma()")
    class CalcularMediaTurmaTest {

        @ParameterizedTest
        @DisplayName("Deve calcular a média das notas corretamente")
        @ArgumentsSource(CalcularMediaTurmaProvider.class)
        void testCalcularMediaTurma(Double[] notas, Double valorEsperado) throws ReflectiveOperationException {

            Class<Turma> clazz = Turma.class;
            Method method = clazz.getDeclaredMethod("calcularMediaTurma", Double[].class);

            method.setAccessible(true);

            Turma obj = new Turma();

            Double media = (Double) method.invoke(obj, (Object) notas);

            assertEquals(valorEsperado, media, 0.01,
                  "calcularMediaTurma() deveria retornar " + valorEsperado + ", mas retornou " + media);
        }
    }

    @Nested
    @DisplayName("contarAprovados()")
    class ContarAprovadosTest {

        @ParameterizedTest
        @DisplayName("Deve contar os alunos aprovados corretamente")
        @ArgumentsSource(ContarAprovadosProvider.class)
        void testContarAprovados(Double[] notas, Integer valorEsperado) throws ReflectiveOperationException {

            Class<Turma> clazz = Turma.class;
            Method method = clazz.getDeclaredMethod("contarAprovados", Double[].class);

            method.setAccessible(true);

            Turma obj = new Turma();

            Integer aprovados = (Integer) method.invoke(obj, (Object) notas);

            assertEquals(valorEsperado, aprovados,
                  "contarAprovados() deveria retornar " + valorEsperado + ", mas retornou " + aprovados);
        }
    }

    @Nested
    @DisplayName("validarQuantidadeNotas()")
    class ValidarQuantidadeNotasTest {

        @ParameterizedTest
        @DisplayName("Deve validar se a quantidade de notas bate com os alunos matriculados")
        @ArgumentsSource(ValidarQuantidadeNotasProvider.class)
        void testValidarQuantidadeNotas(Integer quantidadeAlunosMatriculados, Double[] notas, Boolean valorEsperado)
              throws ReflectiveOperationException {

            Class<Turma> clazz = Turma.class;
            Method method = clazz.getDeclaredMethod("validarQuantidadeNotas", Double[].class);

            method.setAccessible(true);

            Turma obj = new ObjectFieldBuilder<>(Turma.class)
                  .with("quantidadeAlunosMatriculados", quantidadeAlunosMatriculados)
                  .build();

            Boolean valido = (Boolean) method.invoke(obj, (Object) notas);

            assertEquals(valorEsperado, valido,
                  "validarQuantidadeNotas() deveria retornar " + valorEsperado + ", mas retornou " + valido);
        }
    }

    @Nested
    @DisplayName("encontrarNotaMaisProximaDaMedia()")
    class EncontrarNotaMaisProximaDaMediaTest {

        @ParameterizedTest
        @DisplayName("Deve encontrar a nota mais próxima da média corretamente")
        @ArgumentsSource(EncontrarNotaMaisProximaDaMediaProvider.class)
        void testEncontrarNotaMaisProximaDaMedia(Double[] notas, Double valorEsperado)
              throws ReflectiveOperationException {

            Class<Turma> clazz = Turma.class;
            Method method = clazz.getDeclaredMethod("encontrarNotaMaisProximaDaMedia", Double[].class);

            method.setAccessible(true);

            Turma obj = new Turma();

            Double notaMaisProxima = (Double) method.invoke(obj, (Object) notas);

            assertEquals(valorEsperado, notaMaisProxima, 0.01,
                  "encontrarNotaMaisProximaDaMedia() deveria retornar " + valorEsperado + ", mas retornou "
                        + notaMaisProxima);
        }
    }
}

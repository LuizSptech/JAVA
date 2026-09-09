# Avaliação Continuada 01 - RetroArcade 🎮

## 📌 Orientações Gerais:
1. Utilize **apenas** tipos **wrapper** para criar atributos e variáveis.
2. Verifique se **não** há **erros de compilação** no projeto antes de enviar.
3. Respeite os nomes de atributos e métodos definidos no exercício.
4. Tome cuidado com os argumentos especificados no exercício. Não adicione argumentos não solicitados e mantenha a ordem definida no enunciado.

> Dica: copie e cole os nomes do enunciado para evitar erros de digitação

## 🚨 Orientações para a avaliação

1. **Não é permitido** utilizar o material de aula para consulta.
2. **Não é permitido** consultar a internet.
3. **Não é permitido** utilizar qualquer tipo de inteligência artificial.
4. **Não é permitido** o uso de celular.
5. **Não é permitido** a comunicação com colegas.
6. **Não é permitido** pedir ajuda ao professor.

## 🕹️ RetroArcade

Você é o desenvolvedor de um sistema feito em Java para a RetroArcade, uma casa de fliperama, e foi
solicitado a você a implementação do controle de jogadores: cada jogador possui um saldo em
dinheiro, compra fichas com esse saldo, consome fichas para jogar partidas e acumula um histórico
de vitórias e derrotas.

Para isso foi entregue uma documentação detalhada a respeito do funcionamento da RetroArcade:

### 🛠️ Implementação

A classe `Jogador` representa um jogador da RetroArcade e precisa conter os seguintes atributos e
métodos:

**Atributos:**
* nome (Texto) (ex: "Ana")
* saldo (Número real) — saldo em dinheiro disponível para comprar fichas (ex: 50.0)
* quantidadeFichas (Número inteiro) — quantidade de fichas que o jogador possui no momento
* quantidadePartidas (Número inteiro) — total de partidas já disputadas pelo jogador
* vitorias (Número inteiro) — total de partidas vencidas

**Métodos:**

* `Boolean validarJogador()`
  * Verifica se o jogador é válido.
  * Retorna `false` se `nome` for nulo ou se `saldo` for nulo ou menor que zero.
  * Retorna `true` caso passadas as validações acima.


* `void registrarPartida(Boolean vitoria)`
  * Registra uma partida disputada pelo jogador.
  * Atualiza a quantidade de partidas do jogador, somando 1.
  * Caso `vitoria` seja `true`, atualiza o total de vitórias do jogador, somando 1.


* `Boolean consumirFicha(Integer quantidadeFichasParaConsumo)`
  * Recebe a quantidade de fichas que o jogador deseja consumir (por exemplo, para jogar uma
    partida).
  * Caso a quantidade de fichas para consumo seja negativa, retorna `false`;
  * Case a quantidade de fichas para consumo seja maior que a quantidade de fichas que o jogador
    possui, não altera nada e retorna `false`.
  * Caso contrário, subtrai a quantidade recebida da quantidade de fichas do jogador e retorna `true`.


* `Boolean comprarFichas(Integer quantidadeFichasParaCompra)`
  * Recebe a quantidade de fichas que o jogador deseja comprar e calcula o valor total da compra
    de acordo com a tabela de preços abaixo:

    | Quantidade de fichas    | Preço por ficha |
    |-------------------------|-----------------|
    | Até 5 (inclusive)       | R\$ 5,00        |
    | De 6 até 10 (inclusive) | R\$ 4,00        |
    | Acima de 10             | R\$ 3,00        |

  * Caso a quantidade de fichas recebida seja negativa, retorna `false`.
  * Caso o `saldo` do jogador seja insuficiente para cobrir o valor total da compra, não altera nada e retorna `false`.
  * Caso contrário, subtrai o valor total da compra do `saldo` do jogador, soma a quantidade de fichas compradas à quantidade de fichas do jogador e retorna `true`.
  * Exemplo: comprar 6 fichas custa 6 × R\$ 4,00 = R\$ 24,00 (pois 6 está na faixa "de 6 até 10").


* `Integer encontrarMaiorPontuacao(Integer[] pontuacoes)`
  * Recebe um array de pontuações, que representa os pontos obtidos pelo jogador em partidas disputadas.
  * Caso o array esteja vazio, retorne `null`.
  * Retorna o maior valor presente no array.
  * Exemplo:
    * pontuacoes = [-5, 20, -10]
    * **Resultado:** 20


* `Integer contarPontuacoesAcimaDaMedia(Integer[] pontuacoes)`
  * Recebe um array de pontuações, que representa os pontos obtidos pelo jogador em partidas disputadas.
  * Calcula a média das pontuações do array.
  * Retorna a quantidade de pontuações estritamente maiores que a média.
  * Caso o array esteja vazio, retorna `0`.
  * Exemplo:
    * pontuacoes = [1, 2, 3, 4, 5]
    * média = (1 + 2 + 3 + 4 + 5) / 5 = 3
    * **Resultado:** 2 (as pontuações 4 e 5 estão acima da média).
  * Exemplo 2:
    * pontuacoes = [10, 20, 30]
    * média = (10 + 20 + 30) / 3 = 20
    * **Resultado:** 1 (Apenas pontuação 30 está acima da média).

* `Integer encontrarMaiorSequenciaDeVitorias(Boolean[] resultados)`
  * Recebe um array com o resultado de uma sequência de partidas (`true` para vitória, `false`
    para derrota).
  * Retorna o tamanho da maior sequência de vitórias consecutivas (valores `true` seguidos)
    presente no array.
  * Caso o array esteja vazio, retorna `0`.
  * Exemplo:
    * resultados = [true, true, false, true, true, true]
    * **Resultado:** 3 (a segunda sequência de vitórias, com 3 vitórias seguidas, é maior que a
      primeira, com 2)

## 📓 Orientações Finais:

### 🎯 Observações:

1. O projeto contém testes automatizados para verificar a implementação dos atributos e métodos.
2. A classe `Jogador` já está criada e os atributos/métodos devem ser implementados dentro dela.
3. O projeto possui uma classe `Main` caso queira testar manualmente o funcionamento dos atributos e métodos.

### 📦 Entrega:

1. Verifique se há **nenhum erro de compilação** no projeto. Erros de compilação podem resultar em perda de pontos.
2. **Zipe o projeto inteiro** e envie o arquivo compactado no Moodle.
3. A não entrega do projeto resultará em **nota zero**.

### Boa sorte! 🍀

> "O sucesso é a soma de pequenos esforços repetidos dia após dia." - Robert Collier
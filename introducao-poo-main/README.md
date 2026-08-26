# Introdução a POO 📎

## Orientações Gerais: 🚨
1. Utilize **apenas** tipos **wrapper** para criar atributos e métodos.
2. **Respeite** os nomes de atributos e métodos definidos no exercício.
3. Tome **cuidado** com os **argumentos** especificados no exercício. 
**Não** adicione argumentos não solicitados e mantenha a ordem definida no enunciado.
4. Verifique se **não** há **erros de compilação** no projeto antes de enviar.

## Dicas: 💡
* Tome cuidado ao dividir valores no Java. Dividir valores inteiros sempre resulta em uma divisão inteira.
  ```java
  // Divisão inteira de dois inteiros
  Double valor = salario * 10 / 100;
  // > Resultado: 10 / 100 = 0
  
  // Divisão de um inteiro e um double
  Double valor2 = salario * 10 / 100.0;
  // > Resultado: 10 / 100.0 = 0.1
  ```

## Exercício 1 - Confeitaria 🎂

A confeitaria da Dona Maria, precisa de um sistema para gestão dos bolos vendidos em sua loja, que
possibilite a geração de um relatório de vendas para cada tipo de bolo, para implementação do sistema em
Java com OO (orientação a objetos), crie:

A classe `Bolo` precisa conter os seguintes atributos e métodos:

**Atributos:**
* sabor (Texto) (ex: chocolate, morango ou abacaxi)
* valor (Número real) (ex: 30.00 e 50.00)
* quantidadeVendida (Número inteiro) (ex: 10)
* quantidadeEmEstoque (Número inteiro) (ex: 100)

**Métodos:**
* venderBolo: 
  * **recebe** um número inteiro que é a quantidade desejada do bolo.
  * **atualiza** o atributo quantidadeVendida com a nova quantidade.
  * **atualiza** o atributo quantidadeEmEstoque com a nova quantidade após a venda do bolo.
  * caso a quantidade solicitada seja um valor negativo **não faça nada**.
  * caso a quantidade solicitada ultrapasse o limite disponível **não faça nada**.
  

* aumentarEstoque:
  * **recebe** um número inteiro que é a quantidade a ser adicionada ao estoque.
  * **atualiza** o atributo quantidadeEmEstoque com a nova quantidade.
  * caso a quantidade informada seja um valor negativo **não faça nada**.


* quantidadeDisponivel:
  * **retorna** quantas unidades desse bolo estão disponíveis para venda.


* totalVendido:
  * **retorna** a quantidade em dinheiro de bolos vendidos.

Utilize o método `main` na classe `TesteConfeitaria` para realizar os testes necessários.

## Exercício 2 - Encomendas 📦

Crie um programa para gerenciar as encomendas de uma transportadora, que gere etiquetas para
envio, e calcule o frete.

A classe `Encomenda` precisa conter os seguintes atributos e métodos:

**Atributos:**
* tamanho (Texto) (ex: "P", "M", "G")
* enderecoRemetente (Texto) (Ex: Rua Santos da Glória, 18)
* enderecoDestinatario (Texto) (Ex: Av Dr. Pedro, 255)
* distancia (Número real) (ex: 42.2)
* valorProduto (Número real) (ex: 87.50)

**Métodos:**

* calcularFrete:
  * **retorna** o valor do frete do produto levando em conta o **tamanho** e **distância** definida.
  * considere que `tamanho` sempre será `"P"`, `"M"` ou `"G"`.
  * use a seguinte tabela para o calculo:

    | Tamanho | Valor adicionado 💸                |
    |---------|------------------------------------|
    | **P**   | 1% do valor da encomenda           |
    | **M**   | 3% do valor da encomenda           |
    | **G**   | 5% do valor da encomenda           |

    | Distância              | Valor adicionado 💸 |
    |------------------------|---------------------|
    | **Até 50km**           | R$ 3,00             |
    | **Entre 50km e 200km** | R$ 5,00             |
    | **Acima de 200km**     | R$ 7,00             |




* aplicarCupomDeDesconto:
  * **recebe** o percentual de desconto a ser aplicado no valor do produto ex: 20, 15... 
  **(número inteiro)**
  * **altera** o valor do produto para o novo valor com desconto.
  * não é necessário validar o percentual recebido (considere que sempre será um valor entre 0 e 100).


* valorTotalDaEncomenda:
  * **retorna** o valor total da encomenda (valor do produto + frete)

Utilize o método `main` na classe `TesteEncomendas` para realizar os testes necessários.

## Exercício 3 - Empresa 💼

Crie um programa para gerenciar os funcionários de uma empresa.

A classe `Funcionario` precisa conter os seguintes atributos e métodos:

**Atributos:**
* nome (Texto) (ex: William)
* cargo (Texto) (Ex: Analista Desenvolvedor)
* salario (Número real) (Ex: 8000.0)

**Métodos:**

* reajustarSalario:
  * **recebe** o valor percentual do reajuste (ex: 10, 20) (número inteiro)
  * **atualiza** o atributo `salario` com o novo valor reajustado.
  * **Exemplo:** salário = 1000.0, percentual = 10 → novo salário = 1100.0


* calcularValorHora:
  * **retorna** o valor da hora trabalhada por mês dado o `salario` do `Funcionario`.
  * considere que cada funcionario trabalha 220 horas por mês.
  * **Exemplo:** salário = 2200.0 → valor da hora = 10.0


* calcularHoraExtra:
  * **recebe** a quantidade de horas trabalhadas fora do horário convencional de trabalho (valor inteiro)
  * **recebe** o percentual de adicional noturno a ser aplicado (valor inteiro)
  * **retorna** o valor a ser recebido pelas horas extras, considerando que:
    * o valor da hora normal deve ser calculado a partir do salário
    * cada hora extra deve ser paga com o valor da hora normal acrescido do adicional noturno informado
    * o valor final deve corresponder ao valor de uma hora extra multiplicado pela quantidade de horas extras trabalhadas.

  **Exemplo:** salário = 2200.0, horas extras trabalhadas = 10, adicional noturno = 20%
  - valor da hora = 10.0
  - valor da hora extra = 10.0 + 20% = 12.0
  - valor a receber = 120.0


* calcularBonificacaoAnual:
  * **retorna** o valor da bonificação anual do funcionário, calculada como um percentual do
    `salario`, de acordo com a faixa salarial:

    | Faixa salarial                                   | Percentual de bonificação 💸 |
    |--------------------------------------------------|------------------------------|
    | Até R$ 2.500,00 (inclusive)                      | 15% do salário               |
    | Acima de R$ 2.500,00 até R$ 6.000,00 (inclusive) | 10% do salário               |
    | Acima de R$ 6.000,00                             | 5% do salário                |

Utilize o método `main` na classe `TesteEmpresa` para realizar os testes necessários.

## Exercício 4 - Turma 🎓

Crie um programa para gerenciar uma turma de alunos, calculando estatísticas de notas e frequência
a partir de um conjunto de valores.

A classe `Turma` precisa conter os seguintes atributos e métodos:

**Atributos:**
* turma (Texto) (ex: "Turma A")
* capacidadeMaxima (Número inteiro) (ex: 30)
* quantidadeAlunosMatriculados (Número inteiro) (ex: 20)

**Métodos:**

* matricularAluno:
  * **recebe** um número inteiro com a quantidade de alunos a matricular.
  * **atualiza** o atributo `quantidadeAlunosMatriculados` somando a quantidade recebida.
  * caso a quantidade recebida seja um valor negativo **não faça nada**.
  * caso a matrícula ultrapasse a `capacidadeMaxima` da turma **não faça nada**.


* encontrarMaiorNota:
  * **recebe** um vetor (`Double[]`) com as notas dos alunos (sempre com pelo menos um elemento).
  * **retorna** a maior nota presente no vetor.


* calcularMediaTurma:
  * **recebe** um vetor (`Double[]`) com as notas dos alunos.
  * **retorna** a média das notas do vetor.


* contarAprovados:
  * **recebe** um vetor (`Double[]`) com as notas dos alunos.
  * **retorna** a quantidade de alunos aprovados, considerando aprovado quem tirou nota **maior ou
    igual a 6.0**.


* validarQuantidadeNotas:
  * **recebe** um vetor (`Double[]`) com as notas dos alunos.
  * **retorna** `true` se a quantidade de notas recebidas no vetor for **igual** ao atributo
    `quantidadeAlunosMatriculados` da turma (ou seja, se todos os alunos matriculados têm nota
    registrada).
  * **retorna** `false` caso contrário.


* encontrarNotaMaisProximaDaMedia: 🔥 *(Desafio)*
  * **recebe** um vetor (`Double[]`) com as notas dos alunos (sempre com pelo menos um elemento).
  * **calcula** a média das notas do vetor.
  * **retorna** a nota do vetor que está mais próxima da média (a de **menor diferença absoluta**
    em relação à média).
  * em caso de empate entre duas notas igualmente próximas, **retorna** a que aparece primeiro no
    vetor.

   Exemplos:
    - notas = [5.0, 7.0, 9.0] → média = 7.0 → retorna 7.0
    - notas = [2.0, 4.0, 6.0, 8.0] → média = 5.0 → 4.0 e 6.0 empatam, retorna 4.0 (primeiro do vetor)
    - notas = [1.0, 2.0, 10.0] → média ≈ 4.33 → retorna 2.0 (menor diferença em relação à média)

Utilize o método `main` na classe `TesteTurma` para realizar os testes necessários.

## Exercício 5 - Pokémon ⚡

Crie um programa para simular o combate e a evolução de Pokémon, controlando vida, experiência e
batalhas entre eles.

A classe `Pokemon` precisa conter os seguintes atributos e métodos:

**Atributos:**
* nome (Texto) (ex: "Pikachu")
* tipo (Texto) (ex: "Fogo", "Água", "Planta"...)
* vida (Número inteiro) (ex: 100)
* ataque (Número inteiro) (ex: 50)
* experiencia (Número inteiro) (ex: 0)

**Métodos:**

* receberAtaque:
  * **recebe** a quantidade de dano recebido (Número inteiro).
  * **atualiza** o atributo `vida`, subtraindo o dano recebido.
  * a `vida` **nunca** pode ficar negativa — se o dano ultrapassar a vida atual, `vida` deve
    ficar em **0**.
  * caso o dano recebido seja **negativo** **não faça nada**.


* recuperarVida:
  * **recebe** a quantidade de vida a ser recuperada (Número inteiro).
  * **atualiza** o atributo `vida`, somando a recuperação recebida.
  * a `vida` **nunca** pode ultrapassar **100** — se a recuperação ultrapassar esse limite, `vida`
    deve ficar em **100**.
  * caso a recuperação recebida seja **negativa** **não faça nada**.


* ganharExperiencia:
  * **recebe** a quantidade de experiência recebida (Número inteiro).
  * **atualiza** o atributo `experiencia`, somando a experiência recebida.
  * caso a experiência recebida seja **negativa** **não faça nada**.


* calcularNivel:
  * **retorna** o nível do Pokémon. A cada 100 pontos de experiência, o Pokémon sobe 1 nível. O nível inicial é 1. Exemplo: 250 de experiência = nível 2.


* calcularPoderDeCombate:

  * **retorna** o poder de combate do Pokémon. O poder é calculado somando o `ataque` do Pokémon, mais 10 vezes o seu nível, mais a sua `vida`. Exemplo: ataque = 50, nível = 2, vida = 80 → poder de combate = 150.


* **batalhar**: 🔥 *(Desafio)*

  * **recebe** o roteiro de uma batalha em dois vetores de mesmo tamanho: `ataques` (`Integer[]`), com o golpe sofrido a cada rodada, e `curas` (`Integer[]`), com a poção usada a cada rodada.
  * Cada **posição** dos vetores é uma **rodada** do combate: `ataques[0]` e `curas[0]` são a 1ª rodada, `ataques[1]` e `curas[1]` são a 2ª rodada, e assim por diante.
  * **não retorna nada**.
  * **processa** as rodadas em ordem, uma de cada vez:

    1. o Pokémon leva o golpe da rodada, seguindo as mesmas regras de `receberAtaque` (o dano não pode ser negativo e a vida nunca fica abaixo de `0`);
    2. se o golpe zerou a vida, é **nocaute** — a batalha **acaba ali na hora**: a poção dessa rodada não é usada e nenhuma rodada seguinte acontece;
    3. se o Pokémon ainda está de pé, ele toma a poção da rodada, seguindo as mesmas regras de `recuperarVida` (a cura não pode ser negativa e a vida nunca passa de `100`);
    4. Pokémon curado, batalha continua: próxima rodada.

  **Exemplo:**

  ```text
  Vida inicial: 50

  ataques = [10, 20, 40, 50]
  curas    = [30, 20, 10, 30]
  ```

  | Rodada | Ataque | Vida após ataque | Cura | Vida final | Situação   |
  |:------:|-------:|-----------------:|-----:|-----------:|------------|
  |   1    |     10 |          50 → 40 |   30 |     **70** | continua   |
  |   2    |     20 |          70 → 50 |   20 |     **70** | continua   |
  |   3    |     40 |          70 → 30 |   10 |     **40** | continua   |
  |   4    |     50 |       40 → **0** |    — |      **0** | 🛑 nocaute |

  **Vida final: `0`**

  > Na 4ª rodada o golpe é fatal: o Pokémon desmaia ali mesmo. Por isso a poção `30` **não é usada** — e não existe 5ª rodada para acontecer.
Utilize o método `main` na classe `TestePokemon` para realizar os testes necessários.

## Exercício 6 - Lista de Compras 🛒

A classe `ListaDeCompras` precisa conter os seguintes atributos e métodos:

**Atributos:**
* nomeLista (Texto) (ex: "Compras do mês")
* capacidadeMaxima (Número inteiro) — quantidade máxima de itens que a lista comporta (ex: 10)
* itens (`List<String>`) — lista com o nome de cada item adicionado (inicialize como uma lista
  vazia)

**Métodos:**

* adicionarItem:
  * **recebe** o nome do item a ser adicionado (Texto).
  * **adiciona** o item na lista `itens`.
  * caso a lista já tenha atingido a `capacidadeMaxima` **não faça nada**.
  * caso o item recebido **já exista** na lista (mesmo nome) **não faça nada** — não são permitidos
    itens duplicados.


* removerItem:
  * **recebe** o nome do item a ser removido (Texto).
  * **remove** a primeira ocorrência desse item da lista `itens`.
  * **retorna** `true` se o item foi encontrado e removido, ou `false` caso contrário.


* obterItem:
  * **recebe** uma posição da lista (Número inteiro, começando em 0).
  * **retorna** o item que está naquela posição.
  * caso a posição seja **inválida** (negativa ou maior/igual à quantidade de itens da lista),
    **retorna** `null`.


* substituirItem:
  * **recebe** uma posição da lista (Número inteiro, começando em 0) e o novo nome do item
    (Texto).
  * **substitui** o item que está naquela posição pelo novo item recebido e **retorna** `true`.
  * caso a posição seja **inválida** (negativa ou maior/igual à quantidade de itens da lista),
    **não faça nada** e **retorna** `false`.
  * caso o novo item recebido **já exista** na lista **não faça nada** e **retorna** `false` — não
    são permitidos itens duplicados.


* calcularVagasRestantes:
  * **retorna** quantas vagas ainda restam na lista, ou seja, `capacidadeMaxima` menos a
    quantidade de itens já presentes.


* removerItemNaPosicao:
  * **recebe** uma posição da lista (Número inteiro, começando em 0).
  * **remove** o item que está naquela posição (não pelo valor, mas pela posição) e **retorna** o
    item removido.
  * caso a posição seja **inválida** (negativa ou maior/igual à quantidade de itens da lista),
    **não faça nada** e **retorna** `null`.


* removerItensDuplicados: 🔥 *(Desafio)*
  * **percorre** a lista `itens` e **remove** as ocorrências repetidas de cada item, mantendo
    apenas a **primeira** ocorrência de cada um (a lista final não deve ter itens repetidos).
  * **retorna** a quantidade de itens removidos.
  * Exemplo: `["Arroz", "Feijão", "Arroz", "Leite", "Feijão", "Feijão"]` vira
    `["Arroz", "Feijão", "Leite"]` e o método retorna `3`.

Utilize o método `main` na classe `TesteListaDeCompras` para realizar os testes necessários.



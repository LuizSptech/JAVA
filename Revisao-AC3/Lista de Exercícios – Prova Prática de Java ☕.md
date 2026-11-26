# Lista de Exercícios – Prova Prática de Java ☕

Lista de treino no mesmo modelo dos exercícios da SPTech (herança, exceptions, classe abstrata, enum, interface/Observer e algoritmos de ordenação). Os domínios são novos, mas a **estrutura e as pegadinhas** são as mesmas das provas: validações em ordem, exceptions checked x unchecked, listas novas, comparação ignorando maiúsculas/minúsculas, retorno `null` quando não há resultado.

## Orientações Gerais 🚨

1. Utilize **apenas** tipos **wrapper** para criar atributos e métodos (`Integer`, `Double`, `Boolean`).
2. **Respeite** os nomes de classes, atributos e métodos definidos no enunciado.
3. Tome **cuidado** com os **argumentos** e a **ordem** exigidos. Não adicione argumentos não solicitados.
4. Verifique se **não há erros de compilação** antes de enviar.
5. As classes devem seguir as regras de **encapsulamento** (atributos `private`).
6. Situações inválidas devem ser comunicadas através de **exceptions**.
7. Quando o enunciado disser "nesta ordem", a ordem das validações **importa** (os testes conferem qual exception sai primeiro).
8. Sempre que o enunciado pedir uma **nova lista**, não devolva a lista interna do objeto.

> Como treinar: crie um projeto Maven com o pacote `school.sptech` (e `school.sptech.exceptions`), implemente cada exercício e escreva um `Main` (ou testes JUnit) com os exemplos das tabelas. Faça os exercícios **sem consultar** o código antigo.

| # | Exercício | Tema principal | Nível |
| --- | --- | --- | --- |
| 1 | Loja de Eletrônicos | Herança + exceptions (checked e unchecked) | ⭐⭐ |
| 2 | Academia | Classe abstrata + `@Override` + listas | ⭐⭐⭐ |
| 3 | Seguradora | Classe abstrata + **enum** + validações em ordem | ⭐⭐⭐ |
| 4 | Loja de Promoções | **Interface / Observer** + classe abstrata | ⭐⭐⭐ |
| 5 | Ordenação e Busca | Selection, Bubble, Insertion, busca binária, Big O | ⭐⭐ |

---

# Exercício 1 – Loja de Eletrônicos 🛒

**Objetivo:** praticar herança, sobrescrita de método, exceptions checked/unchecked, `try/catch` e `throws`.

## Diagrama de classes (texto)

```
Produto (nome, preco, estoque)
 ├── Celular   (armazenamentoGb)
 └── Notebook  (ramGb)

Loja (nome, produtos : List<Produto>)
```

## Exceptions (pacote `school.sptech.exceptions`)

Todas devem ter um construtor que recebe a mensagem (`String`) e a repassa para a superclasse.

| Exception | Tipo |
| --- | --- |
| `DadoInvalidoException` | **unchecked** |
| `ProdutoNaoEncontradoException` | **unchecked** |
| `EstoqueInsuficienteException` | **checked** |

## Classe `Produto`

**Construtor:** `Produto(String nome, Double preco, Integer estoque)`

- Lança `DadoInvalidoException` se `nome` for `null` ou em branco.
- Lança `DadoInvalidoException` se `preco` for `null` ou menor ou igual a zero.
- Lança `DadoInvalidoException` se `estoque` for `null` ou negativo (zero é permitido).

**Métodos**

`void vender(Integer quantidade)`

- Lança `DadoInvalidoException` se `quantidade` for `null` ou menor ou igual a zero.
- Lança `EstoqueInsuficienteException` se `quantidade` for maior que o estoque. Como é **checked**, o método precisa declarar `throws`.
- Caso contrário, subtrai a quantidade do estoque.

`void repor(Integer quantidade)`

- Lança `DadoInvalidoException` se `quantidade` for `null` ou menor ou igual a zero.
- Caso contrário, soma a quantidade ao estoque.

`Integer getGarantiaMeses()`

- Retorna **3** meses.

`Double calcularFrete()`

- Retorna **R$ 30,00**.

**Deve conter todos os Getters e Setters.**

## Classe `Celular`

**Construtor:** `Celular(String nome, Double preco, Integer estoque, Integer armazenamentoGb)`

- Lança `DadoInvalidoException` se `armazenamentoGb` for `null` ou menor ou igual a zero.
- Deve inicializar os atributos da superclasse corretamente (`super`).

`getGarantiaMeses` retorna **12**. `calcularFrete` retorna **R$ 15,00**.

## Classe `Notebook`

**Construtor:** `Notebook(String nome, Double preco, Integer estoque, Integer ramGb)`

- Lança `DadoInvalidoException` se `ramGb` for `null` ou menor ou igual a zero.

`getGarantiaMeses` retorna **24**.

`calcularFrete`: retorna **R$ 60,00**, exceto quando o preço for **maior ou igual a R$ 5.000,00**, caso em que o frete é **grátis** (`0.0`).

## Classe `Loja`

**Construtor:** `Loja(String nome)`

- Lança `DadoInvalidoException` se `nome` for `null` ou em branco.
- Inicializa a lista de produtos **vazia**.

**Métodos**

`void adicionarProduto(Produto produto)`

- Lança `DadoInvalidoException` se `produto` for `null`. Caso contrário, adiciona na lista.

`Produto buscarPorNome(String nome)`

- Lança `DadoInvalidoException` se `nome` for `null` ou em branco.
- Busca ignorando maiúsculas/minúsculas. Se não encontrar, lança `ProdutoNaoEncontradoException`.

`Double venderProduto(String nome, Integer quantidade)`

- Lança `DadoInvalidoException` se `nome` ou `quantidade` for `null`.
- Busca o produto (se não encontrar, `ProdutoNaoEncontradoException`) e chama o `vender` dele.
- Se o estoque for insuficiente, **não** lança `EstoqueInsuficienteException`: exibe a mensagem `"Estoque insuficiente para o produto."` e retorna `0.0`.
- Se a venda ocorrer, retorna `preco × quantidade + frete` do produto.

`List<Produto> listarEmEstoque()`

- Retorna uma **nova** lista com os produtos de estoque maior que zero, na ordem de cadastro. Sem produtos, lista vazia.

`Integer contarPorTipo(String tipo)`

- Valores permitidos: `"Celular"` ou `"Notebook"`, ignorando maiúsculas/minúsculas.
- Lança `DadoInvalidoException` se `tipo` for `null`, em branco ou diferente dos permitidos.
- Retorna a quantidade de produtos do tipo, estando ou não em estoque. Sem nenhum, retorna `0`.

`Integer calcularGarantiaMedia()`

- Sem produtos cadastrados, retorna `0`. Caso contrário, retorna a média (inteira) das garantias.

`Double calcularValorTotalEstoque()`

- Retorna a soma de `preco × estoque` de todos os produtos. Sem produtos, retorna `0.0`.

### Desafio ⚡

`Double venderKit(List<String> nomes)`

- Lança `DadoInvalidoException` se `nomes` for `null` ou vazia, se **qualquer** nome for `null` ou em branco, ou se houver nomes **duplicados** (ignorando maiúsculas/minúsculas). Essas validações vêm **antes** de qualquer busca ou venda.
- Se **qualquer** nome não for encontrado, lança `ProdutoNaoEncontradoException` e **nenhum** produto pode ser vendido.
- É **tudo ou nada**: se algum produto estiver com estoque `0`, exibe `"Não foi possível vender o kit: o produto '<nome>' está sem estoque."` (com o **primeiro** sem estoque, seguindo a ordem de `nomes`), retorna `0.0` e nada é vendido.
- Se todos tiverem estoque, vende **1 unidade** de cada e retorna a soma dos preços (sem frete) com **10% de desconto**.

**Exemplos**

| Produto | Preço | Quantidade | Frete | Retorno de `venderProduto` |
| --- | --- | --- | --- | --- |
| Celular | R$ 2.000,00 | 2 | 15,00 | R$ 4.015,00 |
| Notebook | R$ 6.000,00 | 1 | 0,00 | R$ 6.000,00 |
| Notebook | R$ 3.000,00 | 3 | 60,00 | R$ 9.060,00 |
| Kit: Celular R$ 2.000 + Notebook R$ 3.000 |  |  |  | R$ 4.500,00 |

---

# Exercício 2 – Academia 🏋️

**Objetivo:** praticar classe abstrata, métodos abstratos, sobrescrita (`@Override`) e reaproveitamento com `super`.

## Diagrama de classes (texto)

```
Assinante (abstrata): nome, documento, meses, gastosLoja, armario
 ├── PlanoBasico   (aulasColetivas, horarioLivre)
 ├── PlanoPremium  (personal, nutricionista, empresaParceira)
 └── PlanoFamilia  (qtdDependentes)

Academia (nome, assinantes : List<Assinante>)
```

## Exceptions (pacote `school.sptech.exceptions`)

Todas com construtor que recebe a mensagem (`String`).

| Exception | Tipo |
| --- | --- |
| `ValorInvalidoException` | **unchecked** |
| `ArmarioInvalidoException` | **unchecked** |
| `AssinanteForaDaAcademiaException` | **unchecked** |
| `ArmarioOcupadoException` | **checked** |

## Classe abstrata `Assinante`

**Getters e Setters**

- Todos os getters e setters para todos os atributos.
- `setMeses` lança `ValorInvalidoException` se `meses` for `null` ou menor ou igual a zero.
- `setGastosLoja` lança `ValorInvalidoException` se `gastosLoja` for `null` ou negativo (zero é permitido).
- Quando lançar a exception, o valor anterior **não** pode ser alterado.

**Construtor:** `Assinante(String nome, String documento, Integer meses, Double gastosLoja)`

- Aplica as **mesmas validações** dos setters (reaproveite os setters). O atributo `armario` começa `null`.

**Métodos**

`calcularMensalidade` – **abstrato**, retorna `Double`.

`calcularPontos` – **abstrato**, retorna `Integer`.

`getTipo` – **abstrato**, retorna `String`.

`calcularTotalPeriodo` – retorna `Double`, seguindo **nesta ordem**:

1. **Mensalidades:** os **6 primeiros meses** são cobrados pelo valor cheio. Do **7º mês em diante**, **10% de desconto**.
2. **Loja:** os gastos da loja têm **taxa de conveniência de 8%**.
3. O total é a soma das mensalidades com os gastos já com a taxa.

| Meses | Mensalidade | Gastos loja | Mensalidades | Loja com taxa | Total |
| --- | --- | --- | --- | --- | --- |
| 4 | R$ 150,00 | R$ 100,00 | 4 × 150 = 600,00 | 108,00 | R$ 708,00 |
| 8 | R$ 200,00 | R$ 50,00 | 6 × 200 + 2 × 180 = 1.560,00 | 54,00 | R$ 1.614,00 |

## Classe `PlanoBasico`

**Construtor:** `PlanoBasico(String nome, String documento, Integer meses, Double gastosLoja, Boolean aulasColetivas, Boolean horarioLivre)`

`calcularMensalidade`

- Base de **R$ 100,00**.
- Com **aulas coletivas**: acrescenta R$ 40,00.
- Com **horário livre**: acrescenta R$ 30,00. Porém, para quem **também** tem aulas coletivas, o horário livre custa apenas **R$ 20,00**.
- Exemplo (os dois): `100 + 40 + 20 = R$ 160,00`.

`calcularPontos`: **1 ponto a cada R$ 50,00 completos** do total do período.

`getTipo`: `"Basico"`.

## Classe `PlanoPremium`

**Construtor:** `PlanoPremium(String nome, String documento, Integer meses, Double gastosLoja, Boolean personal, Boolean nutricionista, Boolean empresaParceira)`

`calcularMensalidade`, **nesta ordem**:

1. Base de **R$ 250,00**.
2. Com **personal**: acrescenta R$ 300,00.
3. Com **nutricionista**: acrescenta R$ 120,00.
4. Se for de **empresa parceira**: **12% de desconto** sobre o valor já com os adicionais.

- Exemplo (tudo): `(250 + 300 + 120) × 0,88 = R$ 589,60`.

`calcularPontos`: **3 pontos a cada R$ 100,00 completos** do total do período. Quem tem **personal** ganha o **dobro**.

- Exemplo: 6 meses, sem loja, plano com tudo: total R$ 3.537,60 → `35 × 3 = 105` → dobro = **210 pontos**.

`getTipo`: `"Premium"`.

## Classe `PlanoFamilia`

**Construtor:** `PlanoFamilia(String nome, String documento, Integer meses, Double gastosLoja, Integer qtdDependentes)`

`calcularMensalidade`

- Base de **R$ 180,00**.
- Os **2 primeiros dependentes** acrescentam **R$ 60,00 cada**. Do **3º em diante**, **R$ 40,00 cada**.
- Se `qtdDependentes` for `null` ou negativo, considere **0**.
- Exemplo: 3 dependentes = `180 + 120 + 40 = R$ 340,00`.

`calcularTotalPeriodo` – deve **sobrescrever** o de `Assinante`: reaproveite o cálculo da superclasse (sem copiar código) e, se `meses` for **maior ou igual a 12**, aplique **8% de desconto** no total.

- Exemplo: 12 meses, 3 dependentes, sem loja: `6 × 340 + 6 × 306 = 3.876,00` → com desconto = **R$ 3.565,92**.

`calcularPontos`: **1 ponto a cada R$ 80,00 completos** do total do período, **mais 10 pontos por dependente** (mesma regra do `null`/negativo).

- Exemplo: total R$ 3.565,92 e 3 dependentes: `44 + 30 = 74 pontos`.

`getTipo`: `"Familia"`.

## Classe `Academia`

**Getters:** apenas getters.

**Construtor:** `Academia(String nome)` – inicia `nome` e a lista de assinantes **vazia**.

**Métodos**

`void matricular(Assinante assinante, Integer armario)`

- `ValorInvalidoException` se `assinante` for `null`.
- Se o assinante **já estiver** na academia, não faz nada (sem exception e sem trocar o armário).
- `ArmarioInvalidoException` se `armario` for `null` ou menor ou igual a zero.
- `ArmarioOcupadoException` (**checked**, declare `throws`) se o armário já estiver ocupado.
- Caso contrário, define o armário do assinante e o adiciona **no fim** da lista.

`Double cancelar(Assinante assinante)`

- `AssinanteForaDaAcademiaException` se não estiver na academia (inclusive `null`).
- Remove da lista, define o armário como `null` e **retorna o total do período**.

`Boolean verificarArmarioLivre(Integer armario)`

- `ArmarioInvalidoException` se `armario` for `null` ou menor ou igual a zero.
- `true` se ninguém usa o armário, `false` caso contrário.

`Boolean transferirArmario(Assinante assinante, Integer novoArmario)`

- `AssinanteForaDaAcademiaException` se o assinante não estiver na academia.
- `ArmarioInvalidoException` se `novoArmario` for `null` ou menor ou igual a zero.
- Se o novo armário estiver ocupado (inclusive pelo próprio assinante), **não** lança exception: retorna `false` e não altera nada.
- Caso contrário, atualiza o armário e retorna `true`.

`Integer buscarPrimeiroArmarioLivre(Integer inicio, Integer fim)`

- `ArmarioInvalidoException` se `inicio` ou `fim` for `null`, ou se `inicio` for menor ou igual a zero.
- `ValorInvalidoException` se `inicio` for maior que `fim`.
- Retorna o **primeiro** armário livre do intervalo (incluindo `inicio` e `fim`). Se todos estiverem ocupados, retorna `null`.

`Assinante buscarAssinanteComMaisPontos()`

- Retorna o assinante com **mais pontos**. Empate: o de **maior total do período**. Persistindo o empate: o que aparece **primeiro** na lista. Sem assinantes, retorna `null`.

`List<PlanoPremium> getPremiums()`

- Retorna uma **nova** lista só com os assinantes `PlanoPremium`, na ordem da lista. Sem premium, lista vazia.

`Integer aplicarBeneficios()`

- Percorre os assinantes e aplica um benefício conforme o tipo:
  - **PlanoBasico** com `horarioLivre` falso e `aulasColetivas` verdadeiro ganha o horário livre.
  - **PlanoPremium** de `empresaParceira` que **não** tem nutricionista ganha a nutricionista.
  - **PlanoFamilia** não recebe benefício.
- Retorna quantos assinantes receberam benefício (quem já tinha **não** conta). Sem assinantes, retorna `0`.

---

# Exercício 3 – Seguradora 🚗🛡️

**Objetivo:** praticar classe abstrata, **enum com atributo**, sobrecarga de método e validações em ordem.

## Diagrama de classes (texto)

```
Veiculo (placa, modelo, marca, valorFipe, sinistros)
Pessoa (nome, cpf)          Empresa (razaoSocial, cnpj)
<<enum>> Cobertura (adicional)

Apolice (abstrata): veiculo, cobertura, parcelas, parcelasPagas
 ├── ApolicePF (pessoa)
 └── ApolicePJ (empresa)

Seguradora (nome, apolices : List<Apolice>)
```

## Exceptions (pacote `school.sptech.exceptions`)

| Exception | Tipo |
| --- | --- |
| `ValorInexistenteException` | **unchecked** |
| `ContratacaoInvalidaException` | **unchecked** |
| `DocumentoInvalidoException` | **unchecked** |
| `PagamentoInvalidoException` | **unchecked** |

## Enum `Cobertura`

- O atributo `adicional` deve possuir **apenas getter**. Valores em **decimal** (`15%` = `0.15`).

| Constante | Adicional |
| --- | --- |
| `BASICA` | `0.0` |
| `INTERMEDIARIA` | `0.15` |
| `COMPLETA` | `0.35` |

## Classe `Veiculo`

**Getters e Setters** para todos os atributos.

**Construtor:** `Veiculo(String placa, String modelo, String marca, Double valorFipe, Integer sinistros)`

`Double calcularPremioBase()`, **nesta ordem**:

1. **3%** do valor Fipe.
2. **+ R$ 200,00** por sinistro.
3. Se a marca for `"Ferrari"`, `"Lamborghini"` ou `"Porsche"` (ignorando maiúsculas/minúsculas), **+20%** sobre o valor acumulado até aqui.

| Marca | Fipe | Sinistros | Cálculo | Prêmio base |
| --- | --- | --- | --- | --- |
| Chevrolet | R$ 60.000,00 | 2 | 1.800 + 400 | R$ 2.200,00 |
| Ferrari | R$ 1.000.000,00 | 1 | (30.000 + 200) × 1,2 | R$ 36.240,00 |

## Classes `Pessoa` e `Empresa`

Todos os getters e setters. Construtores: `Pessoa(String nome, String cpf)` e `Empresa(String razaoSocial, String cnpj)`.

## Classe abstrata `Apolice`

**Getters e Setters** para todos os atributos.

**Construtor:** `Apolice(Veiculo veiculo, Cobertura cobertura, Integer parcelas)` – inicia `parcelasPagas` com `0`.

**Métodos**

`calcularValorTotal` – **abstrato**, retorna `Double`.

`calcularValorParcela` – retorna `valor total ÷ parcelas`.

`pagarParcela(Double valor)` – `void`. Valida **nesta ordem**:

1. `valor` `null` ou **diferente** da parcela → `PagamentoInvalidoException`.
2. Apólice já quitada (`parcelasPagas` igual a `parcelas`) → `PagamentoInvalidoException`.

- Estando tudo certo, aumenta em 1 as parcelas pagas.

`calcularValorPago` – valor pago até o momento. `calcularValorPendente` – valor que falta pagar.

## Classe `ApolicePF`

**Construtor:** `ApolicePF(Veiculo veiculo, Cobertura cobertura, Integer parcelas, Pessoa pessoa)`

`calcularValorTotal`: `prêmio base × (1 + adicional da cobertura)`. Se o veículo tiver **0 sinistros**, aplica **10% de desconto** sobre esse valor.

## Classe `ApolicePJ`

**Construtor:** `ApolicePJ(Veiculo veiculo, Cobertura cobertura, Integer parcelas, Empresa empresa)`

`calcularValorTotal`, **nesta ordem**: `prêmio base × (1 + adicional)`; **desconto de 10%** sobre esse valor; **+ R$ 500,00** de taxa administrativa.

| Tipo | Prêmio base | Cobertura | Cálculo | Total |
| --- | --- | --- | --- | --- |
| PF (2 sinistros) | 2.200,00 | INTERMEDIARIA | 2.200 × 1,15 | R$ 2.530,00 |
| PF (0 sinistros) | 1.800,00 | BASICA | 1.800 × 1,0 × 0,90 | R$ 1.620,00 |
| PJ | 2.200,00 | COMPLETA | 2.200 × 1,35 × 0,90 + 500 | R$ 3.173,00 |

## Classe `Seguradora`

**Getters:** apenas getters. **Construtor:** `Seguradora(String nome)` – inicia `nome` e a lista **vazia**.

**Métodos**

`void contratar(Pessoa pessoa, Veiculo veiculo, Cobertura cobertura, Integer parcelas)` (**pessoa física**). Valida **nesta ordem**:

1. `pessoa`, `veiculo`, `cobertura` ou `parcelas` `null` → `ValorInexistenteException`.
2. `parcelas` menor ou igual a `0` ou maior que `48` → `ContratacaoInvalidaException`.
3. CPF `null` ou **sem exatamente 11 caracteres** → `DocumentoInvalidoException`.

- Tudo certo: cria uma `ApolicePF` e adiciona na lista.

`void contratar(Empresa empresa, Veiculo veiculo, Cobertura cobertura, Integer parcelas)` (**pessoa jurídica**): mesmas regras, trocando CPF por CNPJ com **exatamente 14 caracteres**, e cria uma `ApolicePJ`.

`List<Apolice> buscarPorCpf(String cpf)` – apólices de **pessoa física** com CPF **igual** ao buscado. Sem resultado, lista vazia.

`List<Apolice> buscarPorNomeOuRazaoSocial(String texto)` – apólices cujo **nome da pessoa** ou **razão social** **contém** o texto, ignorando maiúsculas/minúsculas. Ex.: `"silva"` encontra `"Ana Silva"` e `"Silva & Filhos LTDA"`. Sem resultado, lista vazia.

`void pagarParcela(Double valor, String placa)` – `ValorInexistenteException` se `valor` ou `placa` for `null`. Busca a apólice do veículo com a placa e chama o `pagarParcela` **dela** (só ela é alterada). Se não existir apólice com a placa, `ValorInexistenteException`.

`Double totalPago()` e `Double totalPendente()` – somam o valor pago / pendente de **todas** as apólices. Sem apólices, `0.0`.

---

# Exercício 4 – Loja de Promoções 🏷️ (Interface / Observer)

**Objetivo:** praticar interface, padrão Observer, classe abstrata, enum e streams/loops sobre listas.

> **Observer:** o objeto observado (`Loja`) mantém uma lista de observadores inscritos e os notifica a cada evento (`publicar`). Cada observador decide o que fazer com a notícia (`receberPromocao`).

## Diagrama de classes (texto)

```
<<enum>> Categoria { ELETRONICOS, MODA, CASA, ESPORTE }
Promocao (produto, categoria, precoOriginal, precoPromocional)

<<interface>> Observador { void receberPromocao(Promocao promocao) }
 ├── Cliente (nome, email, categoriaInteresse, promocoesRecebidas, ofertasRelevantes)
 └── Estatistica (abstrata) (qtdPromocoesRecebidas)
      ├── EstatisticaCategoria (categoria, qtdPromocoesCategoria)
      └── EstatisticaDescontoAlto (descontoMinimo, qtdDescontoAlto)

Loja (nome, observadores, promocoesPublicadas)
```

## `PromocaoInvalidaException`

Deve ser **unchecked** (herda de `RuntimeException`).

## Classe `Promocao`

**Construtor cheio:** `Promocao(String produto, Categoria categoria, Double precoOriginal, Double precoPromocional)`. Lança `PromocaoInvalidaException` se:

- `produto` for `null` ou em branco;
- `categoria` for `null`;
- `precoOriginal` for `null` ou menor ou igual a zero;
- `precoPromocional` for `null`, menor ou igual a zero, ou **maior ou igual** ao `precoOriginal`.

`Double getDesconto()` – retorna o desconto percentual (0 a 100): `(original − promocional) ÷ original × 100`.

Apenas **getters** para todos os atributos.

## Interface `Observador`

`void receberPromocao(Promocao promocao)` – método abstrato chamado para notificar sobre uma nova promoção.

## Classe `Cliente` (implementa `Observador`)

**Construtor:** `Cliente(String nome, String email, Categoria categoriaInteresse)` – inicializa os atributos e as duas listas **vazias**.

`receberPromocao(Promocao promocao)`

- Adiciona a promoção em `promocoesRecebidas`.
- Se a categoria da promoção for igual à `categoriaInteresse` **e** o desconto for **maior ou igual a 20%**, adiciona também em `ofertasRelevantes`.

Apenas **getters** para todos os atributos.

## Classe abstrata `Estatistica` (implementa `Observador`)

**Construtor:** `Estatistica()` – `qtdPromocoesRecebidas` começa em zero.

`Double getPercentual()` – **abstrato**: percentual (0 a 100) da estatística sobre o total recebido.

`receberPromocao(Promocao promocao)` – incrementa `qtdPromocoesRecebidas`.

Apenas **getters** para todos os atributos.

## Classe `EstatisticaCategoria`

**Construtor:** `EstatisticaCategoria(Categoria categoria)` – inicia `categoria` e `qtdPromocoesCategoria` com zero.

`receberPromocao` – atualiza o total recebido (reaproveite o `super`) e, se a categoria da promoção for igual à da estatística, incrementa `qtdPromocoesCategoria`.

`getPercentual` – percentual de promoções da categoria sobre o total. Se o total for zero, retorna `0.0`.

## Classe `EstatisticaDescontoAlto`

**Construtor:** `EstatisticaDescontoAlto(Double descontoMinimo)` – inicia `descontoMinimo` e `qtdDescontoAlto` com zero.

`receberPromocao` – atualiza o total e, se o desconto da promoção for **maior ou igual** ao `descontoMinimo`, incrementa `qtdDescontoAlto`.

`getPercentual` – percentual de promoções com desconto alto sobre o total. Se o total for zero, retorna `0.0`.

## Classe `Loja`

**Construtor:** `Loja(String nome)` – inicia `nome`, `observadores` e `promocoesPublicadas` como listas **vazias**.

**Métodos**

`inscrever(Observador observador)` – adiciona ao **final** da lista.

`desinscrever(Observador observador)` – remove da lista.

`publicar(Promocao promocao)` – adiciona a promoção em `promocoesPublicadas` e notifica **todos** os observadores chamando `receberPromocao`.

`Estatistica getMaiorPercentual()` – retorna a estatística inscrita com o **maior percentual**. Sem estatísticas inscritas, retorna `null`.

`List<Cliente> getClientesPorCategoria(Categoria categoria)` – clientes inscritos cuja `categoriaInteresse` é igual à informada, na ordem de inscrição. Sem correspondência, lista vazia.

`Promocao getPromocaoMaiorDesconto()` – retorna a promoção publicada com o **maior desconto**. Empate: a **primeira** publicada. Sem promoções, retorna `null`.

`Double getDescontoMedio()` – média dos descontos das promoções publicadas. Sem promoções, retorna `0.0`.

### Desafio 🔥

`Categoria getCategoriaMaisProcurada()` – categoria que mais aparece como `categoriaInteresse` entre os clientes inscritos. Empate: a que aparecer **primeiro** entre os clientes inscritos. Sem clientes, retorna `null`.

`Promocao getPromocaoMaisRelevante()` – promoção que aparece em `ofertasRelevantes` do **maior número** de clientes inscritos. Se não houver clientes inscritos ou nenhum tiver ofertas relevantes, retorna `null`.

**Exemplo de desconto**

| Original | Promocional | Desconto | Relevante para quem tem a categoria como interesse? |
| --- | --- | --- | --- |
| R$ 100,00 | R$ 80,00 | 20,0% | Sim (20% conta) |
| R$ 100,00 | R$ 85,00 | 15,0% | Não |
| R$ 500,00 | R$ 300,00 | 40,0% | Sim |

---

# Exercício 5 – Ordenação e Busca 📎

**Objetivo:** dominar os algoritmos clássicos a partir do pseudo-código e adaptá-los (descendente, `String`, contagens). Use **apenas wrappers** para variáveis. Para trocar, use uma variável auxiliar.

## 5.1 – Bubble Sort Decrescente

`void bubbleSortDecrescente(Integer[] v)` – ordena do **maior para o menor**. Dica: só muda o sinal da comparação.

| Entrada | Saída |
| --- | --- |
| \[5, 1, 4, 2, 8\] | \[8, 5, 4, 2, 1\] |

## 5.2 – Insertion Sort de Strings

`void insertionSortStrings(String[] v)` – ordem alfabética crescente. Use `compareTo` no lugar de `>`.

```
insertionSortStrings(String[] v)
início
    inteiro i, j; texto x;
    para i de 1 enquanto i < v.length faça
    início
        x <- v[i];
        j <- i - 1;
        enquanto (j >= 0) e (v[j] compareTo x > 0) faça
        início
            v[j+1] <- v[j];
            j <- j - 1;
        fim
        v[j+1] <- x;
    fim
fim
```

| Entrada | Saída |
| --- | --- |
| \["uva", "banana", "abacaxi", "cereja"\] | \["abacaxi", "banana", "cereja", "uva"\] |

## 5.3 – Selection Sort que conta trocas

`Integer selectionSortContandoTrocas(Integer[] v)` – Selection Sort **otimizado** (guarda o índice do menor). Retorna a quantidade de trocas **realmente feitas**: só conta quando `indMenor` é diferente de `i`.

| Entrada | Saída (array) | Retorno |
| --- | --- | --- |
| \[64, 25, 12, 22, 11\] | \[11, 12, 22, 25, 64\] | 3 |
| \[1, 2, 3\] | \[1, 2, 3\] | 0 |

## 5.4 – Bubble Sort com parada antecipada

`Integer bubbleSortOtimizado(Integer[] v)` – igual ao Bubble Sort, mas **interrompe** quando uma passada completa não faz nenhuma troca. Retorna a **quantidade de passadas** (iterações do laço externo) executadas, incluindo a última, sem trocas.

```
bubbleSortOtimizado(int[] v)
início
    inteiro i, j, passadas; lógico trocou;
    passadas <- 0; i <- 0; trocou <- verdadeiro;
    enquanto (i < v.length-1) e (trocou) faça
    início
        trocou <- falso;
        passadas <- passadas + 1;
        para j de 1 enquanto j < v.length-i faça
        início
            se v[j-1] > v[j]
            então troca(v[j], v[j-1]); trocou <- verdadeiro;
        fim
        i <- i + 1;
    fim
    retorna passadas;
fim
```

| Entrada | Retorno |
| --- | --- |
| \[1, 2, 3, 4\] | 1 |
| \[2, 1, 3, 4, 5\] | 2 |
| \[4, 3, 2, 1\] | 4 |

## 5.5 – Ordenar objetos

Usando o **Exercício 1**, implemente na `Loja`: `List<Produto> listarOrdenadosPorPreco()` – retorna uma **nova** lista com os produtos do **menor para o maior preço**, usando **Insertion Sort escrito por você** (sem `Collections.sort` e sem `sort` de stream). A lista original **não** pode ser alterada. Sem produtos, lista vazia.

## 5.6 – Busca Binária

`Integer buscaBinaria(Integer[] v, Integer alvo)` – recebe um array **já ordenado** e retorna o **índice** do alvo, ou `-1` se não existir.

```
buscaBinaria(int[] v, int alvo)
início
    inteiro inicio, fim, meio;
    inicio <- 0; fim <- v.length - 1;
    enquanto inicio <= fim faça
    início
        meio <- (inicio + fim) / 2;
        se v[meio] = alvo então retorna meio;
        se v[meio] < alvo então inicio <- meio + 1;
        senão fim <- meio - 1;
    fim
    retorna -1;
fim
```

| Entrada | Alvo | Retorno |
| --- | --- | --- |
| \[11, 12, 22, 25, 64\] | 22 | 2 |
| \[11, 12, 22, 25, 64\] | 30 | -1 |

## 5.7 – Teoria (Big O)

Responda sem consultar:

1. Qual a complexidade de pior caso do Selection, do Bubble e do Insertion Sort? E da Busca Binária?
2. Por que o Selection Sort otimizado continua sendo O(n²) mesmo fazendo menos trocas?
3. Em qual caso o `bubbleSortOtimizado` se aproxima de O(n)?
4. Se o array dobra de tamanho, o que acontece com o tempo de um algoritmo O(n²)? E de um O(n)?

---

# Checklist final antes de entregar ✅

- [ ] Todos os atributos são `private`, com getters/setters apenas onde o enunciado pede (cuidado com "**apenas** getters").
- [ ] Só tipos wrapper (`Integer`, `Double`, `Boolean`) em atributos, parâmetros e retornos.
- [ ] Exceptions **checked** herdam de `Exception` e o método tem `throws`; **unchecked** herdam de `RuntimeException`.
- [ ] Construtores das subclasses chamam `super(...)` e validam os campos novos.
- [ ] Validações na **ordem** do enunciado, com `null` verificado **antes** de comparar valores.
- [ ] Setters que lançam exception **não** alteram o valor anterior.
- [ ] Comparações de texto com `equalsIgnoreCase`; strings em branco com `isBlank()`.
- [ ] Retornos de lista são **novas listas** (`new ArrayList<>()`), na ordem de cadastro.
- [ ] Divisões e médias protegidas contra divisão por zero (lista vazia retorna `0`, `0.0` ou `null`, conforme o enunciado).
- [ ] `@Override` nos métodos sobrescritos; reaproveitar `super.metodo()` em vez de copiar código.
- [ ] Projeto compila sem erros.

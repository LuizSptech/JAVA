package school.sptech;

public class Jogador {

        String nome;
        Double saldo;
        Integer quantidadeFichas;
        Integer quantidadePartidas;
        Integer vitorias;



        Boolean validarJogador(){

            if (nome == null){
                return false;

            } if (saldo == null || saldo < 0.0) {
                return false;
            }
            return true;
        }


        void registrarPartida(Boolean vitoria){
            quantidadePartidas++;
            if (vitoria == true){
                vitorias++;
            }
        }
        Boolean consumirFicha(Integer quantidadeFichasParaConsumo){
            if (quantidadeFichasParaConsumo < 0){
                return false;
            }
            if (quantidadeFichasParaConsumo > quantidadeFichas){
                return false;
            }
            quantidadeFichas -= quantidadeFichasParaConsumo;

            return true;
        }

        Boolean comprarFichas(Integer quantidadeFichasParaComprar){
            Double preco = 0.0;
            if (quantidadeFichasParaComprar < 0){
                return false;
            } else if (quantidadeFichasParaComprar <= 5 ){
                preco = 5.0;

            } else if (quantidadeFichasParaComprar <= 10){
                preco = 4.0;
            }else if (quantidadeFichasParaComprar > 10) {
                preco = 3.0;
            }

            Double valor = quantidadeFichasParaComprar * preco;
            if (valor > saldo){
                return false;
            }

                saldo -= valor;
                quantidadeFichas += quantidadeFichasParaComprar;

            return true;

        }

        Integer encontrarMaiorPontuacao(Integer[] pontuacoes){
            Integer t = pontuacoes.length;
            if (t == 0){
                return null;
            }        Integer maior = pontuacoes[0];
            for (int i = 0; i < pontuacoes.length; i++) {

                if (pontuacoes[i] > pontuacoes[0]){
                    maior = pontuacoes[i];
                }

            }
            return maior;
        }

        Integer contarPontuacoesAcimaDaMedia(Integer[] pontuacoes){
            Integer t = pontuacoes.length;
            Integer soma = 0;
            Integer media = 0;
            Integer contador = 0;

            if (t <= 1){
                return 0;
            }
            for (int i = 0; i < t; i++) {
                soma += pontuacoes[i];
            }
            media = soma/t;

            for (int i = 0; i < t; i++) {
                if (pontuacoes[i] > media){
                    contador++;
                }
            }
            return contador;
        }

        Integer encontrarMaiorSequenciaDeVitorias(Boolean[] resultados){
            //Travei aqui
            Integer t = resultados.length;
            if (t <= 1){
                return 0;
            }
            Integer cont = 0;
            Integer n = 0;
            for (int i = 0; i < t; i++) {
                    if (resultados[i] == true){
                        cont++;
                        n++;
                    }
                    if (resultados[i] == false){
                        n--;
                    }
            }

            return n;
            }



    }

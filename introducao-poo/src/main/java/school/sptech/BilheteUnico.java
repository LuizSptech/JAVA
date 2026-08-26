package school.sptech;

// Classe e a forma do bolo
//Definida como um bilhete unico deve ser

public class BilheteUnico {


        //CARACTERISTICAS EM POO: ATRIBUTOS (Fields)


        String titular;
        String codigo;
        Double saldo;
        Boolean isOld;
        Boolean isEsdudent;
        String cor;

        //sempre validar nulo primeiro
        //Comportamentos são metodos
        Boolean recarregar(Double valor){
           if (valor == null || valor <= 0){
               System.out.println("Valor invalido para recarga");
               return false;
           } if (valor > 300){
                System.out.println("Valor maximo para recarga atingido");
                return false;
            }
           saldo += saldo + valor;
            System.out.println("Recarga realizada com sucesso");
           return true;
       }

       // 5.4 tarifa
       Boolean passarBilhete(){
            if (isOld){
                System.out.println("Subsidio aplicado");
                return true;
            }
            Double tarifaVigente = 5.4;
            Double valorAPagar = isEsdudent ? tarifaVigente / 2.0 : tarifaVigente;
            if (valorAPagar > saldo){
                System.out.println("Saldo insuficiente");
                return false;
            }
            saldo -= valorAPagar;
            return true;
       }
    }

package school.sptech;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //New -> criando uma nova instância de Bilhete
        //ou seja, um novo objeto

        BilheteUnico b01 = new BilheteUnico();
        b01.titular = "Lucas";
        b01.codigo = "814.123.654-21";
        b01.cor = "Azul";
        b01.isOld = false;
        b01.isEsdudent = true;
        b01.saldo = 1.0;
        b01.recarregar(10.0);
        System.out.println("Saldo do b01 " + b01.saldo );

        BilheteUnico b02 = new BilheteUnico();
        b02.titular = "Bob";
        b02.codigo = "123.321.456-78";
        b02.saldo = 0.0;
        b02.isOld = true;
        b02.isEsdudent = false;
        b02.cor = "Vermelho";

        b01.printarInformacoes();
        b02.printarInformacoes();
        b02.passarBilhete();

//------------------------------------------------------------------
        List<BilheteUnico> bilhetes = new ArrayList<>();
        bilhetes.add(b01);
        bilhetes.add(b02);



    }
}

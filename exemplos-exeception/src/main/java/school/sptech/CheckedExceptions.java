package school.sptech;


import java.io.FileNotFoundException;

// Cheked Exceptions: precisa tratar (Obrigatoriamente)
// Se não tratar, dá erro de compilação
// Herdam de Exception
public class CheckedExceptions {
    public static void main(String[] args) {
        try {

            Leitor leitor = new Leitor();
            leitor.ler();
         } catch (FileNotFoundException e){
            System.out.println("Arquivo não achado");
        }


    }
}

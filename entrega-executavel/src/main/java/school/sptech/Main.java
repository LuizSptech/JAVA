package school.sptech;

public class Main {
    public static void main(String[] args) {
        Aviso teste = new Aviso();




        teste.Login("Luiz","1234","12345");
        teste.Login(null,null,null);


        teste.capturaDados(false);
        teste.capturaDados(true);

    }
}

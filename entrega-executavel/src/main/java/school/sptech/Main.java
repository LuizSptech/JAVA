package school.sptech;

public class Main {
    public static void main(String[] args) {
        Aviso login = new Aviso();
        login.nivel = "INFO";


        login.login_feito("Luiz","1234","12345");
        login.login_feito(null,null,null);

    }
}

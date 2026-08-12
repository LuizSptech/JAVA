package school.sptech;

public class Condicionais {
    public static void main(String[] args) {
        //Condicionais: if, else-if, else, ternario
        Boolean sobrou = true;

        if (sobrou){
            System.out.printf("SOBROU!!!!");
        }else {
            System.out.println("Sobra nada...");
        }

        Integer idade = 10;

        if (idade < 16){
            System.out.println("Não pode fazer nada");
        } else if (idade < 18){
            System.out.println("Pode votar");
        } else {
            System.out.println("Pode votar e dirigir");
        }

        String mensagem = idade >= 18 ? "Maior de idade" : "Menor de idade";
        System.out.println(mensagem);

    // && e || mantém
    // >, <, <=, >=, ==, ===
    }


}

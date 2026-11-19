package school.sptech;

public interface Agendavel {

    abstract String getNome();
    abstract void reservarVaga();
    abstract Integer getVagasDisponiveis();
    abstract Integer getCapacidade();


}

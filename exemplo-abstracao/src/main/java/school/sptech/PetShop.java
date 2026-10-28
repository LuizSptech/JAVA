package school.sptech;

import java.util.ArrayList;
import java.util.List;

public class PetShop {
    private String nome;
    private List<Pet> pets;

    public PetShop(String nome) {
        this.nome = nome;
        this.pets = new ArrayList<>();
    }

    public void receberPet(Pet pet){
        this.pets.add(pet);
    }

    public void alimentarPets(){
        for (Pet pet : pets) {
            System.out.println("=".repeat(50));
            System.out.println(pet.getNome());
            pet.comer();
            pet.emitirSom();
        }
    }


    @Override
    public String toString() {
        return "PetShop{" +
                "nome='" + nome + '\'' +
                ", pets=" + pets +
                '}';
    }
}

package school.sptech;
public class Main {
    public static void main(String[] args) {

       // Pet pet01 = new Pet("Princesa", 14, 7.0,  0.3);
        Pet cachorro01 = new Cachorro("Caramelo", 5, 10.0, 1.0, true);
        Pet cachorro02 = new Cachorro("Catapimbas", 5, 10.0, 1.0, true);
        Pet gato01 = new Cachorro("Mia", 4,5.0, 0.45, true);



        PetShop petshop = new PetShop("SPpet");
        petshop.receberPet(cachorro01);
        petshop.receberPet(cachorro02);
        petshop.receberPet(gato01);
        petshop.alimentarPets();
    }
}
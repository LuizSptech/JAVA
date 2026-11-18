package school.sptech;

public class SelectionSortOtimizado {

    public void selectionSortOtimizado(int[] v) {
        int aux =  0;
        for (int i = 0; i < v.length; i++) {
            int menor = i;
            for (int j = i+1; j < v.length; j++) {
                if (v[j] < v[menor]){
                    menor = j;
                }
            }
            aux = v[menor];
            v[menor] = v[i];
            v[i] = aux;


        }
    }
}

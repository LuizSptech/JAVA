package school.sptech;


public class SelectionSort {

    public void selectionSort(int[] v) {
        int aux =  0;
        for (int i = 0; i < v.length; i++) {
            for (int j = i+1; j < v.length; j++) {
                if (v[j] < v[i]){
                    aux = v[j];
                    v[j] = v[i];
                    v[i] = aux;
                }
            }
        }
    }

}

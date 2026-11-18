package school.sptech;

public class BubbleSort {

    public void bubbleSort(int[] v) {
        int aux = 0;
        for (int i = 0; i < v.length; i++) {

            for (int j = 1; j < v.length - i; j++) {
                if (v[j-1] > v[j]){
                    aux = v[j];
                    v[j] = v[j-1];
                    v[j-1] = aux;
                }
            }
        }
    }
}

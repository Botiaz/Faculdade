package OrdenacaoMerge;

public class OrdenacaoMerge {
    public static void intercalar(int arr[], int esquerda, int meio, int direita) {
        int n1 = meio - esquerda + 1;
        int n2 = direita - meio;

        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = arr[esquerda + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[meio + 1 + j];
        }

        int i = 0, j = 0, k = esquerda;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public static void mergeSort(int arr[], int esquerda, int direita) {
        if (esquerda < direita) {
            int meio = esquerda + (direita - esquerda) / 2;
            mergeSort(arr, esquerda, meio);
            mergeSort(arr, meio + 1, direita);
            intercalar(arr, esquerda, meio, direita);
        }
    }

    public static void imprimirArray(int arr[]) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int arr[] = {12, 11, 13, 5, 6, 7};
        int tamanho = arr.length;

        System.out.println("Array original:");
        imprimirArray(arr);

        mergeSort(arr, 0, tamanho - 1);

        System.out.println("Array ordenado:");
        imprimirArray(arr);
    }
}
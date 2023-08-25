#include <stdio.h>
#include <stdlib.h>
#include <string.h> // Para usar a função strcpy

int main() {
    int n;
    FILE *arquivo;
    arquivo = fopen("Entrada.txt", "r");

    fscanf(arquivo, "%d", &n);
    char v[n][20]; // Usar matriz de strings para armazenar os valores

    for (int i = 0; i < n; i++) {
        fscanf(arquivo, "%s", v[i]);
    }
    fclose(arquivo);

    char inverso[n][20]; // Usar matriz de strings para armazenar os valores invertidos

    for (int i = 0; i < n; i++) {
        strcpy(inverso[i], v[n - 1 - i]);
    }

    for (int i = 0; i < n; i++) {
        if (inverso[i][0] == '.') {
            printf("0%s\n", inverso[i]);
        } else {
            printf("%s\n", inverso[i]);
        }
    }

    return 0;
}

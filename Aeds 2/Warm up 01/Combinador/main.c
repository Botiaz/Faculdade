#include <stdio.h>
#include <string.h>

int main() {
    char str1[100];
    char str2[100];
    char resultado[300]; // Tamanho suficiente para a concatena��o das duas frases

    printf("Digite a primeira frase: ");
    scanf(" %[^\n]", str1); // L� a primeira frase at� a quebra de linha

    printf("Digite a segunda frase: ");
    scanf(" %[^\n]", str2); // L� a segunda frase at� a quebra de linha

    int len1 = strlen(str1);
    int len2 = strlen(str2);

    int i, j, k;

    // Concatenando letra por letra
    for (i = 0, j = 0, k = 0; i < len1 || j < len2; i++, j++) {
        if (i < len1) {
            resultado[k++] = str1[i];
        }
        if (j < len2) {
            resultado[k++] = str2[j];
        }
    }

    resultado[k] = '\0'; // Adicione o caractere nulo de termina��o da string

    printf("Resultado da concatena��o letra por letra: %s\n", resultado);

    return 0;
}

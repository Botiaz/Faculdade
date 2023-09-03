#include <stdio.h>
#include <stdbool.h>
#include <string.h>

bool isPalindrome(char s[], int tamanho) {
    bool resp = true;

    for (int i = 0; i < tamanho / 2; i++) {
        if (s[i] != s[tamanho - i - 1]) {
            resp = false;
            break; // Não é necessário verificar o restante da string.
        }
    }

    return resp;
}

int main() {
    char s[1000];
    int tamanho;

    while (true) {

        // Remova o caractere de nova linha do final da string lida por fgets.
        if (fgets(s, sizeof(s), stdin) == NULL) {
            break;
        }

        tamanho = strlen(s);
        if (tamanho > 0 && s[tamanho - 1] == '\n') {
            s[tamanho - 1] = '\0';
        }

        if (strcmp(s, "FIM") == 0) {
            break;
        } else {
            tamanho = strlen(s);
            if (isPalindrome(s, tamanho) == true) {
                printf("SIM\n");
            } else {
                printf("NAO\n");
            }
        }
    }
    return 0;
}


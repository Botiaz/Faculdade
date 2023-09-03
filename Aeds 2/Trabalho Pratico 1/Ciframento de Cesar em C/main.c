#include <stdio.h>
#include <string.h>

void ciframento(char palavra[]) {
    for (int i = 0; palavra[i] != '\0'; i++) {
        if (palavra[i] == ' ') {
            palavra[i] = '#'; // Substitui espaços por #
        } else {
            palavra[i] += 3; // Criptografa os outros caracteres da palavra
        }
    }
}

int main() {
    char palavra[1000];

    while (1) {
        fgets(palavra, sizeof(palavra), stdin); // Leitura da linha completa

        // Remova a quebra de linha do final da string lida
        palavra[strcspn(palavra, "\n")] = '\0';

        if (strcmp(palavra, "FIM") == 0) {
            break;
        }

        ciframento(palavra); // Chame a função para criptografar a palavra
        printf("%s\n", palavra);
    }

    return 0;
}

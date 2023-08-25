#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

bool verificaPalindromo(char palindromo[]){
    int n = strlen(palindromo);
    bool resp = true;
    for(int i = 0; i < n / 2; i++) {
        if (palindromo[i] != palindromo[n - 1 - i]) {
            resp = false;
            break;
        }
    }
    return resp;
}

int main(){
    char palindromo[1000];
    while (1) {
        fgets(palindromo, sizeof(palindromo), stdin);

        // Remover o caractere de nova linha (Enter) do final da string lida por fgets
        int len = strlen(palindromo);
        if (len > 0 && palindromo[len - 1] == '\n') {
            palindromo[len - 1] = '\0';
        }

        if (strcmp(palindromo, "FIM") == 0)
            break;
        else {
            if (verificaPalindromo(palindromo))
                printf("SIM\n");
            else
                printf("NAO\n");
        }
    }
    return 0;
}

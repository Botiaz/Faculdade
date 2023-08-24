#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

bool verificaPalindromo(char palindromo[]){
    int n = strlen(palindromo);
    bool resp;
    char aux;
    char inverso[1000];
    for(int i=0; i<n; i++){
        inverso[i] = palindromo[n-1-i];
    }
    if(strcmp(palindromo, inverso) == 0){
        resp = true;
    }
    else{
        resp = false;
    }
    return resp;
}

int main(){
    char palindromo[1000];
    for(int i=0; ; i++){
        scanf(" %s", &palindromo);
        if(strcmp(palindromo, "FIM") == 0)
            break;
        else{
            if(verificaPalindromo(palindromo) == true)
                printf("SIM\n");
            else{
                printf("NAO\n");
        }

    }
    }
    return 0;
}

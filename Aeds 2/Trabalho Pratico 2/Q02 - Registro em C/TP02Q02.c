#include <stdio.h>
#include <stdlib.h>

int main(){
    int id, peso, altura;
    char nome[], estadoNascimento[], cidadeNascimento[];
    FILE *arquivo;
    arquivo = fopen("players" , "r");
    for(int i=0; ;i++){
        scanf("%d",&id);
        if(id == "FIM")
            break;
        else
            fgets()
    }
    return 0;
}

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void alteracao(char texto[], int i) {
  char caracter = 'a' + (rand() % 26);
  char caracterTrocar = 'a' + (rand() % 26);

  if (i < strlen(texto)) {
    if (texto[i] == caracter) {
      texto[i] = caracterTrocar;
    } else {
      texto[i] = texto[i];
    }
    alteracao(texto, i + 1);
  }
}

int main() {
  srand(4);
  char texto[500];
  scanf("%[^\n]", texto);
  getchar();

  while (!(strlen(texto) == 3 && texto[0] == 'F' && texto[1] == 'I' &&
           texto[2] == 'M')) {
    alteracao(texto, 0);
    printf("%s\n", texto);
    scanf("%[^\n]", texto);
    getchar();
  }
  return 0;
}


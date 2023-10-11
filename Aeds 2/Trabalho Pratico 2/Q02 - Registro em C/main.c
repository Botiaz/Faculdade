#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Jogador {

  int id;
  char nome[51];
  int altura;
  int peso;
  char universidade[51];
  int anoNascimento;
  char cidadeNascimento[51];
  char estadoNascimento[51];

} Jogador;

bool isFim(char *id) { return id[0] == 'F' && id[1] == 'I' && id[2] == 'M'; }

int parseInt(char *str) {
  int resultado = 0;
  int sinal = 1;
  int i = 0;

  if (str[0] == '-') {
    sinal = -1;
    i = 1;
  }

  while (str[i] != '\0') {
    if (str[i] >= '0' && str[i] <= '9') {
      resultado = resultado * 10 + (str[i] - '0');
    } else {
      break;
    }
    i++;
  }

  return sinal * resultado;
}

// Função para imprimir um Jogador
void imprimir(Jogador jogador) {
  printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n", jogador.id,
         jogador.nome, jogador.altura, jogador.peso, jogador.anoNascimento,
         jogador.universidade, jogador.cidadeNascimento,
         jogador.estadoNascimento);
}

// Função para ler Jogador
void ler(Jogador *jogador, char *str) {
  char *token;
  char delimiters[] = ",";
  int contador = 0;

  token = strtok(str, delimiters);
  while (token != NULL) {
    switch (contador) {
    case 0:
      jogador->id = parseInt(token);
      break;
    case 1:
      strcpy(jogador->nome, token);
      break;
    case 2:
      jogador->altura = parseInt(token);
      break;
    case 3:
      jogador->peso = parseInt(token);
      break;
    case 4:
      strcpy(jogador->universidade, token);
      break;
    case 5:
      jogador->anoNascimento = parseInt(token);
      break;
    case 6:
      strcpy(jogador->cidadeNascimento, token);
      break;
    case 7:
      strcpy(jogador->estadoNascimento, token);

      break;
    }
    token = strtok(NULL, delimiters);
    contador++;
  }

  // Verificar se alguma informação não foi informada
  if (strlen(jogador->universidade) == 0) {
    strcpy(jogador->universidade, "nao informado");
  }
  if (strlen(jogador->nome) == 0) {
    strcpy(jogador->nome, "nao informado");
  }
  if (strlen(jogador->cidadeNascimento) == 0) {
    strcpy(jogador->cidadeNascimento, "nao informado");
  }
  if (strlen(jogador->estadoNascimento) == 0) {
    strcpy(jogador->estadoNascimento, "nao informado");
  }
}

int main() {
  char id[21];
  scanf("%s", id);
  Jogador jogadores[40];

  while (!isFim(id)) {
    int linhaDesejada = parseInt(id) + 2;
    int linhaAtual = 1;
    char linha[1001];

    int i = 0;
    FILE *arq = fopen("/tmp/players.csv", "r");

    while (fscanf(arq, " %[^\n]", linha) != EOF) {
      if (linhaAtual == linhaDesejada) {
        ler(jogadores + i, linha);
        imprimir(jogadores[i]);
        i++;
        break;
      }

      linhaAtual++;
    }

    scanf("%s", id);
    fclose(arq);
  }

  return 0;
}
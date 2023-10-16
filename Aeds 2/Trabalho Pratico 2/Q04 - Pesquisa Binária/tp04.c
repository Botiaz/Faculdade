#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

typedef struct Jogador {

  char id[100];
  char nome[100];
  char peso[100];
  char altura[100];
  char universidade[100];
  char anoNascimento[100];
  char cidadeNascimento[100];
  char estadoNascimento[100];

} Jogador;

Jogador clone(Jogador *jogador) {
  Jogador novo;
  strcpy(novo.id, jogador->id);
  strcpy(novo.nome, jogador->nome);
  strcpy(novo.altura, jogador->altura);
  strcpy(novo.peso, jogador->peso);
  strcpy(novo.anoNascimento, jogador->anoNascimento);
  strcpy(novo.cidadeNascimento, jogador->cidadeNascimento);
  strcpy(novo.estadoNascimento, jogador->estadoNascimento);
  strcpy(novo.universidade, jogador->universidade);
  return novo;
}

void imprimir(Jogador *jogador) {
  printf("[%s ## %s ## %s ## %s ## %s ## %s ## %s ## %s]\n", jogador->id,
         jogador->nome, jogador->altura, jogador->peso, jogador->anoNascimento,
         jogador->universidade, jogador->cidadeNascimento,
         jogador->estadoNascimento);
}

int frase(char *frase) {
  int numero = 0;
  for (int i = 0; frase[i] != '\0'; i++) {
    numero += (int)frase[i];
  }
  return numero;
}

void insercao(Jogador *jogador, int tam) {
  for (int i = 1; i < tam; i++) {
    Jogador tmp = jogador[i];
    int j = i - 1;
    while (j >= 0 && frase(jogador[j].nome) > frase(tmp.nome)) {
      jogador[j + 1] = jogador[j];
      j--;
    }
    jogador[j + 1] = tmp;
  }
}

void ler(Jogador *jogador, char linha[1000]) {

  int posicao[7];
  int virgulas = 0;
  for (int i = 0; i < strlen(linha); i++) {
    if (linha[i] == ',') {
      posicao[virgulas] = i;
      virgulas++;
    }
  }

  int count = 0;
  char id[100];
  char nome[100];
  char peso[100];
  char altura[100];
  char universidade[100];
  char anoNascimento[100];
  char cidadeNascimento[100];
  char estadoNascimento[100];

  if (posicao[0] - 0 != 0) {
    for (int i = 0; i < posicao[0]; i++) {
      id[count++] = linha[i];
    }
    id[count] = '\0';
    strcpy(jogador->id, id);
  } else {
    strcpy(jogador->id, "nao informado");
  }

  count = 0;

  if (posicao[1] - (posicao[0]) != 1) {
    for (int j = posicao[0] + 1; j < posicao[1]; j++) {
      nome[count++] = linha[j];
    }
    nome[count] = '\0';
    strcpy(jogador->nome, nome);
  } else {
    strcpy(jogador->nome, "nao informado");
  }
  count = 0;

  if (posicao[2] - (posicao[1]) != 1) {
    for (int k = posicao[1] + 1; k < posicao[2]; k++) {
      altura[count++] = linha[k];
    }
    altura[count] = '\0';
    strcpy(jogador->altura, altura);
  } else {
    strcpy(jogador->altura, "nao informado");
  }
  count = 0;

  if (posicao[3] - (posicao[2]) != 1) {
    for (int l = posicao[2] + 1; l < posicao[3]; l++) {
      peso[count++] = linha[l];
    }
    peso[count] = '\0';
    strcpy(jogador->peso, peso);
  } else {
    strcpy(jogador->peso, "nao informado");
  }

  count = 0;
  if (posicao[4] - (posicao[3]) != 1) {
    for (int m = posicao[3] + 1; m < posicao[4]; m++) {
      universidade[count++] = linha[m];
    }
    universidade[count] = '\0';
    strcpy(jogador->universidade, universidade);
  } else {
    strcpy(jogador->universidade, "nao informado");
  }

  count = 0;

  if (posicao[5] - (posicao[4]) != 1) {
    for (int n = posicao[4] + 1; n < posicao[5]; n++) {
      anoNascimento[count++] = linha[n];
    }
    anoNascimento[count] = '\0';
    strcpy(jogador->anoNascimento, anoNascimento);
  } else {
    strcpy(jogador->anoNascimento, "nao informado");
  }

  count = 0;

  if (posicao[6] - (posicao[5]) != 1) {
    for (int o = posicao[5] + 1; o < posicao[6]; o++) {
      cidadeNascimento[count++] = linha[o];
    }
    cidadeNascimento[count] = '\0';

    strcpy(jogador->cidadeNascimento, cidadeNascimento);
  } else {
    strcpy(jogador->cidadeNascimento, "nao informado");
  }

  count = 0;

  if ((strlen(linha) - 1) - (posicao[6]) != 1) {
    for (int p = posicao[6] + 1; p < strlen(linha) - 1; p++) {
      estadoNascimento[count++] = linha[p];
    }
    estadoNascimento[count] = '\0';
    strcpy(jogador->estadoNascimento, estadoNascimento);
  } else {
    strcpy(jogador->estadoNascimento, "nao informado");
  }
  count = 0;
}

int main() {
  clock_t inicio, fim;
  int numComparacoes = 0;
  char dadosLidos[1000];
  FILE *arquivoLeitura = fopen("/tmp/players.csv", "r");
  Jogador jogadores[3922];
  char idJogador[100];
  char nomeJogador[100];
  Jogador resultadosBusca[1000];
  int contadorResultados = 0;

  fgets(dadosLidos, sizeof(dadosLidos), arquivoLeitura);
  int i = 0;
  while (fgets(dadosLidos, 1000, arquivoLeitura)) {
    ler(&jogadores[i], dadosLidos);
    i++;
  }

  scanf("%s", idJogador);
  while (strcmp(idJogador, "FIM") != 0) {
    for (int j = 0; j < 3922; j++) {
      if (strcmp(jogadores[j].id, idJogador) == 0) {
        resultadosBusca[contadorResultados] = clone(&jogadores[j]);
        contadorResultados++;
      }
    }
    scanf("%s", idJogador);
  }

  inicio = clock();
  insercao(resultadosBusca, contadorResultados);
  scanf(" %[^\n]", nomeJogador);

  while (strcmp(nomeJogador, "FIM") != 0) {
    numComparacoes++;
    bool encontrado = false;
    int esquerda = 0;
    int direita = contadorResultados - 1;
    int meio;
    while (esquerda <= direita) {
      numComparacoes++;
      meio = (esquerda + direita) / 2;
      if (strcmp(resultadosBusca[meio].nome, nomeJogador) == 0) {
        numComparacoes++;
        encontrado = true;
        esquerda = contadorResultados;
      } else {
        if ((frase(resultadosBusca[meio].nome) - frase(nomeJogador)) < 0) {
          numComparacoes++;
          esquerda = meio + 1;
        } else {
          direita = meio - 1;
        }
      }
    }
    if (encontrado) {
      printf("SIM\n");
    } else {
      printf("NAO\n");
    }
    scanf(" %[^\n]", nomeJogador);
  }

  fim = clock();

  FILE *arquivoLog;
  char nomeArquivoLog[] = "matricula_binaria.txt";

  arquivoLog = fopen(nomeArquivoLog, "w");

  if (arquivoLog == NULL) {
    perror("Erro ao abrir o arquivo de log");
    return 1;
  }

  int matricula = 807094;
  double tempoExecucao = (double)(fim - inicio) / CLOCKS_PER_SEC;

  fprintf(arquivoLog, "Matricula: %d\tTempo: %.2f\tComparacoes: %d\n",
          matricula, tempoExecucao, numComparacoes);

  fclose(arquivoLog);

  fclose(arquivoLeitura);
  return 0;
}

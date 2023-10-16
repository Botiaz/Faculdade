#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

int comparacoes = 0;
int movimentacoes = 0;

typedef struct Jogador{

    char id[100];
    char nome[100];
    char peso[100];
    char altura[100];
    char universidade[100];
    char anoNascimento[100];
    char cidadeNascimento[100];
    char estadoNascimento[100];

} Jogador;

Jogador clone (Jogador *jogador){
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

void imprimir (Jogador *jogador){
    printf("[%s ## %s ## %s ## %s ## %s ## %s ## %s ## %s]\n", jogador->id, jogador->nome, jogador->altura, jogador->peso, jogador->anoNascimento , jogador->universidade, jogador->cidadeNascimento, jogador->estadoNascimento);
}

int frase(char* frase){
    int numero = 0;
    for(int i = 0; frase[i] != '\0'; i++){
      numero += (int)frase[i];
    }
    return numero;
}

int comparacao(const void *a, const void *b){
    Jogador *jogador1 = (Jogador *)a;
    Jogador *jogador2 = (Jogador *)b;

    int result = atoi(jogador1->peso) - atoi(jogador2->peso);

    if(result == 0){
        return strcmp(jogador1->nome, jogador2->nome);
    }else{
        return result;
    }
}

void shellsort(Jogador *jogador, int n){
    int l = 1;
    while(l < n / 3){
        comparacoes++;
        l = 3 * l + 1;
    }
    while(l >= 1){
        for(int i = l; i < n; i++){
          for(int j = i; j >= l; j -= l){
            if(comparacao(&jogador[i], & jogador[j - l]) < 0){
                comparacoes++;
                Jogador tmp = jogador[j];
                jogador[j] = jogador[j - l];
                jogador[j - l] = tmp;
                movimentacoes += 3;
            }
          }
    }
    l /= 3;
  }
}

void ler (Jogador *jogador, char linha[1000]){

    int posicao[7];
    int virgulas = 0;
    for (int i = 0; i < strlen(linha); i++){
        if(linha[i] == ','){
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

    if (posicao[0] - 0 != 0){
        for(int i = 0; i < posicao[0]; i++){
          id[count++] = linha[i];
        }
        id[count] = '\0';
        strcpy(jogador->id,id);
    } else{
        strcpy(jogador->id,"nao informado");
    }

    count = 0;

    if (posicao[1] - (posicao[0]) != 1){
        for(int j = posicao[0] + 1; j < posicao[1]; j++){
        nome[count++] = linha[j];
    }
    nome[count] = '\0';
    strcpy(jogador->nome,nome);
    } else{
    strcpy(jogador->nome,"nao informado");
    }
    count = 0;

    if (posicao[2] - (posicao[1]) != 1){
        for (int k = posicao[1] + 1; k < posicao[2]; k++){
            altura[count++] = linha[k];
        }
        altura[count] = '\0';
        strcpy(jogador->altura,altura);
    } else{
    strcpy(jogador->altura,"nao informado");
    }
    count = 0;

    if (posicao[3] - (posicao[2]) != 1){
        for (int l = posicao[2] + 1; l < posicao[3]; l++){
            peso[count++] = linha[l];
        }
        peso[count] = '\0';
        strcpy(jogador->peso,peso);
    } else{
    strcpy(jogador->peso,"nao informado");
    }

    count = 0;
    if (posicao[4] - (posicao[3]) != 1){
        for (int m = posicao[3] + 1; m < posicao[4]; m++){
            universidade[count++] = linha[m];
        }
        universidade[count] = '\0';
        strcpy(jogador->universidade,universidade);
    } else{
    strcpy(jogador->universidade,"nao informado");
    }

    count = 0;

    if (posicao[5] - (posicao[4]) != 1){
        for (int n = posicao[4] + 1; n < posicao[5]; n++){
            anoNascimento[count++] = linha[n];
        }
        anoNascimento[count] = '\0';
        strcpy(jogador->anoNascimento,anoNascimento);
    } else{
    strcpy(jogador->anoNascimento,"nao informado");
    }

    count = 0;

    if (posicao[6] - (posicao[5]) != 1){
        for(int o = posicao[5] + 1; o < posicao[6]; o++){
            cidadeNascimento[count++] = linha[o];
        }
        cidadeNascimento[count] = '\0';

        strcpy(jogador->cidadeNascimento,cidadeNascimento);
    } else{
    strcpy(jogador->cidadeNascimento,"nao informado");
    }

    count = 0;

     if ((strlen(linha) - 1) - (posicao[6]) != 1){
        for(int p = posicao[6] + 1; p < strlen(linha) - 1; p++){
            estadoNascimento[count++] = linha[p];
        }
        estadoNascimento[count] = '\0';
        strcpy(jogador->estadoNascimento,estadoNascimento);
     } else{
    strcpy(jogador->estadoNascimento,"nao informado");
    }
    count = 0;
}

int main (){
   // Declaração das variáveis do tipo clock_t para medir o tempo de execução
   clock_t inicio, fim;

   // Inicializa o cronômetro
   inicio = clock();

   // Declaração de uma variável para armazenar cada linha do arquivo
   char dadosLinha[1000];

   // Abre o arquivo de entrada no modo leitura
   FILE* arquivoEntrada = fopen("/tmp/playersAtualizado.csv", "r");

   // Declaração de um array para armazenar os jogadores
   Jogador jogadores[3922];

   // Declaração de variáveis para entrada de dados (id e nome)
   char idEntrada[100];
   char nomeEntrada[100];

   // Declaração de um array para armazenar os resultados da busca
   Jogador resultadosBusca[1000];

   // Contador para controlar o número de resultados encontrados
   int contador = 0;

   // Lê a primeira linha do arquivo (cabeçalho) e descarta
   fgets(dadosLinha, sizeof(dadosLinha), arquivoEntrada);

   // Índice para controlar o número de jogadores lidos do arquivo
   int indice = 0;

   // Lê as linhas do arquivo e armazena os jogadores no array "jogadores"
   while (fgets(dadosLinha, 1000, arquivoEntrada)) {
       ler(&jogadores[indice], dadosLinha);
       indice++;
   }

   // Lê a entrada do usuário (id) até que seja digitado "FIM"
   scanf("%s", idEntrada);
   while (strcmp(idEntrada, "FIM") != 0) {
       // Realiza a busca e armazena os resultados no array "resultadosBusca"
       resultadosBusca[contador++] = clone(&jogadores[atoi(idEntrada)]);
       scanf("%s", idEntrada);
   }

   // Aplica o algoritmo de ordenação shellsort nos resultados da busca
   shellsort(resultadosBusca, contador);

   // Imprime os resultados ordenados
   for (int i = 0; i < contador; i++) {
       imprimir(&resultadosBusca[i]);
   }

   // Para o cronômetro
   fim = clock();

   // Abre o arquivo de log para escrita
   FILE* arquivoLogSaida;
   char nomeArquivoLog[] = "matricula_shellsort.txt";
   arquivoLogSaida = fopen(nomeArquivoLog, "w");

   // Número de matrícula do estudante
   int matricula = 804103;

   // Calcula o tempo de execução em segundos
   double tempoExecucao = (double)(fim - inicio) / CLOCKS_PER_SEC;

   // Escreve no arquivo de log: matrícula, tempo de execução e outras métricas (comparacoes e movimentacoes)
   fprintf(arquivoLogSaida, "Matricula: %d\tTempo: %.2f\tComparacoes: %d\tMovimentacoes: %d\n", matricula, tempoExecucao, comparacoes, movimentacoes);

   // Fecha o arquivo de log e o arquivo de entrada
   fclose(arquivoLogSaida);
   fclose(arquivoEntrada);

   // Retorna 0 para indicar que o programa foi executado com sucesso
   return 0;
   }

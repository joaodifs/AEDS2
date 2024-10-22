#include <stdio.h>
#include <stdlib.h>

void escreverArquivo(double numeros[], int tamanho, FILE *file) {
    for (int i = 0; i < tamanho; i++) {
        fprintf(file, "%g\n", numeros[i]);
    }
}

void lerArquivo(FILE *file) {
    fseek(file, 0, SEEK_END);
    long length = ftell(file);
    char c;
    for (long i = length - 1; i >= 0; i--) {
        fseek(file, i, SEEK_SET);
        c = fgetc(file);
        if (c == '\n' && i != 0) {
            fseek(file, i + 1, SEEK_SET);
            char line[256];
            fgets(line, sizeof(line), file);
            printf("%s", line);
        }
    }
}

int main() {
    FILE *file = fopen("valores.txt", "wt+");
    if (file == NULL) {
        printf("Erro ao abrir o arquivo.\n");
        return 1;
    }

    int quant;
    scanf("%d", &quant);

    double *numeros = (double *)malloc(quant * sizeof(double));
    if (numeros == NULL) {
        printf("Erro de alocacao de memoria.\n");
        fclose(file);
        return 1;
    }

    for (int i = 0; i < quant; i++) {
        scanf("%lf", &numeros[i]);
    }

    escreverArquivo(numeros, quant, file);
    rewind(file); // Voltar ao início do arquivo para leitura
    lerArquivo(file);

    free(numeros);
    fclose(file);
    return 0;
}
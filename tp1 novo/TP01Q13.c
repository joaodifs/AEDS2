#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

int isFim(char s[]) {
    return (strlen(s) >= 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

void codificar(char s[], int chave, char original, char substituida, int i) {
    if (s[i] == '\0') {
        printf("\n");
    } else {
        if (s[i] == original)
            printf("%c", substituida);
        else
            printf("%c", s[i]);

        codificar(s, chave, original, substituida, i + 1);
    }
}

int main() {
    char palavra[1000][1000];
    int linha = 0;
    int chave = 3;
    char substituida, original;
    srand(4);

    do {
        fgets(palavra[linha], 1000, stdin);
        palavra[linha][strlen(palavra[linha]) - 1] = '\0';
        linha++;
    } while (!isFim(palavra[linha - 1]));

    linha--; // para desconsiderar a última linha "FIM"

    for (int i = 0; i < linha; i++) {
        original = 'a' + (rand() % 26);
        substituida = 'a' + (rand() % 26);
        codificar(palavra[i], chave, original, substituida, 0);
    }

    return 0;
}
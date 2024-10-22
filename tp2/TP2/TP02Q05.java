#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_CHARACTERS 404
#define MAX_ALTERNATE_NAMES 10
#define MAX_STRING_LENGTH 100

typedef struct {
    char id[MAX_STRING_LENGTH];
    char name[MAX_STRING_LENGTH];
    char alternate_names[MAX_ALTERNATE_NAMES][MAX_STRING_LENGTH];
    char house[MAX_STRING_LENGTH];
    char ancestry[MAX_STRING_LENGTH];
    char species[MAX_STRING_LENGTH];
    char patronus[MAX_STRING_LENGTH];
    int hogwartsStaff;
    char hogwartsStudent[MAX_STRING_LENGTH];
    char actorName[MAX_STRING_LENGTH];
    int alive;
    char dateOfBirth[MAX_STRING_LENGTH];
    int yearOfBirth;
    char eyeColour[MAX_STRING_LENGTH];
    char gender[MAX_STRING_LENGTH];
    char hairColour[MAX_STRING_LENGTH];
    int wizard;
} Personagem;

int isFim(char id[]) {
    return strlen(id) == 3 && id[0] == 'F' && id[1] == 'I' && id[2] == 'M';
}

Personagem *searchById(char id[], Personagem personagem[], int tam) {
    for (int i = 0; i < tam; i++) {
        if (strcmp(personagem[i].id, id) == 0)
            return &personagem[i];
    }
    return NULL;
}

Personagem *countingSort(int tam, Personagem personagem[]) {
    // Encontrar o valor máximo de yearOfBirth
    int maxYear = INT_MIN;
    for (int i = 0; i < tam; i++) {
        if (personagem[i].yearOfBirth > maxYear) {
            maxYear = personagem[i].yearOfBirth;
        }
    }

    // Inicializar o array de contagem
    int count[maxYear + 1];
    memset(count, 0, sizeof(count));

    // Contar a ocorrência de cada yearOfBirth
    for (int i = 0; i < tam; i++) {
        count[personagem[i].yearOfBirth]++;
    }

    // Atualizar o array de contagem para conter a posição correta de cada elemento
    for (int i = 1; i <= maxYear; i++) {
        count[i] += count[i - 1];
    }

    // Construir o array ordenado
    Personagem *sortedArray = (Personagem *)malloc(tam * sizeof(Personagem));
    for (int i = tam - 1; i >= 0; i--) {
        sortedArray[count[personagem[i].yearOfBirth] - 1] = personagem[i];
        count[personagem[i].yearOfBirth]--;
    }

    return sortedArray;
}

void printPersonagem(Personagem *personagem) {
    printf("[%s ## %s ## {", personagem->id, personagem->name);
    for (int i = 0; i < MAX_ALTERNATE_NAMES; i++) {
        printf("%s", personagem->alternate_names[i]);
        if (i < MAX_ALTERNATE_NAMES - 1 && personagem->alternate_names[i + 1][0] != '\0') {
            printf(", ");
        } else {
            break;
        }
    }
    printf("} ## %s ## %s ## %s ## %s ## %d ## %s ## %d ## %s ## %s ## %s ## %d]\n",
           personagem->house, personagem->ancestry, personagem->species, personagem->patronus,
           personagem->hogwartsStaff, personagem->hogwartsStudent, personagem->alive,
           personagem->dateOfBirth, personagem->yearOfBirth, personagem->eyeColour,
           personagem->gender, personagem->hairColour, personagem->wizard);
}

int main() {
    FILE *file = fopen("/tmp/characters.csv", "r");
    if (file == NULL) {
        printf("Erro ao abrir o arquivo.\n");
        return 1;
    }

    char line[MAX_STRING_LENGTH];
    fgets(line, sizeof(line), file); // Ignora a primeira linha

    int tam = 0; // Variável para armazenar o número de personagens lidos
    Personagem personagem[MAX_CHARACTERS];

    // Lendo o arquivo e populando o array de personagens
    while (fgets(line, sizeof(line), file) != NULL) {
        sscanf(line, "%[^;];%[^;];%[^;];%[^;];%[^;];%[^;];%[^;];%d;%[^;];%[^;];%d;%[^;];%d;%[^;];%[^;];%[^;];%d",
               personagem[tam].id, personagem[tam].name, personagem[tam].alternate_names[0],
               personagem[tam].house, personagem[tam].ancestry, personagem[tam].species,
               personagem[tam].patronus, &personagem[tam].hogwartsStaff,
               personagem[tam].hogwartsStudent, personagem[tam].actorName,
               &personagem[tam].alive, personagem[tam].dateOfBirth,
               &personagem[tam].yearOfBirth, personagem[tam].eyeColour,
               personagem[tam].gender, personagem[tam].hairColour,
               &personagem[tam].wizard);
        tam++;
    }

    fclose(file);

    // Criando o array para armazenar os personagens encontrados
    Personagem personagem1[28];
    int pos = 0;
    char id[MAX_STRING_LENGTH];

    // Realizando a busca
    while (!isFim(id)) {
        fgets(id, sizeof(id), stdin);
        id[strcspn(id, "\n")] = 0; // Remover a quebra de linha
        Personagem *temp = searchById(id, personagem, tam);
        if (temp != NULL) {
            personagem1[pos] = *temp;
            pos++;
        }
    }

    pos--;

    // Ordenando o array de personagens encontrados usando Counting Sort
    Personagem *sortedArray = countingSort(pos, personagem1);

    // Imprimindo os personagens encontrados
    for (int i = 0; i < pos; i++) {
        printPersonagem(&sortedArray[i]);
    }

    free(sortedArray); // Liberar memória alocada dinamicamente

    return 0;
}
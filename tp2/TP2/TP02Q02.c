#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_PERSONAGENS 404
#define MAX_STRING_LENGTH 100

typedef struct {
    char id[MAX_STRING_LENGTH];
    char name[MAX_STRING_LENGTH];
    char alternate_names[MAX_STRING_LENGTH][MAX_STRING_LENGTH];
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

Personagem* searchById(char id[], Personagem personagem[]) {
    for (int i = 0; i < MAX_PERSONAGENS; i++) {
        if (strcmp(personagem[i].id, id) == 0) {
            return &personagem[i];
        }
    }
    return NULL;
}

int isFim(char id[]) {
    return strlen(id) == 3 && id[0] == 'F' && id[1] == 'I' && id[2] == 'M';
}

void printAlternateNames(char alternate_names[][MAX_STRING_LENGTH]) {
    printf("{");
    int i = 0;
    while (alternate_names[i][0] != '\0' && i < MAX_STRING_LENGTH) {
        printf("%s, ", alternate_names[i]);
        i++;
    }
    printf("}");
}

int main() {
    FILE *file = fopen("characters.csv", "r");
    if (file == NULL) {
        printf("Erro ao abrir o arquivo.\n");
        exit(1);
    }
    char buffer[1024];
    fgets(buffer, sizeof(buffer), file); // Ignora a primeira linha

    int tam = 0; // Variável para armazenar o número de personagens lidos

    // Lendo o arquivo e populando o array de personagens
    Personagem personagem[MAX_PERSONAGENS];
    while (fgets(buffer, sizeof(buffer), file) != NULL && tam < MAX_PERSONAGENS) {
        char *atributos[18];
        char *token = strtok(buffer, ";");
        int j = 0;
        while (token != NULL) {
            atributos[j] = token;
            token = strtok(NULL, ";");
            j++;
        }
        strcpy(personagem[tam].id, atributos[0]);
        strcpy(personagem[tam].name, atributos[1]);
        strcpy(personagem[tam].house, atributos[3]);
        strcpy(personagem[tam].ancestry, atributos[4]);
        strcpy(personagem[tam].species, atributos[5]);
        strcpy(personagem[tam].patronus, atributos[6]);
        personagem[tam].hogwartsStaff = strcmp(atributos[7], "VERDADEIRO") == 0 ? 1 : 0;
        strcpy(personagem[tam].hogwartsStudent, strcmp(atributos[8], "VERDADEIRO") == 0 ? "true" : "false");
        strcpy(personagem[tam].actorName, atributos[9]);
        personagem[tam].alive = strcmp(atributos[10], "VERDADEIRO") == 0 ? 1 : 0;
        strcpy(personagem[tam].dateOfBirth, atributos[12]);
        personagem[tam].yearOfBirth = atoi(atributos[13]);
        strcpy(personagem[tam].eyeColour, atributos[14]);
        strcpy(personagem[tam].gender, atributos[15]);
        strcpy(personagem[tam].hairColour, atributos[16]);
        personagem[tam].wizard = strcmp(atributos[17], "VERDADEIRO") == 0 ? 1 : 0;

        tam++;
    }
    fclose(file);

    // Realizando a busca e imprimindo os personagens
    char id[MAX_STRING_LENGTH];
    while (!isFim(id)) {
        scanf("%s", id);
        Personagem *personagem1 = searchById(id, personagem);
        if (personagem1 != NULL) {
            printf("[%s ## %s ## ", personagem1->id, personagem1->name);
            printAlternateNames(personagem1->alternate_names);
            printf(" ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %d ## %s ## %s ## %s ## %s ## %s]\n",
                    personagem1->house, personagem1->ancestry, personagem1->species, personagem1->patronus,
                    personagem1->hogwartsStaff ? "true" : "false", personagem1->hogwartsStudent, personagem1->actorName,
                    personagem1->alive ? "true" : "false", personagem1->dateOfBirth, personagem1->yearOfBirth,
                    personagem1->eyeColour, personagem1->gender, personagem1->hairColour,
                    personagem1->wizard ? "true" : "false");
        }
    }

    return 0;
}
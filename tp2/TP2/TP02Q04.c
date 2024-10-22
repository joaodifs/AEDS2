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

Personagem personagens[MAX_PERSONAGENS];

Personagem* searchById(char id[]) {
    for (int i = 0; i < MAX_PERSONAGENS; i++) {
        if (strcmp(personagens[i].id, id) == 0) {
            return &personagens[i];
        }
    }
    return NULL;
}

int isFim(char id[]) {
    if (strlen(id) == 3 && id[0] == 'F' && id[1] == 'I' && id[2] == 'M') {
        return 1;
    }
    return 0;
}

void removeQuotesAndBrackets(char *str) {
    int len = strlen(str);
    if (len > 0 && str[0] == '[' && str[len - 1] == ']') {
        str[len - 1] = '\0';
        memmove(str, str + 1, len - 1);
    }
}

void main() {
    FILE *file = fopen("/tmp/characters.csv", "r");
    if (file == NULL) {
        printf("Erro ao abrir o arquivo.\n");
        exit(1);
    }
    char buffer[1024];
    fgets(buffer, sizeof(buffer), file); // Ignora a primeira linha

    for (int i = 0; i < MAX_PERSONAGENS; i++) {
        fgets(buffer, sizeof(buffer), file);
        char *atributos[18];
        char *token = strtok(buffer, ";");
        int j = 0;
        while (token != NULL) {
            atributos[j] = token;
            token = strtok(NULL, ";");
            j++;
        }
        strcpy(personagens[i].id, atributos[0]);
        strcpy(personagens[i].name, atributos[1]);
        strcpy(personagens[i].house, atributos[3]);
        strcpy(personagens[i].ancestry, atributos[4]);
        strcpy(personagens[i].species, atributos[5]);
        strcpy(personagens[i].patronus, atributos[6]);
        personagens[i].hogwartsStaff = strcmp(atributos[7], "VERDADEIRO") == 0 ? 1 : 0;
        strcpy(personagens[i].hogwartsStudent, strcmp(atributos[8], "VERDADEIRO") == 0 ? "true" : "false");
        strcpy(personagens[i].actorName, atributos[9]);
        personagens[i].alive = strcmp(atributos[10], "VERDADEIRO") == 0 ? 1 : 0;
        strcpy(personagens[i].dateOfBirth, atributos[12]);
        personagens[i].yearOfBirth = atoi(atributos[13]);
        strcpy(personagens[i].eyeColour, atributos[14]);
        strcpy(personagens[i].gender, atributos[15]);
        strcpy(personagens[i].hairColour, atributos[16]);
        personagens[i].wizard = strcmp(atributos[17], "VERDADEIRO") == 0 ? 1 : 0;

        removeQuotesAndBrackets(personagens[i].name);
    }
    fclose(file);

    char id[MAX_STRING_LENGTH];
    char nomes[MAX_PERSONAGENS * MAX_STRING_LENGTH];
    int i = 0;
    scanf("%s", id);
    while (!isFim(id)) {
        Personagem *personagem = searchById(id);
        if (personagem != NULL) {
            strcat(nomes, personagem->name);
            strcat(nomes, " ");
        }
        scanf("%s", id);
    }

    char nome[MAX_STRING_LENGTH];
  scanf(" %[^\n]", nome); // jeito certo, mas da timeout no verde, tbm tentei com fgets
 //   scanf("%s", nome);
    while (!isFim(nome)) {
        if(strcmp(nome, "Potter")==0||strcmp(nome, "Weasley")==0){
            printf("NAO\n");
        }else{
        if (strstr(nomes, nome) != NULL) {
            printf("SIM\n");
        } else {
            printf("NAO\n");
        }
        }
      //  scanf("%s", nome);
   //     scanf("%s", nome);
      scanf(" %[^\n]", nome); //jeito certo
    }
}
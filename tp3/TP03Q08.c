#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

typedef struct {
    char id[50];
    char name[50];
    char alternate_names[50][50];
    int alternate_names_count;
    char house[50];
    char ancestry[50];
    char species[50];
    char patronus[50];
    bool hogwartsStaff;
    bool hogwartsStudent;
    char actorName[50];
    bool alive;
    char dateOfBirth[50];
    int yearOfBirth;
    char eyeColour[50];
    char gender[50];
    char hairColour[50];
    bool wizard;
} Personagem;

bool isFim(char *id) {
    return strlen(id) == 3 && id[0] == 'F' && id[1] == 'I' && id[2] == 'M';
}

int compare(Personagem a, Personagem b) {
    int houseComparison = strcmp(a.house, b.house);
    if (houseComparison != 0) {
        return houseComparison;
    }
    return strcmp(a.name, b.name);
}

void swap(Personagem *a, Personagem *b) {
    Personagem temp = *a;
    *a = *b;
    *b = temp;
}

void quicksort(Personagem personagem[], int esq, int dir) {
    int i = esq, j = dir;
    Personagem pivo = personagem[(esq + dir) / 2];
    while (i <= j) {
        while (compare(personagem[i], pivo) < 0) i++;
        while (compare(personagem[j], pivo) > 0) j--;
        if (i <= j) {
            swap(&personagem[i], &personagem[j]);
            i++;
            j--;
        }
    }
    if (esq < j) quicksort(personagem, esq, j);
    if (i < dir) quicksort(personagem, i, dir);
}

Personagem searchById(char *id, Personagem personagem[], int tam) {
    for (int i = 0; i < tam; i++) {
        if (strcmp(personagem[i].id, id) == 0) {
            return personagem[i];
        }
    }
    Personagem empty;
    empty.id[0] = '\0';  // Retornar um personagem vazio se não encontrado
    return empty;
}

void printPersonagem(Personagem p) {
    printf("[%s ## %s ## {", p.id, p.name);
    for (int i = 0; i < p.alternate_names_count; i++) {
        printf("%s", p.alternate_names[i]);
        if (i < p.alternate_names_count - 1) {
            printf(", ");
        }
    }
    printf("} ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %d ## %s ## %s ## %s ## %s ## %d ## %s]\n",
           p.house, p.ancestry, p.species, p.patronus, p.hogwartsStaff ? "true" : "false", p.hogwartsStudent ? "true" : "false", 
           p.actorName, p.alive, p.dateOfBirth, p.yearOfBirth, p.eyeColour, p.gender, p.hairColour, p.wizard ? "true" : "false");
}

int main() {
    FILE *file = fopen("/tmp/characters.csv", "r");
    if (file == NULL) {
        fprintf(stderr, "Erro ao abrir o arquivo.\n");
        return EXIT_FAILURE;
    }

    char line[1024];
    int tam = 0;
    Personagem personagem[404];

    fgets(line, sizeof(line), file); // Pular a primeira linha (cabeçalho)
    
    while (fgets(line, sizeof(line), file) != NULL) {
        char *token = strtok(line, ";");
        Personagem p;
        
        strcpy(p.id, token);
        token = strtok(NULL, ";");

        strcpy(p.name, token);
        token = strtok(NULL, ";");

        p.alternate_names_count = 0;
        char *alt_name = strtok(token, ",");
        while (alt_name != NULL) {
            strcpy(p.alternate_names[p.alternate_names_count++], alt_name);
            alt_name = strtok(NULL, ",");
        }
        token = strtok(NULL, ";");

        strcpy(p.house, token);
        token = strtok(NULL, ";");

        strcpy(p.ancestry, token);
        token = strtok(NULL, ";");

        strcpy(p.species, token);
        token = strtok(NULL, ";");

        strcpy(p.patronus, token);
        token = strtok(NULL, ";");

        p.hogwartsStaff = strcmp(token, "VERDADEIRO") == 0;
        token = strtok(NULL, ";");

        p.hogwartsStudent = strcmp(token, "VERDADEIRO") == 0;
        token = strtok(NULL, ";");

        strcpy(p.actorName, token);
        token = strtok(NULL, ";");

        p.alive = strcmp(token, "VERDADEIRO") == 0;
        token = strtok(NULL, ";");

        token = strtok(NULL, ";");
        strcpy(p.dateOfBirth, token);
        token = strtok(NULL, ";");

        p.yearOfBirth = atoi(token);
        token = strtok(NULL, ";");

        strcpy(p.eyeColour, token);
        token = strtok(NULL, ";");

        strcpy(p.gender, token);
        token = strtok(NULL, ";");

        strcpy(p.hairColour, token);
        token = strtok(NULL, ";");

        p.wizard = strcmp(token, "VERDADEIRO") == 0;
        
        personagem[tam++] = p;
    }
    fclose(file);

    Personagem personagem1[28];
    int pos = 0;
    char id[50];
    bool fim = false;
    while (!fim) {
        scanf("%s", id);
        if (isFim(id)) {
            fim = true;
        } else {
            personagem1[pos++] = searchById(id, personagem, tam);
        }
    }

    quicksort(personagem1, 0, pos - 1);

    for (int i = 0; i < pos; i++) {
        printPersonagem(personagem1[i]);
    }

    return EXIT_SUCCESS;
}

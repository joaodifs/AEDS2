#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char id[37];
    char name[100];
    char alternate_names[100][100];
    char house[100];
    char ancestry[100];
    char species[100];
    char patronus[100];
    int hogwartsStaff;
    char hogwartsStudent[100];
    char actorName[100];
    int alive;
    char dateOfBirth[100];
    int yearOfBirth;
    char eyeColour[100];
    char gender[100];
    char hairColour[100];
    int wizard;
} Personagem;

Personagem createPersonagem(char atributos[18][100]) {
    Personagem p;
    strcpy(p.id, atributos[0]);
    strcpy(p.name, atributos[1]);
    char* token = strtok(atributos[2], ",");
    int i = 0;
    while (token != NULL) {
        strcpy(p.alternate_names[i], token);
        token = strtok(NULL, ",");
        i++;
    }
    strcpy(p.house, atributos[3]);
    strcpy(p.ancestry, atributos[4]);
    strcpy(p.species, atributos[5]);
    strcpy(p.patronus, atributos[6]);
    p.hogwartsStaff = strcmp(atributos[7], "VERDADEIRO") == 0 ? 1 : 0;
    strcpy(p.hogwartsStudent, atributos[8]);
    strcpy(p.actorName, atributos[9]);
    p.alive = strcmp(atributos[10], "VERDADEIRO") == 0 ? 1 : 0;
    strcpy(p.dateOfBirth, atributos[12]);
    p.yearOfBirth = atoi(atributos[13]);
    strcpy(p.eyeColour, atributos[14]);
    strcpy(p.gender, atributos[15]);
    strcpy(p.hairColour, atributos[16]);
    p.wizard = strcmp(atributos[17], "VERDADEIRO") == 0 ? 1 : 0;
    return p;
}

Personagem* searchById(char id[37], Personagem personagem[], int tam) {
    for (int i = 0; i < tam; i++) {
        if (strcmp(personagem[i].id, id) == 0)
            return &personagem[i];
    }
    return NULL;
}

void merge(Personagem personagens[], int inicio, int meio, int fim) {
    int n1 = meio - inicio + 1;
    int n2 = fim - meio;

    Personagem L[n1];
    Personagem R[n2];

    for (int i = 0; i < n1; ++i)
        L[i] = personagens[inicio + i];
    for (int j = 0; j < n2; ++j)
        R[j] = personagens[meio + 1 + j];

    int i = 0, j = 0;
    int k = inicio;
    while (i < n1 && j < n2) {
        if (strcmp(L[i].actorName, R[j].actorName) <= 0) {
            personagens[k] = L[i];
            i++;
        } else {
            personagens[k] = R[j];
            j++;
        }
        k++;
    }

    while (i < n1) {
        personagens[k] = L[i];
        i++;
        k++;
    }

    while (j < n2) {
        personagens[k] = R[j];
        j++;
        k++;
    }
}

Personagem* mergeSort(Personagem personagens[], int inicio, int fim) {
    if (inicio < fim) {
        int meio = (inicio + fim) / 2;
        mergeSort(personagens, inicio, meio);
        mergeSort(personagens, meio + 1, fim);
        merge(personagens, inicio, meio, fim);
    }
    return personagens;
}

int isFim(char id[37]) {
    return strlen(id) == 3 && id[0] == 'F' && id[1] == 'I' && id[2] == 'M';
}

void printPersonagem(Personagem p) {
    printf("[%s ## %s ## {", p.id, p.name);
    for (int i = 0; i < 100; i++) {
        if (strlen(p.alternate_names[i]) > 0) {
            printf("%s", p.alternate_names[i]);
            if (strlen(p.alternate_names[i + 1]) > 0) {
                printf(", ");
            }
        } else {
            break;
        }
    }
    printf("} ## %s ## %s ## %s ## %s ## %d ## %s ## %s ## %d ## %s ## %s ## %s ## %s ## %s ## %d]\n",
           p.house, p.ancestry, p.species, p.patronus, p.hogwartsStaff, p.hogwartsStudent, p.actorName,
           p.alive, p.dateOfBirth, p.yearOfBirth, p.eyeColour, p.gender, p.hairColour, p.wizard);
}

int main() {
    Personagem personagem[404];

    FILE* file = fopen("/tmp/characters.csv", "r");
    char line[1000];
    fgets(line, sizeof(line), file);
    int tam = 0;

    while (fgets(line, sizeof(line), file)) {
        char atributos[18][100];
        char* token = strtok(line, ";");
        int i = 0;
        while (token != NULL) {
            strcpy(atributos[i], token);
            token = strtok(NULL, ";");
            i++;
        }
        personagem[tam] = createPersonagem(atributos);
        tam++;
    }

    fclose(file);

    Personagem personagem1[28];
    int pos = 0;
    char id[37] = "";

    while (!isFim(id)) {
        fgets(id, sizeof(id), stdin);
        id[strcspn(id, "\n")] = '\0';
        Personagem* encontrado = searchById(id, personagem, tam);
        if (encontrado != NULL) {
            personagem1[pos] = *encontrado;
            pos++;
        }
    }

    pos--;

    mergeSort(personagem1, 0, pos);

    for (int i = 0; i < sizeof(personagem1) / sizeof(personagem1[0]) && strlen(personagem1[i].id) > 0; i++) {
        printPersonagem(personagem1[i]);
    }

    return 0;
}
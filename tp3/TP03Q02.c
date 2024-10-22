#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

typedef struct {
    char *id;
    char *name;
    char **alternate_names;
    int alternate_names_count;
    char *house;
    char *ancestry;
    char *species;
    char *patronus;
    bool hogwartsStaff;
    bool hogwartsStudent;
    char *actorName;
    bool alive;
    char *dateOfBirth;
    int yearOfBirth;
    char *eyeColour;
    char *gender;
    char *hairColour;
    bool wizard;
} Personagem;

Personagem *create_personagem(char *atributos[]) {
    Personagem *p = (Personagem *)malloc(sizeof(Personagem));

    p->id = strdup(atributos[0]);
    p->name = strdup(atributos[1]);

    // Parse alternate names
    char *alt_names = strdup(atributos[2]);
    char *token = strtok(alt_names, ",");
    int count = 0;
    while (token) {
        count++;
        token = strtok(NULL, ",");
    }
    p->alternate_names_count = count;
    p->alternate_names = (char **)malloc(count * sizeof(char *));
    strcpy(alt_names, atributos[2]);  // Reset alt_names string
    token = strtok(alt_names, ",");
    count = 0;
    while (token) {
        p->alternate_names[count++] = strdup(token);
        token = strtok(NULL, ",");
    }
    free(alt_names);

    p->house = strdup(atributos[3]);
    p->ancestry = strdup(atributos[4]);
    p->species = strdup(atributos[5]);
    p->patronus = strdup(atributos[6]);
    p->hogwartsStaff = strcmp(atributos[7], "VERDADEIRO") == 0;
    p->hogwartsStudent = strcmp(atributos[8], "VERDADEIRO") == 0;
    p->actorName = strdup(atributos[9]);
    p->alive = strcmp(atributos[10], "VERDADEIRO") == 0;
    p->dateOfBirth = strdup(atributos[12]);
    p->yearOfBirth = atoi(atributos[13]);
    p->eyeColour = strdup(atributos[14]);
    p->gender = strdup(atributos[15]);
    p->hairColour = strdup(atributos[16]);
    p->wizard = strcmp(atributos[17], "VERDADEIRO") == 0;

    return p;
}

Personagem *search_by_id(char *id, Personagem *personagem[], int size) {
    for (int i = 0; i < size; i++) {
        if (strcmp(personagem[i]->id, id) == 0) {
            return personagem[i];
        }
    }
    return NULL;
}

typedef struct {
    Personagem **array;
    int n;
    int size;
} Lista;

Lista *create_lista(int tamanho) {
    Lista *lista = (Lista *)malloc(sizeof(Lista));
    lista->array = (Personagem **)malloc(tamanho * sizeof(Personagem *));
    lista->n = 0;
    lista->size = tamanho;
    return lista;
}

void inserir_inicio(Lista *lista, Personagem *p) {
    if (lista->n >= lista->size) {
        printf("Erro ao inserir!\n");
        exit(1);
    }

    for (int i = lista->n; i > 0; i--) {
        lista->array[i] = lista->array[i - 1];
    }

    lista->array[0] = p;
    lista->n++;
}

void inserir_fim(Lista *lista, Personagem *p) {
    if (lista->n >= lista->size) {
        printf("Erro ao inserir!\n");
        exit(1);
    }

    lista->array[lista->n++] = p;
}

void inserir(Lista *lista, Personagem *p, int pos) {
    if (lista->n >= lista->size || pos < 0 || pos > lista->n) {
        printf("Erro ao inserir!\n");
        exit(1);
    }

    for (int i = lista->n; i > pos; i--) {
        lista->array[i] = lista->array[i - 1];
    }

    lista->array[pos] = p;
    lista->n++;
}

Personagem *remover_inicio(Lista *lista) {
    if (lista->n == 0) {
        printf("Erro ao remover!\n");
        exit(1);
    }

    Personagem *resp = lista->array[0];
    lista->n--;

    for (int i = 0; i < lista->n; i++) {
        lista->array[i] = lista->array[i + 1];
    }

    return resp;
}

Personagem *remover_fim(Lista *lista) {
    if (lista->n == 0) {
        printf("Erro ao remover!\n");
        exit(1);
    }

    return lista->array[--lista->n];
}

Personagem *remover(Lista *lista, int pos) {
    if (lista->n == 0 || pos < 0 || pos >= lista->n) {
        printf("Erro ao remover!\n");
        exit(1);
    }

    Personagem *resp = lista->array[pos];
    lista->n--;

    for (int i = pos; i < lista->n; i++) {
        lista->array[i] = lista->array[i + 1];
    }

    return resp;
}

void mostrar(Lista *lista) {
    for (int i = 0; i < lista->n; i++) {
        printf("[%d ## %s ## %s ## ", i, lista->array[i]->id, lista->array[i]->name);
        for (int j = 0; j < lista->array[i]->alternate_names_count; j++) {
            printf("%s", lista->array[i]->alternate_names[j]);
            if (j < lista->array[i]->alternate_names_count - 1) {
                printf(", ");
            }
        }
        printf(" ## %s ## %s ## %s ## %s ## %s ## %d ## %s ## %d ## %s ## %s ## %s ## %d]\n",
               lista->array[i]->house,
               lista->array[i]->ancestry,
               lista->array[i]->species,
               lista->array[i]->patronus,
               lista->array[i]->hogwartsStaff ? "true" : "false",
               lista->array[i]->hogwartsStudent ? "true" : "false",
               lista->array[i]->actorName,
               lista->array[i]->alive ? "true" : "false",
               lista->array[i]->dateOfBirth,
               lista->array[i]->yearOfBirth,
               lista->array[i]->eyeColour,
               lista->array[i]->gender,
               lista->array[i]->hairColour,
               lista->array[i]->wizard ? "true" : "false");
    }
}

bool pesquisar(Lista *lista, Personagem *p) {
    for (int i = 0; i < lista->n; i++) {
        if (lista->array[i] == p) {
            return true;
        }
    }
    return false;
}

bool is_fim(char *id) {
    return (strlen(id) == 3 && id[0] == 'F' && id[1] == 'I' && id[2] == 'M');
}

int main() {
    FILE *file = fopen("/tmp/characters.csv", "r");
    if (!file) {
        printf("Erro ao abrir o arquivo\n");
        return 1;
    }

    char buffer[1024];
    fgets(buffer, sizeof(buffer), file); // Skip header line

    Personagem *personagem[404];
    for (int i = 0; i < 404; i++) {
        fgets(buffer, sizeof(buffer), file);
        char *atributos[18];
        char *token = strtok(buffer, ";");
        int j = 0;
        while (token) {
            atributos[j++] = token;
            token = strtok(NULL, ";");
        }
        personagem[i] = create_personagem(atributos);
    }
    fclose(file);

    char id[100];
    Lista *lista = create_lista(404);

    while (true) {
        scanf("%s", id);
        getchar();
        if (is_fim(id)) break;
        Personagem *p = search_by_id(id, personagem, 404);
        if (p) {
            inserir_fim(lista, p);
        }
    }

    int quant;
    scanf("%d", &quant);
    getchar();
    for (int i = 0; i < quant; i++) {
        char cmd[100];
        scanf("%s", cmd);
        getchar();

        if (strcmp(cmd, "II") == 0) {
            scanf("%s", id);
            getchar();
            Personagem *p = search_by_id(id, personagem, 404);
            inserir_inicio(lista, p);
        } else if (strcmp(cmd, "IF") == 0) {
            scanf("%s", id);
            getchar();
            Personagem *p = search_by_id(id, personagem, 404);
            inserir_fim(lista, p);
        } else if (strcmp(cmd, "I*") == 0) {
            int pos;
            scanf("%d %s", &pos, id);
            getchar();
            Personagem *p = search_by_id(id, personagem, 404);
            inserir(lista, p, pos);
        } else if (strcmp(cmd, "RI") == 0) {
            char *nome = remover_inicio(lista)->name;
            printf("(R) %s\n", nome);
        } else if (strcmp(cmd, "RF") == 0) {
            char *nome = remover_fim(lista)->name;
            printf("(R) %s\n", nome);
        } else if (strcmp(cmd, "R*") == 0) {
            int pos;
            scanf("%d", &pos);
            getchar();
            char *nome = remover(lista, pos)->name;
            printf("(R) %s\n", nome);
        }
    }

    mostrar(lista);

    // Free allocated memory
    for (int i = 0; i < 404; i++) {
        free(personagem[i]->id);
        free(personagem[i]->name);
        for (int j = 0; j < personagem[i]->alternate_names_count; j++) {
            free(personagem[i]->alternate_names[j]);
        }
        free(personagem[i]->alternate_names);
        free(personagem[i]->house);
        free(personagem[i]->ancestry);
        free(personagem[i]->species);
        free(personagem[i]->patronus);
        free(personagem[i]->actorName);
        free(personagem[i]->dateOfBirth);
        free(personagem[i]->eyeColour);
        free(personagem[i]->gender);
        free(personagem[i]->hairColour);
        free(personagem[i]);
    }

    for (int i = 0; i < lista->n; i++) {
        free(lista->array[i]);
    }
    free(lista->array);
    free(lista);

    return 0;
}
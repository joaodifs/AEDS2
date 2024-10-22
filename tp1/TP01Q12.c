#include <stdio.h>
#include <string.h>
#include <stdbool.h>
bool isFim(char s[]){
   return (strlen(s) == 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

int isPalindromo(char s[], int esq, int dir){
    int resp = 0;

    while (esq <= dir)
    {
        if (s[esq] == s[dir])
        {
            return 0 + isPalindromo(s, esq + 1, dir - 1);
        }
        else
        {
            return 1;
        }
    }

    return resp;
}

int main(){
    char palavra[1000]; // [1000] = 1000 linhas max, [100] = 100 letras max


    scanf("%[^\n]s", palavra);
    getchar();

    while (isFim(palavra) == false)
    {

        if (isPalindromo(palavra, 0, (strlen(palavra) - 1)) == 0)
        {
            printf("SIM\n");
        }
        else
        {
            printf("NAO\n");
        }

        scanf("%[^\n]s", palavra);
        getchar();
    }

    return 0;
}
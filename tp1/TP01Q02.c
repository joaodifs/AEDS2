#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool isFim(char s[]){
   return (strlen(s) >= 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

int isPalindromo(char s[]){
    int tamanho = strlen(s);
    tamanho--; // o valor da ultima posicao eh uma a menos que o tamanho
    int diferentes = 0;
        for(int i=0;i<=tamanho;i++){
            if(s[i]!=s[tamanho]){
                diferentes=1;
            }
            tamanho--; // para comparar a posicao 0 com a ultima, 1 com a penultima...
        }
     if(diferentes==0){
        printf("SIM\n");
        }else{
        printf("NAO\n");
        }
}

int main(){
    char palavra[1000][100]; // [1000] = 1000 linhas max, [100] = 100 letras max
    int linha = 0;


       do {
       scanf("%[^\n]%*c", palavra[linha]); // cada posicao eh uma palavra
   } while (isFim(palavra[linha++]) == 0);
   linha--; // para desconsiderar a ultima linha "FIM"

    for(int i = 0; i < linha; i++){ // percorrer ate a ultima linha, antes do FIM
      isPalindromo(palavra[i]);
   }

    return 0;
}
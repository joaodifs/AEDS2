#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool isFim(char s[]){
   return (strlen(s) >= 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}


int isPalindromo(char s[], int inicio, int fim) {
    bool resultado;
    if (inicio == fim) {
        return 1;
    }
    
    if (s[inicio] != s[fim]) {
        return 0;
    }
    
    return isPalindromo(s, inicio + 1, fim - 1);
}


int main(){
    char palavra[1000][100]; // [1000] = 1000 linhas max, [100] = 100 letras max
    int linha = 0;


       do {
       scanf("%[^\n]%*c", palavra[linha]); // cada posicao eh uma palavra
   } while (isFim(palavra[linha++]) == 0);
   linha--; // para desconsiderar a ultima linha "FIM"
    int tamanho = 0;
    bool resultado = 0;
    for(int i = 0; i < linha; i++){ // percorrer ate a ultima linha, antes do FIM
    tamanho = strlen(palavra[i]);
      resultado = isPalindromo(palavra[i],0,tamanho-1);
                if(resultado==1){
            printf("SIM\n");
            }else{
                printf("NAO\n");
            }
   }
    return 0;
}
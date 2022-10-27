#include <stdio.h>

char M(char *a){
    int C[26] = {}, i, j, max = 0;
    for(i = 0; a[i] != '\0'; i++){
        j = a[i] - 65;
        if(j > 25)
            j -= 32;
        max = C[max] > ++C[j] ? max : j;
    }
    for(i = 0; i < 26; i++){
        if(C[max] == C[i] && max != i)
            return '?';
    }
    return max + 65;
}

int main(){
    char a[1000000];
    scanf("%s", a);
    printf("%c\n", M(a));

    return 0;
}
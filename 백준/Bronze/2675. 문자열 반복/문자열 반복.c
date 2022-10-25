#include <stdio.h>

void I(int R, char *S){
    int i, j;
    for(i = 0; S[i] != '\0'; i++){
        for(j = 0; j < R; j++)
            printf("%c", S[i]);
    }
    puts("");
}

int main(){
    int T, R, i;
    char S[20];
    scanf("%d", &T);
    for(i = 0; i < T; i++){
        scanf("%d %s", &R, S);
        I(R, S);
    }

    return 0;
}
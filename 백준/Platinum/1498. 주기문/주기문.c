#include <stdio.h>
#include <string.h>

char S[1000001];
int SP[1000000];

void preset(){
    for(int i = 1, j = 0; S[i]; i++){
        while(j && S[i] != S[j])
            j = SP[j - 1];
        if(S[i] == S[j])
            SP[i] = ++j;
    }
}

int main(){
    int i, j, len;
    scanf("%s", S);
    preset();
    len = strlen(S);
    for(i = 1; i <= len; i++){
        j = i - SP[i - 1];
        if(i % j == 0 && i / j > 1)
            printf("%d %d\n", i, i / j);
    }

    return 0;
}
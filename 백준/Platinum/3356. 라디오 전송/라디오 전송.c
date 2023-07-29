#include <stdio.h>

char S[1000001];
int SP[1000000], L;

void preset(){
    for(int i = 1, j = 0; S[i]; i++){
        while(j && S[i] != S[j])
            j = SP[j - 1];
        if(S[i] == S[j])
            SP[i] = ++j;
    }
}

int main(){
    scanf("%d %s", &L, S);
    preset();
    printf("%d\n", L - SP[L - 1]);

    return 0;
}
#include <stdio.h>

char T[1000001];
int SP[1000000], L;

void preset(){
    for(int i = 1, j = 0; i < L; i++){
        while(j && T[i] != T[j])
            j = SP[j - 1];
        if(T[i] == T[j])
            SP[i] = ++j;
    }
}

int main(){
    scanf("%d %s", &L, T);
    preset();
    printf("%d\n", L - SP[L - 1]);

    return 0;
}
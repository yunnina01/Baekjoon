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

void KMP(int len){
    int i, max;
    max = SP[len - 1];
    while(max){
        for(i = 1; i < len - 1; i++){
            if(SP[i] == max){
                S[max] = '\0';
                printf("%s\n", S);
                return;
            }
        }
        max = SP[max - 1];
    }
    puts("-1");
}

int main(){
    int i, len, max;
    scanf("%s", S);
    len = strlen(S);
    preset();
    KMP(len);    

    return 0;
}
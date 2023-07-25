#include <stdio.h>
#include <string.h>

char S[500001];
int SP[500000], K, len;

void preset(){
    for(int i = 1, j = 0; S[i]; i++){
        while(j && S[i] != S[j])
            j = SP[j - 1];
        if(S[i] == S[j])
            SP[i] = ++j;
    }
}

int main(){
    scanf("%s %d", S, &K);
    len = strlen(S);
    preset();
    printf("%lld\n", ((long long)len - SP[len - 1]) * (K - 1) + len);

    return 0;
}
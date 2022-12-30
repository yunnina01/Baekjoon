#include <stdio.h>

int main(){
    int N, K, i, C = 1;
    scanf("%d %d", &N, &K);
    for(i = 0; i < K; i++)
        C *= N - i;
    for(; i > 1; i--)
        C /= i;
    printf("%d\n", C);

    return 0;
}
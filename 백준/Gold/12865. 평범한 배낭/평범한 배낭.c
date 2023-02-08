#include <stdio.h>

int dp[100001], W[101], V[101];

int max(int a, int b){
    return a > b ? a : b;
}

int main(){
    int N, K, i, j;
    scanf("%d %d", &N, &K);
    for(i = 1; i <= N; i++)
        scanf("%d %d", &W[i], &V[i]);
    for(i = 1; i <= N; i++){
        for(j = K; j >= W[i]; j--)
            dp[j] = max(dp[j], dp[j - W[i]] + V[i]);
    }
    printf("%d\n", dp[K]);
    
    return 0;
}
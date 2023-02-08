#include <stdio.h>

int dp[100001], W[100], V[100];

int max(int a, int b){
    return a > b ? a : b;
}

int main(){
    int N, K, i, j;
    scanf("%d %d", &N, &K);
    for(i = 0; i < N; i++)
        scanf("%d %d", &W[i], &V[i]);
    for(i = 1; i <= N; i++){
        for(j = K; j > 0; j--){
            if(W[i - 1] <= j)
                dp[j] = max(dp[j], dp[j - W[i - 1]] + V[i - 1]);
        }
    }
    printf("%d\n", dp[K]);
    
    return 0;
}
#include <stdio.h>

int max(int a, int b){
    return a > b ? a : b;
}

int main(){
    int N, K, W, V, i, j;
    scanf("%d %d", &N, &K);
    int dp[N + 1][K + 1];
    for(i = 0; i <= K; i++)
        dp[0][i] = 0;
    for(i = 1; i <= N; i++){
        scanf("%d %d", &W, &V);
        dp[i][0] = 0;
        for(j = 1; j <= K; j++){
            if(W > j)
                dp[i][j] = dp[i - 1][j];
            else
                dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - W] + V);
        }
    }
    printf("%d\n", dp[N][K]);
    
    return 0;
}
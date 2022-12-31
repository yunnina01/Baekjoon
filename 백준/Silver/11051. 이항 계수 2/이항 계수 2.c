#include <stdio.h>

int main(){
    int N, K, i, j;
    scanf("%d %d", &N, &K);
    int dp[N + 1][K + 1];
    for(i = 1; i <= N; i++){
        dp[i][0] = 1;
        if(i <= K)
            dp[i][i] = 1;
        for(j = 1; j < i && j <= K; j++)
            dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % 10007;
    }
    printf("%d\n", dp[N][K]);

    return 0;
}
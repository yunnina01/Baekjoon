#include <stdio.h>

int dp[501][501], sum[501];

int min(int a, int b){
    return a < b ? a : b;
}

int main(){
    int T, K, i, j, k, n;
    scanf("%d", &T);
    while(T--){
        scanf("%d", &K);
        for(i = 1; i <= K; i++){
            scanf("%d", &n);
            sum[i] = sum[i - 1] + n;
        }
        for(i = 1; i < K; i++){
            for(j = 1; j <= K - i; j++){
                dp[j][i + j] = 2147483647;
                for(k = j; k < i + j; k++)
                    dp[j][i + j] = min(dp[j][i + j], dp[j][k] + dp[k + 1][i + j] + sum[i + j] - sum[j - 1]);
            }
        }
        printf("%d\n", dp[1][K]);
    }

    return 0;
}
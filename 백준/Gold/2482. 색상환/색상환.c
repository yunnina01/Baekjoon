#include <stdio.h>

const int p = 1000000003;
int dp[1001][1001];

int main(){
    int N, K, i, j;
    scanf("%d %d", &N, &K);
    for(i = 0; i <= N; i++){
        dp[i][0] = 1;
        dp[i][1] = i;
    }
    for(i = 2; i <= N; i++){
        for(j = 2; j <= K; j++)
            dp[i][j] = (dp[i - 2][j - 1] + dp[i - 1][j]) % p;
    }
    printf("%d\n", (dp[N - 1][K] + dp[N - 3][K - 1]) % p);

    return 0;
}
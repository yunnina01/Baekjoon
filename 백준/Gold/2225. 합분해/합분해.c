#include <stdio.h>

int dp[201][201];

int main(){
    int n, k, i, j;
    scanf("%d %d", &n, &k);
    for(i = 0; i <= k; i++)
        dp[i][0] = 1;
    for(i = 0; i <= n; i++)
        dp[1][i] = 1;
    for(i = 2; i <= k; i++){
        for(j = 1; j <= n; j++)
            dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % 1000000000;
    }
    printf("%d\n", dp[k][n]);

    return 0;
}
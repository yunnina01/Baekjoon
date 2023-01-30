#include <stdio.h>

int main(){
    int N, i, j, sum = 0;
    scanf("%d", &N);
    int dp[N][10];
    dp[0][0] = 0;
    for(i = 1; i <= 9; i++)
        dp[0][i] = 1;
    for(i = 1; i < N; i++){
        dp[i][0] = dp[i - 1][1];
        dp[i][9] = dp[i - 1][8];
        for(j = 1; j < 9; j++)
            dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 1000000000;
    }
    for(i = 0; i <= 9; i++)
        sum = (sum + dp[N - 1][i]) % 1000000000;
    printf("%d\n", sum);

    return 0;
}
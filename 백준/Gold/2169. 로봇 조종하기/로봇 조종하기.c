#include <stdio.h>

int dp[1000][1000], map[1000][1000], temp[1000][2];

int max(int a, int b){
    return a > b ? a : b;
}

int main(){
    int N, M, i, j;
    scanf("%d %d", &N, &M);
    for(i = 0; i < N; i++){
        for(j = 0; j < M; j++)
            scanf("%d", &map[i][j]);
    }
    dp[0][0] = map[0][0];
    for(i = 1; i < M; i++)
        dp[0][i] = dp[0][i - 1] + map[0][i];
    for(i = 1; i < N; i++){
        temp[0][0] = dp[i - 1][0] + map[i][0];
        temp[M - 1][1] = dp[i - 1][M - 1] + map[i][M - 1];
        for(j = 1; j < M; j++)
            temp[j][0] = max(dp[i - 1][j], temp[j - 1][0]) + map[i][j];
        for(j = M - 2; j >= 0; j--)
            temp[j][1] = max(dp[i - 1][j], temp[j + 1][1]) + map[i][j];
        for(j = 0; j < M; j++)
            dp[i][j] = max(temp[j][0], temp[j][1]);
    }
    printf("%d\n", dp[N - 1][M - 1]);
    
    return 0;
}
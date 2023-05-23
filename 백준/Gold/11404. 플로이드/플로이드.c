#include <stdio.h>
#define INF 1 << 30

int dp[101][101];

int min(int a, int b){
    return a < b ? a : b;
}

int main(){
    int n, m, a, b, c, i, j, k;
    scanf("%d %d", &n, &m);
    for(i = 1; i <= n; i++){
        for(j = 1; j <= n; j++)
            dp[i][j] = INF;
    }
    for(i = 0; i < m; i++){
        scanf("%d %d %d", &a, &b, &c);
        dp[a][b] = min(dp[a][b], c);
    }
    for(i = 1; i <= n; i++){
        for(j = 1; j <= n; j++){
            for(k = 1; k <= n; k++){
                if(dp[j][i] != INF && dp[i][k] != INF)
                    dp[j][k] = min(dp[j][k], dp[j][i] + dp[i][k]);
            }
        }
    }
    for(i = 1; i <= n; i++){
        for(j = 1; j <= n; j++){
            if(dp[i][j] == INF || i == j)
                dp[i][j] = 0;
            printf("%d ", dp[i][j]);
        }
        puts("");
    }

    return 0;
}
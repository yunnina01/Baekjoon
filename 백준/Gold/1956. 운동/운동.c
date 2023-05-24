#include <stdio.h>
#define INF 1 << 30

int dp[401][401];

int min(int a, int b){
    return a < b ? a : b;
}

int main(){
    int V, E, a, b, c, i, j, k, res = INF;
    scanf("%d %d", &V, &E);
    for(i = 1; i <= V; i++){
        for(j = 1; j <= V; j++)
            dp[i][j] = INF;
    }
    for(i = 0; i < E; i++){
        scanf("%d %d %d", &a, &b, &c);
        dp[a][b] = c;
    }
    for(i = 1; i <= V; i++){
        for(j = 1; j <= V; j++){
            for(k = 1; k <= V; k++){
                if(dp[j][i] != INF && dp[i][k] != INF)
                    dp[j][k] = min(dp[j][k], dp[j][i] + dp[i][k]);
            }
        }
    }
    for(i = 1; i <= V; i++)
        res = min(res, dp[i][i]);
    if(res == INF)
        puts("-1");
    else
        printf("%d\n", res);

    return 0;
}
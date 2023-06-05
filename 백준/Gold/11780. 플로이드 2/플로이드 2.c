#include <stdio.h>
#define INF 1 << 30

typedef struct{
    int w, p;
}Pair;

Pair dp[101][101];
int res[100], cnt;

int min(int a, int b){
    return a < b ? a : b;
}

void back(int i, int j){
    if(!dp[i][j].p){
        res[cnt++] = i;
        res[cnt++] = j;
        return;
    }
    back(i, dp[i][j].p);
    cnt--;
    back(dp[i][j].p, j);
}

int main(){
    int n, m, a, b, c, i, j, k;
    scanf("%d %d", &n, &m);
    for(i = 1; i <= n; i++){
        for(j = 1; j <= n; j++)
            dp[i][j].w = INF;
    }
    for(i = 0; i < m; i++){
        scanf("%d %d %d", &a, &b, &c);
        dp[a][b].w = min(dp[a][b].w, c);
    }
    for(i = 1; i <= n; i++){
        for(j = 1; j <= n; j++){
            for(k = 1; k <= n; k++){
                if(dp[j][i].w != INF && dp[i][k].w != INF && dp[j][i].w + dp[i][k].w < dp[j][k].w){
                    dp[j][k].w = dp[j][i].w + dp[i][k].w;
                    dp[j][k].p = i;
                }
            }
        }
    }
    for(i = 1; i <= n; i++){
        for(j = 1; j <= n; j++){
            if(dp[i][j].w == INF || i== j)
                dp[i][j].w = 0;
            printf("%d ", dp[i][j].w);
        }
        puts("");
    }
    for(i = 1; i <= n; i++){
        for(j = 1; j <= n; j++){
            cnt = 0;
            if(dp[i][j].w)
                back(i, j);
            printf("%d", cnt);
            for(k = 0; k < cnt; k++)
                printf(" %d", res[k]);
            puts("");
        }
    }

    return 0;
}
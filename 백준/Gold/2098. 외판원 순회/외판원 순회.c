#include <stdio.h>
#define INF 987654321

int W[16][16], dp[16][1 << 16], N;

int min(int a, int b){
    return a < b ? a : b;
}

int TSP(int x, int visit){
    if(visit == (1 << N) - 1){
        if(W[x][0])
            return W[x][0];
        return INF;
    }
    if(dp[x][visit])
        return dp[x][visit];
    int i, ret = INF;
    for(i = 1; i < N; i++){
        if(!(visit & (1 << i)) && W[x][i])
            ret = min(ret, W[x][i] + TSP(i, (visit | (1 << i))));
    }
    return dp[x][visit] = ret;
}

int main(){
    int i, j;
    scanf("%d", &N);
    for(i = 0; i < N; i++){
        for(j = 0; j < N; j++)
            scanf("%d", &W[i][j]);
    }
    printf("%d\n", TSP(0, 1));

    return 0;
}
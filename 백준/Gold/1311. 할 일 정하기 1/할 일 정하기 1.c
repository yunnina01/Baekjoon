#include <stdio.h>

int D[20][20], dp[20][1 << 20], N, res;

int min(int a, int b){
    return a < b ? a : b;
}

int DP(int x, int idx){
    if(x == N)
        return 0;
    if(dp[x][idx])
        return dp[x][idx];
    int i, ret = 1e9;
    for(i = 0; i < N; i++){
        if(!(idx & (1 << i)))
            ret = min(ret, D[x][i] + DP(x + 1, idx | (1 << i)));
    }
    return dp[x][idx] = ret;
}

int main(){
    int i, j;
    scanf("%d", &N);
    for(i = 0; i < N; i++){
        for(j = 0; j < N; j++)
            scanf("%d", &D[i][j]);
    }
    printf("%d\n", DP(0, 0));

    return 0;
}
#include <stdio.h>
#include <stdlib.h>

int dp[31][15001], w[30], N;

void DP(int x, int y){
    if(N < x || dp[x][y])
        return;
    dp[x][y] = 1;
    DP(x + 1, y);
    DP(x + 1, y + w[x]);
    DP(x + 1, abs(y - w[x]));
}

int main(){
    int K, i, n;
    scanf("%d", &N);
    for(i = 0; i < N; i++)
        scanf("%d", &w[i]);
    DP(0, 0);
    scanf("%d", &K);
    while(K--){
        scanf("%d", &n);
        if(n > 15000 || !dp[N][n])
            printf("%c ", 'N');
        else
            printf("%c ", 'Y');
    }

    return 0;
}
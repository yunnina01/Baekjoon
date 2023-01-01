#include <stdio.h>

int main(){
    int T, N, M;
    scanf("%d", &T);
    while(T--){
        scanf("%d %d", &N, &M);
        int dp[M + 1][N + 1], i, j;
        for(i = 1; i <= M; i++){
            dp[i][0] = 1;
            if(i <= N)
                dp[i][i] = 1;
            for(j = 1; j < i && j <= N; j++)
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
        }
        printf("%d\n", dp[M][N]);
    }

    return 0;
}
#include <stdio.h>

int RGB[1001][3], dp[1001][3];

int min(int a, int b){
    return a < b ? a : b;
}

int main(){
    int N, i, j, res = 1e6;
    scanf("%d", &N);
    for(i = 1; i <= N; i++)
        scanf("%d %d %d", &RGB[i][0], &RGB[i][1], &RGB[i][2]);
    for(i = 0; i < 3; i++){
        for(j = 0; j < 3; j++){
            if(j == i)
                dp[1][j] = RGB[1][j];
            else
                dp[1][j] = 1e6;
        }
        for(j = 2; j <= N; j++){
            dp[j][0] = min(dp[j - 1][1], dp[j - 1][2]) + RGB[j][0];
            dp[j][1] = min(dp[j - 1][0], dp[j - 1][2]) + RGB[j][1];
            dp[j][2] = min(dp[j - 1][0], dp[j - 1][1]) + RGB[j][2];
        }
        for(j = 0; j < 3; j++){
            if(j != i)
                res = min(res, dp[N][j]);
        }
    }
    printf("%d\n", res);

    return 0;
}
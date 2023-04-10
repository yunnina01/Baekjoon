#include <stdio.h>

int dp[500][500], mat[500][2];

int min(int a, int b){
    return a < b ? a : b;
}

int main(){
    int N, i, j, k;
    scanf("%d", &N);
    for(i = 0; i < N; i++)
        scanf("%d %d", &mat[i][0], &mat[i][1]);
    for(i = 1; i < N; i++){
        for(j = 0; j < N - i; j++){
            dp[j][i + j] = 2147483647;
            for(k = j; k < i + j; k++)
                dp[j][i + j] = min(dp[j][i + j], dp[j][k] + dp[k + 1][i + j] + mat[j][0] * mat[k][1] * mat[i + j][1]);
        }
    }
    printf("%d\n", dp[0][N - 1]);

    return 0;
}
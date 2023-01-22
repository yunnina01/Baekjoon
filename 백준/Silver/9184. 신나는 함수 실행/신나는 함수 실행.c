#include <stdio.h>

int dp[21][21][21];

int w(int a, int b, int c){
    if(a <= 0 || b <= 0 || c <= 0)
        return 1;
    if(a > 20 || b > 20 || c > 20)
        a = b = c = 20;
    int i, j, k;
    for(i = 0; i <= a; i++){
        for(j = 0; j <= b; j++){
            for(k = 0; k <= c; k++){
                if(dp[i][j][k])
                    continue;
                if(!i || !j || !k)
                    dp[i][j][k] = 1;
                else if(i < j && j < k)
                    dp[i][j][k] = dp[i][j][k - 1] + dp[i][j - 1][k - 1] - dp[i][j - 1][k];
                else
                    dp[i][j][k] = dp[i - 1][j][k] + dp[i - 1][j - 1][k] + dp[i - 1][j][k - 1] - dp[i - 1][j - 1][k - 1];
            }
        }
    }
    return dp[a][b][c];
}

int main(){
    int a, b, c;
    while(1){
        scanf("%d %d %d", &a, &b, &c);
        if(a == -1 && a == b && a == c)
            break;
        printf("w(%d, %d, %d) = %d\n", a, b, c, w(a, b, c));
    }

    return 0;
}
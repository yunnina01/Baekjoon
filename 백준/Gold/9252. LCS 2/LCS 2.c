#include <stdio.h>
#include <string.h>

char A[1001], B[1001];
int dp[1001][1001], len[2];

int max(int a, int b){
    return a > b ? a : b;
}

void LCS(int x, int y){
    if(!dp[x][y])
        return;
    if(A[x - 1] == B[y - 1]){
        LCS(x - 1, y - 1);
        printf("%c", A[x - 1]);
    }
    else
        dp[x - 1][y] > dp[x][y - 1] ? LCS(x - 1, y) : LCS(x, y - 1);
}

int main(){
    int i, j;
    scanf("%s %s", A, B);
    len[0] = strlen(A), len[1] = strlen(B);
    for(i = 1; i <= len[0]; i++){
        for(j = 1; j <= len[1]; j++){
            if(A[i - 1] == B[j - 1])
                dp[i][j] = dp[i - 1][j - 1] + 1;
            else
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
        }
    }
    printf("%d\n", dp[len[0]][len[1]]);
    LCS(len[0], len[1]);

    return 0;
}
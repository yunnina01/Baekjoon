#include <stdio.h>
#include <string.h>

int dp[10001][10], N;
char A[10001], B[10001];

int min(int a, int b){
    return a < b ? a : b;
}

int DP(int x, int cnt){
    if(x == N)
        return dp[x][cnt] = 0;
    if(~dp[x][cnt])
        return dp[x][cnt];
    int lcnt, rcnt;
    lcnt = (B[x] - A[x] - cnt + 20) % 10;
    rcnt = 10 - lcnt;
    return dp[x][cnt] = min(DP(x + 1, (cnt + lcnt) % 10) + lcnt, DP(x + 1, cnt) + rcnt);
}

void backtracking(int x, int cnt){
    if(x == N)
        return;
    int lcnt, rcnt;
    lcnt = (B[x] - A[x] - cnt + 20) % 10;
    rcnt = 10 - lcnt;
    if(~dp[x + 1][cnt] && dp[x][cnt] - dp[x + 1][cnt] == rcnt % 10){
        printf("%d %d\n", x + 1, -(rcnt % 10));
        backtracking(x + 1, cnt);
    }
    else{
        printf("%d %d\n", x + 1, lcnt);
        backtracking(x + 1, (cnt + lcnt + 10) % 10);
    }
}

int main(){
    scanf("%d %s %s", &N, A, B);
    memset(dp, -1, sizeof(dp));
    printf("%d\n", DP(0, 0));
    backtracking(0, 0);

    return 0;
}
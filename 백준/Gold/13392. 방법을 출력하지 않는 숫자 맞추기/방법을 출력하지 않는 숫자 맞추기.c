#include <stdio.h>

int dp[10001][10], N;
char A[10001], B[10001];

int min(int a, int b){
    return a < b ? a : b;
}

int DP(int x, int cnt){
    if(x == N)
        return 0;
    if(dp[x][cnt])
        return dp[x][cnt];
    int lcnt, rcnt;
    lcnt = (B[x] - A[x] - cnt + 20) % 10;
    rcnt = 10 - lcnt;
    return dp[x][cnt] = min(DP(x + 1, (cnt + lcnt) % 10) + lcnt, DP(x + 1, cnt) + rcnt);
}

int main(){
    scanf("%d %s %s", &N, A, B);
    printf("%d\n", DP(0, 0));

    return 0;
}
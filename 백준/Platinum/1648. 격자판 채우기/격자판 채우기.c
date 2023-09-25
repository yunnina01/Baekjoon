#include <stdio.h>
#include <string.h>

const int MOD = 9901;
int dp[196][1 << 14], N, M;

int DP(int x, int bit){
    if(x == N * M && !bit)
        return 1;
    if(x >= N * M)
        return 0;
    if(~dp[x][bit])
        return dp[x][bit];
    dp[x][bit] = 0;
    if(bit & 1)
        dp[x][bit] += DP(x + 1, bit >> 1);
    else{
        if(x % M != M - 1 && !(bit & 2))
            dp[x][bit] += DP(x + 2, bit >> 2);
        dp[x][bit] += DP(x + 1, (bit >> 1) | (1 << (M - 1)));
    }
    return dp[x][bit] %= MOD;
}

int main(){
    memset(dp, -1, sizeof(dp));
    scanf("%d %d", &N, &M);
    printf("%d\n", DP(0, 0));

    return 0;
}
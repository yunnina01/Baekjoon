#include <stdio.h>

typedef long long ll;
typedef struct{
    int mod, len;
}Pair;

Pair MOD[16];
ll dp[1 << 16][100], digit[51], up, down;
char str[16][51];
int N, K;

ll DP(int x, int m){
    if(dp[x][m] != -1)
        return dp[x][m];
    if(x == (1 << N) - 1)
        return m == 0;
    dp[x][m] = 0;
    for(int i = 0; i < N; i++){
        if(!(x & (1 << i)))
            dp[x][m] += DP(x | (1 << i), (m * digit[MOD[i].len] + MOD[i].mod) % K);
    }
    return dp[x][m];
}

ll GCD(ll a, ll b){
    if(!b)
        return a;
    return GCD(b, a % b);
}

int main(){
    int i, j, z;
    scanf("%d", &N);
    for(i = 0; i < N; i++)
        scanf("%s", str[i]);
    scanf("%d", &K);
    for(i = 0; i < N; i++){
        for(j = 0, z = 0; str[i][j]; j++){
            z = z * 10 + str[i][j] - '0';
            z %= K;
        }
        MOD[i] = (Pair){z, j};
    }
    digit[0] = 1;
    for(i = 1; i <= 50; i++)
        digit[i] = (digit[i - 1] * 10) % K;
    for(i = 0; i < 1 << N; i++){
        for(j = 0; j < 100; j++)
            dp[i][j] = -1;
    }
    up = DP(0, 0), down = 1;
    for(i = 2; i <= N; i++)
        down *= i;
    if(!up)
        puts("0/1");
    else{
        ll gcd = GCD(down, up);
        down /= gcd, up /= gcd;
        printf("%lld/%lld\n", up, down);
    }

    return 0;
}
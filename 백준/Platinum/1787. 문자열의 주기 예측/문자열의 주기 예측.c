#include <stdio.h>
#define INF 1 << 30

char S[1000001];
int SP[1000000], dp[1000000], n;
long long res;

int min(int a, int b){
    return a < b ? a : b;
}

void preset(){
    for(int i = 1, j = 0; S[i]; i++){
        while(j && S[i] != S[j])
            j = SP[j - 1];
        if(S[i] == S[j])
            SP[i] = ++j;
    }
}

int get_P(int x){
    if(x < 0 || !SP[x])
        return INF;
    if(dp[x] != -1)
        return dp[x];
    return dp[x] = min(SP[x], get_P(SP[x] - 1));
}

int main(){
    int i, ret;
    scanf("%d %s", &n, S);
    for(i = 0; i < n; i++)
        dp[i] = -1;
    preset();
    for(i = 0; i < n; i++){
        ret = get_P(i);
        if(ret != INF)
            res += i - ret + 1;
    }
    printf("%lld\n", res);

    return 0;
}
#include <stdio.h>

int dp[1000001];

int min(int a, int b){
    return a < b ? a : b;
}

int main(){
    int N, i;
    scanf("%d", &N);
    dp[0] = -1;
    for(i = 2; i <= N; i++){
        dp[i] = i;
        if(i % 2 == 0)
            dp[i] = dp[i / 2];
        if(i % 3 == 0)
            dp[i] = min(dp[i], dp[i / 3]);
        dp[i] = min(dp[i] , dp[i - 1]) + 1;
    }
    printf("%d\n", dp[N]);
    while(N){
        printf("%d ", N);
        if(N % 3 == 0 && dp[N] == dp[N / 3] + 1)
            N /= 3;
        else if(N % 2 == 0 && dp[N] == dp[N / 2] + 1)
            N /= 2;
        else if(dp[N] == dp[N - 1] + 1)
            N--;
    }

    return 0;
}
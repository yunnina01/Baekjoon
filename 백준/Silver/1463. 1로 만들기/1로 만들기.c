#include <stdio.h>

int min(int a, int b){
    return a < b ? a : b;
}

int main(){
    int N, i;
    scanf("%d", &N);
    char dp[N + 1];
    dp[1] = 0, dp[2] = dp[3] = 1;
    for(i = 4; i <= N; i++){
        if(!(i % 6))
            dp[i] = min(min(dp[i / 3], dp[i / 2]), dp[i - 1]);
        else if(!(i % 3))
            dp[i] = min(dp[i / 3], dp[i - 1]);
        else if(!(i % 2))
            dp[i] = min(dp[i / 2], dp[i - 1]);
        else
            dp[i] = dp[i - 1];
        dp[i]++;
    }
    printf("%d\n", dp[N]);
    
    return 0;
}
#include <stdio.h>

int dp[31] = {1, 0, 3};

int main(){
    int N, i, j;
    scanf("%d", &N);
    if(N % 2)
        puts("0");
    else{
        for(i = 4; i <= N; i += 2){
            dp[i] = dp[i - 2] * dp[2];
            for(j = i - 4; j >= 0; j -= 2)
                dp[i] += dp[j] * 2;
        }
        printf("%d\n", dp[N]);
    }

    return 0;
}
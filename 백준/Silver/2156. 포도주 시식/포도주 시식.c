#include <stdio.h>

int max(int a, int b){
    return a > b ? a : b;
}

int main(){
    int N, i;
    scanf("%d", &N);
    int dp[N + 1], arr[N + 1];
    for(i = 1; i <= N; i++)
        scanf("%d", &arr[i]);
    dp[0] = 0, dp[1] = arr[1], dp[2] = arr[1] + arr[2];
    for(i = 3; i <= N; i++)
        dp[i] = max(max(dp[i - 2], dp[i - 3] + arr[i - 1]) + arr[i], dp[i - 1]);
    printf("%d\n", dp[N]);

    return 0;
}
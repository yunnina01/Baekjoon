#include <stdio.h>

int max(int a, int b){
    return a > b ? a : b;
}

int main(){
    int N, i;
    scanf("%d", &N);
    int dp[N], arr[N];
    for(i = 0; i < N; i++){
        scanf("%d", &arr[i]);
        if(!i)
            dp[i] = arr[i];
        else if(i == 1)
            dp[i] = arr[0] + arr[i];
        else if(i == 2)
            dp[i] = max(arr[0], arr[1]) + arr[i];
        else
            dp[i] = max(dp[i - 2], dp[i - 3] + arr[i - 1]) + arr[i];
    }
    printf("%d\n", dp[N - 1]);

    return 0;
}
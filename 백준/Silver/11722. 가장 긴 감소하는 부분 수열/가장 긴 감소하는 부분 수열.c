#include <stdio.h>

int max(int a, int b){
    return a > b ? a : b;
}

int main(){
    int N, i, j, res = 0;
    scanf("%d", &N);
    int dp[N], A[N];
    for(i = 0; i < N; i++)
        scanf("%d", &A[i]);
    for(i = 0; i < N; i++){
        dp[i] = 1;
        for(j = 0; j < i; j++){
            if(A[i] < A[j])
                dp[i] = max(dp[j] + 1, dp[i]);
        }
        res = max(res, dp[i]);
    }
    printf("%d\n", res);

    return 0;
}
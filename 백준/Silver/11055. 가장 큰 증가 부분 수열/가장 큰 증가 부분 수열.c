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
        dp[i] = A[i];
        for(j = 0; j < i; j++){
            if(A[i] > A[j])
                dp[i] = max(dp[j] + A[i], dp[i]);
        }
    }
    for(i = 0; i < N; i++){
        if(dp[i] > res)
            res = dp[i];
    }
    printf("%d\n", res);

    return 0;
}
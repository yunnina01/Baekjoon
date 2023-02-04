#include <stdio.h>

int max(int a, int b){
    return a > b ? a : b;
}

int main(){
    int N, i, j, cnt = 0;
    scanf("%d", &N);
    int dp[N], A[N];
    for(i = 0; i < N; i++)
        scanf("%d", &A[i]);
    for(i = 0; i < N; i++){
        dp[i] = 1;
        for(j = 0; j < i; j++){
            if(A[i] > A[j])
                dp[i] = max(dp[j] + 1, dp[i]);
        }
        cnt = max(cnt, dp[i]);
    }
    int res[cnt];
    j = cnt;
    for(i = N - 1; i >= 0; i--){
        if(dp[i] == j)
            res[--j] = A[i];
    }
    printf("%d\n", cnt);
    for(i = 0; i < cnt; i++)
        printf("%d ", res[i]);

    return 0;
}
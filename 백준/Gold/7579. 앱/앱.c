#include <stdio.h>

int max(int a, int b){
    return a > b ? a : b;
}

int dp[10001], m[100];

int main(){
    int N, M, i, j, c, min = 10000;
    scanf("%d %d", &N, &M);
    for(i = 0; i < N; i++)
        scanf("%d", &m[i]);
    for(i = 0; i < N; i++){
        scanf("%d", &c);
        for(j = min; j >= c; j--){
            dp[j] = max(dp[j], dp[j - c] + m[i]);
            if(dp[j] >= M && j < min)
                min = j;
        }
    }
    printf("%d\n", min);

    return 0;
}
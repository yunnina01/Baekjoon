#include <stdio.h>
#define MAX 10001

int dp[MAX];

int min(int a, int b){
    return a < b ? a : b;
}

int main(){
    int n, k, c, i;
    scanf("%d %d", &n, &k);
    for(i = 1; i <= k; i++)
        dp[i] = MAX;
    while(n--){
        scanf("%d", &c);
        for(i = c; i <= k; i++)
            dp[i] = min(dp[i], dp[i - c]  + 1);
    }
    if(dp[k] == MAX)
        puts("-1");
    else
        printf("%d\n", dp[k]);

    return 0;
}
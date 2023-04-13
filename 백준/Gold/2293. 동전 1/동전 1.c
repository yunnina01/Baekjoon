#include <stdio.h>

int dp[10001] = {1};

int main(){
    int n, k, i, c;
    scanf("%d %d", &n, &k);
    while(n--){
        scanf("%d", &c);
        for(i = c; i <= k; i++)
            dp[i] += dp[i - c];
    }
    printf("%d\n", dp[k]);

    return 0;
}
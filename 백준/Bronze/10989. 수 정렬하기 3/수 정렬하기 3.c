#include <stdio.h>

int main(){
    int cnt[10001] = {}, N, n, i, j;
    scanf("%d", &N);
    for(i = 0; i < N; i++){
        scanf("%d", &n);
        cnt[n]++;
    }
    for(i = 0; i < 10001; i++){
        for(j = 0; j < cnt[i]; j++)
            printf("%d\n", i);
    }

    return 0;
}
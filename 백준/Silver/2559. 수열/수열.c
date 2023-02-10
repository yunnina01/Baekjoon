#include <stdio.h>

int ps[100001];

int main(){
    int N, K, i, t, res = -1e7;
    scanf("%d %d", &N, &K);
    for(i = 1; i <= N; i++){
        scanf("%d", &ps[i]);
        ps[i] += ps[i - 1];
    }
    for(i = K; i <= N; i++){
        t = ps[i] - ps[i - K];
        if(t > res)
            res = t;
    }
    printf("%d\n", res);

    return 0;
}
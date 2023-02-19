#include <stdio.h>

int main(){
    int N, i, min;
    long long res = 0;
    scanf("%d", &N);
    int cos[N], dis[N - 1];
    for(i = 0; i < N - 1; i++)
        scanf("%d", &dis[i]);
    for(i = 0; i < N; i++)
        scanf("%d", &cos[i]);
    min = cos[0];
    for(i = 0; i < N - 1; i++){
        if(cos[i] < min)
            min = cos[i];
        res += (long long)min * dis[i];
    }
    printf("%lld\n", res);

    return 0;
}
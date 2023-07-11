#include <stdio.h>

int b[50], cnt[50], res;
double g, maxg;

int main(){
    int N, i, j;
    scanf("%d", &N);
    for(i = 0; i < N; i++)
        scanf("%d", &b[i]);
    for(i = 0; i < N; i++){
        maxg = -(1 << 30);
        for(j = i + 1; j < N; j++){
            g = (b[j] - b[i]) * 1.0 / (j - i);
            if(g > maxg){
                maxg = g;
                cnt[i]++;
                cnt[j]++;
            }
        }
    }
    for(i = 0; i < N; i++){
        if(cnt[i] > res)
            res = cnt[i];
    }
    printf("%d\n", res);

    return 0;
}
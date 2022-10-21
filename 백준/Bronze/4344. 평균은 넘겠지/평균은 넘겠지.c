#include <stdio.h>

int main(){
    int C, N, i, j;
    scanf("%d", &C);
    for(i = 0; i < C; i++){
        scanf("%d", &N);
        int a[N], sum = 0, cnt = 0;
        for(j = 0; j < N; j++){
            scanf("%d", &a[j]);
            sum += a[j];
        }
        float avg = (float)sum / N;
        for(j = 0; j < N; j++){
            if(a[j] > avg)
                cnt++;
        }
        printf("%.3f%%\n", (float)cnt / N * 100);        
    }

    return 0;
}
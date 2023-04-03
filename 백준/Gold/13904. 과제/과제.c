#include <stdio.h>
#include <stdlib.h>

typedef struct{
    int d, w;
}HW;

HW hw[1000];
int day[1001], last, res;

int desc(const void *a, const void *b){
    return ((HW*)b)->w - ((HW*)a)->w;
}

int main(){
    int N, i, j;
    scanf("%d", &N);
    for(i = 0; i < N; i++)
        scanf("%d %d", &hw[i].d, &hw[i].w);
    qsort(hw, N, sizeof(HW), desc);
    for(i = 0; i < N; i++){
        if(hw[i].d > last)
            last = hw[i].d;
        for(j = hw[i].d; j > 0; j--){
            if(!day[j]){
                day[j] = hw[i].w;
                break;
            }
        }
    }
    for(i = 1; i <= last; i++)
        res += day[i];
    printf("%d\n", res);

    return 0;
}
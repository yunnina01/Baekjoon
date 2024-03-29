#include <stdio.h>
#include <stdlib.h>

typedef struct{
    int d, w;
}HW;

HW hw[1000];
int day[1001], res;

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
        for(j = hw[i].d; j > 0; j--){
            if(!day[j]){
                day[j] = 1;
                res += hw[i].w;
                break;
            }
        }
    }
    printf("%d\n", res);

    return 0;
}
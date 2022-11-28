#include <stdio.h>
#include <stdlib.h>

typedef struct{
    int x, idx;
}Point;

int compare(const void *a, const void *b){
    return ((Point*)a)->x - ((Point*)b)->x;
}

int main(){
    int N, i, cnt = 0;
    scanf("%d", &N);
    Point arr[N];
    int res[N];
    for(i = 0; i < N; i++){
        scanf("%d", &arr[i].x);
        arr[i].idx = i;
    }
    qsort(arr, N, sizeof(Point), compare);
    int temp = arr[0].x;
    res[arr[0].idx] = 0;
    for(i = 1; i < N; i++){
        if(temp != arr[i].x){
            temp = arr[i].x;
            cnt++;
        }
        res[arr[i].idx] = cnt;
    }
    for(i = 0; i < N; i++)
        printf("%d ", res[i]);

    return 0;
}
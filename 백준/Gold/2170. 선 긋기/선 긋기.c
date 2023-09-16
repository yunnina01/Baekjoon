#include <stdio.h>
#include <stdlib.h>

typedef struct{
    int s, e;
}LINE;

LINE arr[1000001];

int asc(const void *a, const void *b){
    return ((LINE*)a)->s - ((LINE*)b)->s;
}

int main(){
    int N, i, left, right, res;
    scanf("%d", &N);
    for(i = 0; i < N; i++)
        scanf("%d %d", &arr[i].s, &arr[i].e);
    qsort(arr, N, sizeof(LINE), asc);
    left = arr[0].s;
    right = arr[0].e;
    res = 0;
    for(i = 1; i < N; i++){
        if(arr[i].s <= right){
            if(arr[i].e > right)
                right = arr[i].e;
        }
        else{
            res += right - left;
            left = arr[i].s;
            right = arr[i].e;
        }
    }
    printf("%d\n", res + right - left);
}
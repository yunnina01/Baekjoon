#include <stdio.h>
#include <stdlib.h>

typedef struct{
    int s, e;
}Meet;

int asc(const void *a, const void *b){
    Meet *A = (Meet*)a;
    Meet *B = (Meet*)b;
    if(A->e == B->e)
        return A->s - B->s;
    else
        return A->e - B->e;
}

int main(){
    int N, i, last = 0, res = 0;
    scanf("%d", &N);
    Meet arr[N];
    for(i = 0; i < N; i++)
        scanf("%d %d", &arr[i].s, &arr[i].e);
    qsort(arr, N, sizeof(Meet), asc);
    for(i = 0; i < N; i++){
        if(arr[i].s >= last){
            res++;
            last = arr[i].e;
        }
    }
    printf("%d\n", res);

    return 0;
}
#include <stdio.h>
#include <stdlib.h>

int asc(const void *a, const void *b){
    int *A = (int*)a, *B = (int*)b;
    if(*A > *B)
        return 1;
    if(*A < *B)
        return -1;
    return 0;
}

int main(){
    int N, M, X, i;
    scanf("%d", &N);
    int A[N];
    for(i = 0; i < N; i++)
        scanf("%d", &A[i]);
    qsort(A, N, sizeof(int), asc);
    scanf("%d", &M);
    while(M--){
        scanf("%d", &X);
        if(bsearch(&X, A, N, sizeof(int), asc))
            puts("1");
        else
            puts("0");
    }

    return 0;
}
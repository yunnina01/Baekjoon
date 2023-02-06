#include <stdio.h>
#include <stdlib.h>

typedef struct{
    int A, B;
}Wire;

int max(int a, int b){
    return a > b ? a : b;
}

int compare(const void *a, const void *b){
    return ((Wire*)a)->A - ((Wire*)b)->A;
}

int main(){
    int N, i, j, res = 0;
    scanf("%d", &N);
    Wire arr[N];
    int LIS[N];
    for(i = 0; i < N; i++)
        scanf("%d %d", &arr[i].A, &arr[i].B);
    qsort(arr, N, sizeof(Wire), compare);
    for(i = 0; i < N; i++){
        LIS[i] = 1;
        for(j = 0; j < i; j++){
            if(arr[i].B > arr[j].B)
                LIS[i] = max(LIS[j] + 1, LIS[i]);
        }
        if(LIS[i] > res)
            res = LIS[i];
    }
    printf("%d\n", N - res);

    return 0;
}
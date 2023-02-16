#include <stdio.h>
#include <stdlib.h>

int asc(const void *a, const void *b){
    return *(int*)a - *(int*)b;
}

int main(){
    int N, i, res;
    scanf("%d", &N);
    int arr[N];
    for(i = 0; i < N; i++)
        scanf("%d", &arr[i]);
    qsort(arr, N, sizeof(arr[0]), asc);
    res = arr[0];
    for(i = 1; i < N; i++){
        arr[i] += arr[i - 1];
        res += arr[i];
    }
    printf("%d\n", res);

    return 0;
}
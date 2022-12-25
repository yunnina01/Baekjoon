#include <stdio.h>
#include <stdlib.h>

int compare(const void *a, const void *b){
    return *(int*)a - *(int*)b;
}

int main(){
    int N, i;
    scanf("%d", &N);
    int arr[N];
    for(i = 0; i < N; i++)
        scanf("%d", &arr[i]);
    qsort(arr, N, sizeof(arr[0]), compare);
    printf("%lld\n", (long long)arr[0] * arr[N - 1]);

    return 0;
}
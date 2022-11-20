#include <stdio.h>
#include <stdlib.h>

int compare(const void *a, const void *b){
    return *(int*)a - *(int*)b;
}

int main(){
    int N, i, n;
    scanf("%d", &N);
    int arr[N];
    for(i = 0; i < N; i++)
        scanf("%d", &arr[i]);
    qsort(arr, N, sizeof(int), compare);
    for(i = 0; i < N; i++)
        printf("%d\n", arr[i]);

    return 0;
}
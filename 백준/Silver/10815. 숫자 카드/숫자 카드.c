#include <stdio.h>
#include <stdlib.h>

int binary_search(int *arr, int N, int key){
    int low = 0, high = N - 1, mid;
    while(low <= high){
        mid = (low + high) / 2;
        if(key == arr[mid])
            return 1;
        else if(key > arr[mid])
            low = mid + 1;
        else
            high = mid - 1;
    }
    return 0;
}

int compare(const void *a, const void *b){
    return *(int*)a - *(int*)b;
}

int main(){
    int N, M, i, j;
    scanf("%d", &N);
    int A[N];
    for(i = 0; i < N; i++)
        scanf("%d", &A[i]);
    scanf("%d", &M);
    int B[M];
    for(i = 0; i < M; i++)
        scanf("%d", &B[i]);
    qsort(A, N, sizeof(int), compare);
    for(i = 0; i < M; i++)
        printf("%d ", binary_search(A, N, B[i]));
        
    return 0;
}
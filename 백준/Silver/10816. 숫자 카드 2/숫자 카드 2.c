#include <stdio.h>
#include <stdlib.h>

int lower_bound(int *arr, int N, int key){
    int low = 0, high = N - 1, mid;
    while(low < high){
        mid = (low + high) / 2;
        if(key <= arr[mid])
            high = mid;
        else
            low = mid + 1;
    }
    return high;
}

int upper_bound(int *arr, int N, int key){
    int low = 0, high = N - 1, mid;
    while(low < high){
        mid = (low + high) / 2;
        if(key < arr[mid])
            high = mid;
        else
            low = mid + 1;
    }
    if(arr[high] == key)
        high++;
    return high;
}

int compare(const void *a, const void *b){
    return *(int*)a - *(int*)b;
}

int main(){
    int N, M, i;
    scanf("%d", &N);
    int A[N];
    for(i = 0; i < N; i++)
        scanf("%d", &A[i]);
    qsort(A, N, sizeof(int), compare);
    scanf("%d", &M);
    int B[M];
    for(i = 0; i < M; i++)
        scanf("%d", &B[i]);
    for(i = 0; i < M; i++)
        printf("%d ", upper_bound(A, N, B[i]) - lower_bound(A, N, B[i]));

    return 0;
}
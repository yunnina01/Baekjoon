#include <stdio.h>
#include <stdlib.h>

int asc(const void *a, const void *b){
    return *(int*)a - *(int*)b;
}

int main(){
    int N, C, i, j;
    scanf("%d %d", &N, &C);
    int arr[N];
    for(i = 0; i < N; i++)
        scanf("%d", &arr[i]);
    qsort(arr, N, sizeof(int), asc);
    int low = 1, high = arr[N - 1] - arr[0];
    while(low <= high){
        int mid = (low + high) / 2, cnt = 1;
        for(i = 1, j = 0; i < N; i++){
            if(arr[i] - arr[j] >= mid){
                cnt++;
                j = i;
            }
        }
        if(cnt >= C)
            low = mid + 1;
        else
            high = mid - 1;
    }
    printf("%d\n", high);

    return 0;
}
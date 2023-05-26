#include <stdio.h>
#include <stdlib.h>

int arr[100000], min = (1 << 31) - 1;

int asc(const void *a, const void *b){
    return *(int*)a - *(int*)b;
}

int main(){
    int N, i;
    scanf("%d", &N);
    for(i = 0; i < N; i++)
        scanf("%d", &arr[i]);
    qsort(arr, N, sizeof(int), asc);
    int left = 0, right = N - 1, val, low, high;
    while(left < right){
        val = arr[left] + arr[right];
        if(abs(val) < abs(min)){
            min = val;
            low = left;
            high = right;
        }
        if(val < 0)
            left++;
        else
            right--;
    }
    printf("%d %d\n", arr[low], arr[high]);

    return 0;
}
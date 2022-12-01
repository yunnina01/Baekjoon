#include <stdio.h>

int temp[500000], K, S, cnt;

void merge(int *arr, int left, int mid, int right){
    int i = left, j = mid + 1, k = left;
    while(i <= mid && j <= right){
        if(arr[i] <= arr[j])
            temp[k++] = arr[i++];
        else
            temp[k++] = arr[j++];
    }
    while(i <= mid)
        temp[k++] = arr[i++];
    while(j <= right)
        temp[k++] = arr[j++];
    for(i = left; i <= right; i++){
        cnt++;
        arr[i] = temp[i];
        if(cnt == K)
            S = arr[i];
    }
}

void merge_sort(int *arr, int left, int right){
    if(left < right){
        int mid = (left + right) / 2;
        merge_sort(arr, left, mid);
        merge_sort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }
}

int main(){
    int N, i;
    scanf("%d %d", &N, &K);
    int arr[N];
    for(i = 0; i < N; i++)
        scanf("%d", &arr[i]);
    merge_sort(arr, 0, N - 1);
    if(K > cnt)
        puts("-1");
    else
        printf("%d\n", S);

    return 0;
}
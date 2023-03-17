#include <stdio.h>

int arr[1000000];
long long low, high, mid, cnt;

int main(){
    int N, M, i;
    scanf("%d %d", &N, &M);
    for(i = 0; i < N; i++){
        scanf("%d", &arr[i]);
        if(arr[i] > high)
            high = arr[i];
    }
    while(low <= high){
        mid = (low + high) / 2, cnt = 0;
        for(i = 0; i < N; i++){
            if(arr[i] > mid)
                cnt += arr[i] - mid;
        }
        if(cnt >= M)
            low = mid + 1;
        else
            high = mid - 1;
    }
    printf("%lld\n", high);

    return 0;
}
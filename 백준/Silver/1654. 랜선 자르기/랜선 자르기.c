#include <stdio.h>

int arr[10000];

int main(){
    int K, N, i;
    long long low = 1, high = 0, mid, cnt;
    scanf("%d %d", &K, &N);
    for(i = 0; i < K; i++){
        scanf("%d", &arr[i]);
        high += arr[i];
    }
    while(low <= high){
        mid = (low + high) / 2, cnt = 0;
        for(i = 0; i < K; i++)
            cnt += arr[i] / mid;
        if(cnt >= N)            
            low = mid + 1;
        else
            high = mid - 1;
    }
    printf("%lld\n", high);

    return 0;
}
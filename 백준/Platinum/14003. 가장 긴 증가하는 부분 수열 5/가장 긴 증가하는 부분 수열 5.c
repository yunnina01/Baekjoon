#include <stdio.h>

int arr[1000000], LIS[1000000], dp[1000000], idx;

int main(){
    int N, i, j;
    scanf("%d", &N);
    for(i = 0; i < N; i++){
        scanf("%d", &arr[i]);
        LIS[idx] = arr[i];
        if(!idx || LIS[idx] > LIS[idx - 1])
            dp[i] = ++idx;
        else{
            int left = 0, right = idx, mid;
            while(left < right){
                mid = (left + right) / 2;
                if(LIS[idx] > LIS[mid])
                    left = mid + 1;
                else
                    right = mid;
            }
            LIS[right] = LIS[idx];
            dp[i] = right + 1;
        }
    }
    j = idx;
    for(i = N - 1; i >= 0; i--){
        if(dp[i] == j)
            LIS[--j] = arr[i];
    }
    printf("%d\n", idx);
    for(i = 0; i < idx; i++)
        printf("%d ", LIS[i]);

    return 0;
}
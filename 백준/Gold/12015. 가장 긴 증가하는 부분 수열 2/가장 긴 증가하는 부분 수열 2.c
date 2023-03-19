#include <stdio.h>

int main(){
    int N, idx = 0;
    scanf("%d", &N);
    int LIS[N];
    while(N--){
        scanf("%d", &LIS[idx]);
        if(!idx || LIS[idx] > LIS[idx - 1])
            idx++;
        else{
            int low = 0, high = idx, mid;
            while(low < high){
                mid = (low + high) / 2;
                if(LIS[idx] > LIS[mid])
                    low = mid + 1;
                else
                    high = mid;
            }
            LIS[high] = LIS[idx];
        }
    }
    printf("%d\n", idx);

    return 0;
}
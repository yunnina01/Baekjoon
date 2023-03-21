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
            int left = 0, right = idx, mid;
            while(left < right){
                mid = (left + right) / 2;
                if(LIS[idx] > LIS[mid])
                    left = mid + 1;
                else
                    right = mid;
            }
            LIS[right] = LIS[idx];
        }
    }
    printf("%d\n", idx);

    return 0;
}
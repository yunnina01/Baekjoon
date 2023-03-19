#include <stdio.h>

int main(){
    int N, k, i;
    scanf("%d %d", &N, &k);
    int left = 1, right = k;
    while(left <= right){
        int mid = (left + right) / 2, cnt = 0;
        for(i = 1; i <= N; i++)
            cnt += N < mid / i ? N : mid / i;
        if(cnt < k)
            left = mid + 1;
        else
            right = mid - 1;
    }
    printf("%d\n", left);

    return 0;
}
#include <stdio.h>

int max(int a, int b){
    return a > b ? a : b;
}

int main(){
    int N, i, j, res = 0;
    scanf("%d", &N);
    int A[N], LIS[N], LDS[N];
    for(i = 0; i < N; i++)
        scanf("%d", &A[i]);
    for(i = 0; i < N; i++){
        LIS[i] = 1;
        for(j = 0; j < i; j++){
            if(A[i] > A[j])
                LIS[i] = max(LIS[j] + 1, LIS[i]);
        }
    }
    for(i = N - 1; i >= 0; i--){
        LDS[i] = 1;
        for(j = N - 1; j > i; j--){
            if(A[i] > A[j])
                LDS[i] = max(LDS[j] + 1, LDS[i]);
        }
        if(LIS[i] + LDS[i] - 1 > res)
            res = LIS[i] + LDS[i] - 1;
    }
    printf("%d\n", res);

    return 0;
}
#include <stdio.h>

int max(int a, int b){
    return a > b ? a : b;
}

int main(){
    int N, i, j, res = 0;
    scanf("%d", &N);
    int arr[N], LIS[N];
    for(i = 0; i < N; i++)
        scanf("%d", &arr[i]);
    for(i = 0; i < N; i++){
        LIS[i] = 1;
        for(j = 0; j < i; j++){
            if(arr[i] > arr[j])
                LIS[i] = max(LIS[j] + 1, LIS[i]);
        }
    }
    for(i = 0; i < N; i++){
        if(LIS[i] > res)
            res = LIS[i];
    }
    printf("%d\n", res);

    return 0;
}
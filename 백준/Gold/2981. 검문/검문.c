#include <stdio.h>
#include <stdlib.h>

int M[100];

int compare(const void *a, const void *b){
    return *(int*)a - *(int*)b;
}

int main(){
    int N, i, j, x, cnt = 0;
    scanf("%d", &N);
    int arr[N];
    for(i = 0; i < N; i++)
        scanf("%d", &arr[i]);
    x = arr[1] > arr[0] ? arr[1] - arr[0] : arr[0] - arr[1];
    for(i = 2; i <= x / i; i++){
        if(!(x % i)){
            M[cnt++] = i;
            if(x / i != i)
                M[cnt++] = x / i;
        }
    }
    qsort(M, cnt, sizeof(M[0]), compare);
    M[cnt++] = x;
    for(i = 0; i < cnt; i++){
        for(j = 1; j < N; j++){
            if(arr[j] % M[i] != arr[0] % M[i])
                break;
        }
        if(j == N)
            printf("%d ", M[i]);
    }

    return 0;
}
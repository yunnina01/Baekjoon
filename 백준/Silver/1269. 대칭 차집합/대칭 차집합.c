#include <stdio.h>
#include <stdlib.h>

int compare(const void *a, const void *b){
    return *(int*)a - *(int*)b;
}

int main(){
    int N, M, i, cnt = 0;
    scanf("%d %d", &N, &M);
    int sum = N + M, A[sum];
    for(i = 0; i < sum; i++)
        scanf("%d", &A[i]);
    qsort(A, sum, sizeof(A[0]), compare);
    for(i = 1; i < sum; i++){
        if(A[i - 1] == A[i])
            cnt++;
    }
    printf("%d\n", sum - cnt * 2);

    return 0;
}
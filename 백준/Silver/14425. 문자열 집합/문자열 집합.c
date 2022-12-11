#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int binary_search(char arr[][501], int N, char *key){
    int low = 0, high = N - 1, mid, cmp;
    while(low <= high){
        mid = (low + high) / 2;
        cmp = strcmp(key, arr[mid]);
        if(!cmp)
            return 1;
        else if(cmp > 0)
            low = mid + 1;
        else
            high = mid - 1;
    }
    return 0;
}

int compare(const void *a, const void *b){
    return strcmp((char*)a, (char*)b);
}

int main(){
    int N, M, i, cnt = 0;
    scanf("%d %d", &N, &M);
    char A[N][501], B[501];
    for(i = 0; i < N; i++)
        scanf("%s", A[i]);
    qsort(A, N, sizeof(A[0]), compare);
    for(i = 0; i < M; i++){
        scanf("%s", B);
        cnt += binary_search(A, N, B);
    }
    printf("%d\n", cnt);

    return 0;
}
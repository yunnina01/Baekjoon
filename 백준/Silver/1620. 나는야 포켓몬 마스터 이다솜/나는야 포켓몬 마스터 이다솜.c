#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct{
    int idx;
    char name[21];
}Mon;

void binary_search(Mon *arr, int N, char *key){
    int low = 0, high = N - 1, mid, cmp;
    while(low <= high){
        mid = (low + high) / 2;
        cmp = strcmp(key, arr[mid].name);
        if(!cmp)
            printf("%d\n", arr[mid].idx);
        if(cmp > 0)
            low = mid + 1;
        else
            high = mid - 1;
    }
}

int compare(const void *a, const void *b){
    return strcmp(((Mon*)a)->name, ((Mon*)b)->name);
}

int main(){
    int N, M, i, temp;
    scanf("%d %d", &N, &M);
    Mon A[N], B[N];
    char C[M];
    for(i = 0; i < N; i++){
        A[i].idx = B[i].idx = i + 1;
        scanf("%s", A[i].name);
        strcpy(B[i].name, A[i].name);
    }
    qsort(B, N, sizeof(Mon), compare);
    for(i = 0; i < M; i++){
        scanf("%s", C);
        temp = atoi(C);
        if(temp)
            printf("%s\n", A[temp - 1].name);
        else
            binary_search(B, N, C);
    }

    return 0;
}
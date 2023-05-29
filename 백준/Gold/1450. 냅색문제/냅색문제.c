#include <stdio.h>
#include <stdlib.h>

int arr[30], dq[2][1 << 15], len[2], N, C;

int asc(const void *a, const void *b){
    return *(int*)a - *(int*)b;
}

void knapsack(int mode, int cur, int end, int cap){
    if(cur == end){
        dq[mode][len[mode]++] = cap;
        return;
    }
    if(cap + arr[cur] <= C)
        knapsack(mode, cur + 1, end, cap + arr[cur]);
    knapsack(mode, cur + 1, end, cap);
}

int upper_bound(int key){
    int low = 0, high = len[1], mid;
    while(low < high){
        mid = (low + high) / 2;
        if(key < dq[1][mid])
            high = mid;
        else
            low = mid + 1;
    }
    return high;
}

int main(){
    int i, res = 0;
    scanf("%d %d", &N, &C);
    for(i = 0; i < N; i++)
        scanf("%d", &arr[i]);
    knapsack(0, 0, N / 2, 0);
    knapsack(1, N / 2, N, 0);
    qsort(dq[1], len[1], sizeof(int), asc);
    for(i = 0; i < len[0]; i++)
        res += upper_bound(C - dq[0][i]);
    printf("%d\n", res);
    
    return 0;
}
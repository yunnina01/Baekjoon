#include <stdio.h>
#include <stdlib.h>
#include <math.h>

typedef struct{
    int x, y;
}Pair;

Pair *land;
int *tree, *y_lst;

int pair_asc(const void *a, const void *b){
    Pair *A = (Pair*)a, *B = (Pair*)b;
    return A->x == B->x ? A->y - B->y : B->x - A->x;
}

int asc(const void *a, const void *b){
    return *(int*)a - *(int*)b;
}

int upper_bound(int N, int key){
    int low = 0, high = N - 1, mid;
    while(low < high){
        mid = (low + high) >> 1;
        if(key < y_lst[mid])
            high = mid;
        else
            low = mid + 1;
    }
    if(y_lst[high] == key)
        high++;
    return high;
}

void update(int node, int start, int end, int idx){
    if(idx < start || idx > end)
        return;
    tree[node]++;
    if(start != end){
        int mid = (start + end) >> 1;
        update(node * 2, start, mid, idx);
        update(node * 2 + 1, mid + 1, end, idx);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }
}

int query(int node, int start, int end, int left, int right){
    if(left > end || right < start)
        return 0;
    if(left <= start && right >= end)
        return tree[node];
    int mid = (start + end) >> 1;
    return query(node * 2, start, mid, left, right) + query(node * 2 + 1, mid + 1, end, left, right);
}

int main(){
    int T, N, i, idx;
    long long res;
    scanf("%d", &T);
    while(T--){
        scanf("%d", &N);
        land = (Pair*)malloc(sizeof(Pair) * N);
        tree = (int*)calloc(1 << ((int)ceil(log2(N)) + 1), sizeof(int));
        y_lst = (int*)malloc(sizeof(int) * N);
        res = 0;
        for(i = 0; i < N; i++){
            scanf("%d %d", &land[i].x, &land[i].y);
            y_lst[i] = land[i].y;
        }
        qsort(land, N, sizeof(Pair), pair_asc);
        qsort(y_lst, N, sizeof(int), asc);
        for(i = 0; i < N; i++){
            idx = upper_bound(N, land[i].y) - 1;
            res += query(1, 0, N - 1, 0, idx);
            update(1, 0, N - 1, idx);
        }
        printf("%lld\n", res);
    }

    return 0;
}
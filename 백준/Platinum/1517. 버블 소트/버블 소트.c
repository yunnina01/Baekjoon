#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define ll long long

typedef struct{
    int f, s;
}Pair;

Pair *A;
ll *tree, res;

int asc(const void *a, const void *b){
    Pair *A = (Pair*)a, *B = (Pair*)b;
    if(A->f == B->f)
        return A->s - B->s;
    return A->f - B->f;
}

void update(int node, int start, int end, int idx){
    if(start == end){
        tree[node] = 1;
        return;
    }
    int mid = (start + end) >> 1;
    if(idx <= mid)
        update(node * 2, start, mid, idx);
    else
        update(node * 2 + 1, mid + 1, end, idx);
    tree[node] = tree[node * 2] + tree[node * 2 + 1];
}

ll query(int node, int start, int end, int left, int right){
    if(left > end || right < start)
        return 0;
    if(left <= start && right >= end)
        return tree[node];
    int mid = (start + end) >> 1;
    return query(node * 2, start, mid, left, right) + query(node * 2 + 1, mid + 1, end, left, right);
}

int main(){
    int N, i;
    scanf("%d", &N);
    tree = (ll*)calloc(1 << ((int)ceil(log2(N)) + 1), sizeof(ll));
    A = (Pair*)malloc(sizeof(Pair) * N);
    for(i = 0; i < N; i++){
        scanf("%d", &A[i].f);
        A[i].s = i;
    }
    qsort(A, N, sizeof(Pair), asc);
    for(i = 0; i < N; i++){
        res += query(1, 0, N - 1, A[i].s, N - 1);
        update(1, 0, N - 1, A[i].s);
    }
    printf("%lld\n", res);

    return 0;
}
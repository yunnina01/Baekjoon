#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int *tree, *A;

int min(int a, int b){
    if(a == -1)
        return b;
    if(b == -1)
        return a;
    if(A[a] == A[b])
        return a < b ? a : b;
    return A[a] < A[b] ? a : b;
}

int init(int node, int start, int end){
    if(start == end)
        return tree[node] = start;
    int mid = (start + end) >> 1, l, r;
    l = init(node * 2, start, mid);
    r = init(node * 2 + 1, mid + 1, end);
    return tree[node] = min(l, r);
}

int update(int node, int start, int end, int idx){
    if(idx < start || idx > end || start == end)
        return tree[node];
    int mid = (start + end) >> 1, l, r;
    l = update(node * 2, start, mid, idx);
    r = update(node * 2 + 1, mid + 1, end, idx);
    return tree[node] = min(l, r);
}

int query(int node, int start, int end, int left, int right){
    if(left > end || right < start)
        return -1;
    if(left <= start && right >= end)
        return tree[node];
    int mid = (start + end) >> 1, l, r;
    l = query(node * 2, start, mid, left, right);
    r = query(node * 2 + 1, mid + 1, end, left, right);
    return min(l, r);
}

int main(){
    int N, M, i, j, op;
    scanf("%d", &N);
    tree = (int*)calloc(1 << ((int)ceil(log2(N + 1)) + 1), sizeof(int));
    A = (int*)malloc(sizeof(int) * (N + 1));
    for(i = 1; i <= N; i++)
        scanf("%d", &A[i]);
    scanf("%d", &M);
    init(1, 1, N);
    while(M--){
        scanf("%d %d %d", &op, &i, &j);
        if(op == 1){
            A[i] = j;
            update(1, 1, N, i);
        }
        else
            printf("%d\n", query(1, 1, N, i, j));
    }

    return 0;
}
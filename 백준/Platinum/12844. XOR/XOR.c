#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int *tree, *lazy, *A;

int init(int node, int start, int end){
    if(start == end)
        return tree[node] = A[start];
        
    int mid = (start + end) >> 1;
    return tree[node] = init(node * 2, start, mid) ^ init(node * 2 + 1, mid + 1, end);
}

void update_lazy(int node, int start, int end){
    if(lazy[node]){
        if((end - start + 1) % 2)
            tree[node] ^= lazy[node];
        if(start != end){
            lazy[node * 2] ^= lazy[node];
            lazy[node * 2 + 1] ^= lazy[node];
        }
        lazy[node] = 0;
    }
}

int update_tree(int node, int start, int end, int left, int right, int val){
    update_lazy(node, start, end);
    if(left > end || right < start)
        return tree[node];
    if(left <= start && right >= end){
        lazy[node] ^= val;
        update_lazy(node, start, end);
        return tree[node];
    }
    int mid = (start + end) >> 1;
    return tree[node] = update_tree(node * 2, start, mid, left, right, val) ^ update_tree(node * 2 + 1, mid + 1, end, left, right, val);
}

int query(int node, int start, int end, int left, int right){
    update_lazy(node, start, end);
    if(left > end || right < start)
        return 0;
    if(left <= start && right >= end)
        return tree[node];
    int mid = (start + end) >> 1;
    return query(node * 2, start, mid, left, right) ^ query(node * 2 + 1, mid + 1, end, left, right);
}

int main(){
    int N, M, op, i, j, k;
    scanf("%d", &N);
    k = 1 << ((int)ceil(log2(N)) + 1);
    tree = (int*)calloc(k, sizeof(int));
    lazy = (int*)calloc(k, sizeof(int));
    A = (int*)malloc(sizeof(int) * N);
    for(i = 0; i < N; i++)
        scanf("%d", &A[i]);
    scanf("%d", &M);
    init(1, 0, N - 1);
    while(M--){
        scanf("%d %d %d", &op, &i, &j);
        if(op == 1){
            scanf("%d", &k);
            update_tree(1, 0, N - 1, i, j, k);
        }
        else
            printf("%d\n", query(1, 0, N - 1, i, j));
    }

    return 0;
}
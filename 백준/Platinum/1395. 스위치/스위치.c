#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int *tree, *lazy;

void update_lazy(int node, int start, int end){
    if(lazy[node] % 2){
        tree[node] = (end - start + 1) - tree[node];
        if(start != end){
            lazy[node * 2] += lazy[node];
            lazy[node * 2 + 1] += lazy[node];
        }
        lazy[node] = 0;
    }
}

void update_tree(int node, int start, int end, int left, int right){
    update_lazy(node, start, end);
    if(left > end || right < start)
        return;
    if(left <= start && right >= end){
        tree[node] = (end - start + 1) - tree[node];
        if(start != end){
            lazy[node * 2]++;
            lazy[node * 2 + 1]++;
        }
        return;
    }
    int mid = (start + end) >> 1;
    update_tree(node * 2, start, mid, left, right);
    update_tree(node * 2 + 1, mid + 1, end, left, right);
    tree[node] = tree[node * 2] + tree[node * 2 + 1];
}

int query(int node, int start, int end, int left, int right){
    update_lazy(node, start, end);
    if(left > end || right < start)
        return 0;
    if(left <= start && right >= end)
        return tree[node];
    int mid = (start + end) >> 1;
    return query(node * 2, start, mid, left, right) + query(node * 2 + 1, mid + 1, end, left, right);
}

int main(){
    int N, M, O, S, T;
    scanf("%d %d", &N, &M);
    T = 1 << ((int)ceil(log2(N)) + 1);
    tree = (int*)calloc(T, sizeof(int));
    lazy = (int*)calloc(T, sizeof(int));
    while(M--){
        scanf("%d %d %d", &O, &S, &T);
        if(!O)
            update_tree(1, 0, N - 1, S - 1, T - 1);
        else
            printf("%d\n", query(1, 0, N - 1, S - 1, T - 1));
    }

    return 0;
}
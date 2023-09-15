#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define ll long long

ll *tree, *lazy;

void update_lazy(int node, int start, int end){
    if(lazy[node]){
        tree[node] += (end - start + 1) * lazy[node];
        if(start != end){
            lazy[node * 2] += lazy[node];
            lazy[node * 2 + 1] += lazy[node];
        }
        lazy[node] = 0;
    }
}

void update_tree(int node, int start, int end, int left, int right, int val){
    update_lazy(node, start, end);
    if(left > end || right < start)
        return;
    if(left <= start && right >= end){
        tree[node] += (end - start + 1) * val;
        if(start != end){
            lazy[node * 2] += val;
            lazy[node * 2 + 1] += val;
        }
        return;
    }
    int mid = (start + end) >> 1;
    update_tree(node * 2, start, mid, left, right, val);
    update_tree(node * 2 + 1, mid + 1, end, left, right, val);
    tree[node] = tree[node * 2] + tree[node * 2 + 1];
}

ll query(int node, int start, int end, int left, int right){
    update_lazy(node, start, end);
    if(left > end || right < start)
        return 0;
    if(left <= start && right >= end)
        return tree[node];
    int mid = (start + end) >> 1;
    return query(node * 2, start, mid, left, right) + query(node * 2 + 1, mid + 1, end, left, right);
}

int main(){
    int N, Q, L, R, i, op;
    scanf("%d", &N);
    i = 1 << ((int)ceil(log2(N)) + 1);
    tree = (ll*)calloc(i, sizeof(ll));
    lazy = (ll*)calloc(i, sizeof(ll));
    for(i = R = 0; i < N; i++){
        scanf("%d", &L);
        update_tree(1, 0, N - 1, i, i, L - R);
        R = L;
    }
    scanf("%d", &Q);
    while(Q--){
        scanf("%d %d", &op, &L);
        if(op == 1){
            scanf("%d", &R);
            update_tree(1, 0, N - 1, L - 1, R - 1, 1);
            update_tree(1, 0, N - 1, R, R, -(R - L + 1));
        }
        else
            printf("%lld\n", query(1, 0, N - 1, 0, L - 1));
    }

    return 0;
}
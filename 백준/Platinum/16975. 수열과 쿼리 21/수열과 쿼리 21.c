#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define ll long long

ll *tree, *lazy;
int *arr;

ll init(int node, int start, int end){
    if(start == end)
        return tree[node] = arr[start];
    int mid = (start + end) >> 1;
    return tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
}

void update_lazy(int node, int start, int end){
    if(lazy[node]){
        tree[node] += (end - start + 1) * lazy[node];
        if(start != end){
            lazy[node * 2] += lazy[node];
            lazy[node * 2 + 1] += lazy[node];
        }
    }
    lazy[node] = 0;
}

ll update_tree(int node, int start, int end, int left, int right, int val){
    update_lazy(node, start, end);
    if(left > end || right < start)
        return tree[node];
    if(left <= start && right >= end){
        lazy[node] += val;
        update_lazy(node, start, end);
        return tree[node];
    }
    int mid = (start + end) >> 1;
    return tree[node] = update_tree(node * 2, start, mid, left, right, val) + update_tree(node * 2 + 1, mid + 1, end, left, right, val);
}


ll query(int node, int start, int end, int idx){
    update_lazy(node, start, end);
    if(start == end)
        return tree[node];
    int mid = (start + end) >> 1;
    if(idx <= mid)
        return query(node * 2, start, mid, idx);
    return query(node * 2 + 1, mid + 1, end, idx);
}

int main(){
    int N, M, q, i, j, k, x;
    scanf("%d", &N);
    x = 1 << ((int)ceil(log2(N)) + 1);
    tree = (ll*)calloc(x, sizeof(ll));
    lazy = (ll*)calloc(x, sizeof(ll));
    arr = (int*)malloc(sizeof(int) * N);
    for(i = 0; i < N; i++)
        scanf("%d", &arr[i]);
    init(1, 0, N - 1);
    scanf("%d", &M);
    while(M--){
        scanf("%d", &q);
        if(q == 1){
            scanf("%d %d %d", &i, &j, &k);
            update_tree(1, 0, N - 1, i - 1, j - 1, k);
        }
        else{
            scanf("%d", &x);
            printf("%lld\n", query(1, 0, N - 1, x - 1));
        }
    }

    return 0;
}
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define ll long long

ll *tree, *lazy, *arr;

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
        lazy[node] = 0;
    }
}

void update_tree(int node, int start, int end, int left, int right, ll val){
    update_lazy(node, start, end);
    if(right < start || left > end)
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

ll sum(int node, int start, int end, int left, int right){
    update_lazy(node, start, end);
    if(left > end || right < start)
        return 0;
    if(left <= start && right >= end)
        return tree[node];
    int mid = (start + end) >> 1;
    return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);
}

int main(){
    int N, M, K, a, b, c, i;
    ll d;
    scanf("%d %d %d", &N, &M, &K);
    a = 1 << ((int)ceil(log2(N)) + 1);
    tree = (ll*)calloc(a, sizeof(ll));
    lazy = (ll*)calloc(a, sizeof(ll));
    arr = (ll*)malloc(sizeof(ll) * N);
    for(i = 0; i < N; i++)
        scanf("%lld", &arr[i]);
    init(1, 0, N - 1);
    M += K;
    while(M--){
        scanf("%d %d %d", &a, &b, &c);
        if(a == 1){
            scanf("%lld", &d);
            update_tree(1, 0, N - 1, b - 1, c - 1, d);
        }
        else
            printf("%lld\n", sum(1, 0, N - 1, b - 1, c - 1));
    }

    return 0;
}
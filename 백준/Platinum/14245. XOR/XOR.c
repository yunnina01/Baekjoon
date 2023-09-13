#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int *tree, *lazy, *arr;

int init(int node, int start, int end){
    if(start == end)
        return tree[node] = arr[start];
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

int query(int node, int start, int end, int idx){
    update_lazy(node, start, end);
    if(idx < start || idx > end)
        return tree[node];
    if(start == end)
        return 0;
    int mid = (start + end) >> 1;
    return query(node * 2, start, mid, idx) ^ query(node * 2 + 1, mid + 1, end, idx);
}

int main(){
    int n, m, t, a, b, c, i;
    scanf("%d", &n);
    t = 1 << ((int)ceil(log2(n)) + 1);
    tree = (int*)calloc(t, sizeof(int));
    lazy = (int*)calloc(t, sizeof(int));
    arr = (int*)malloc(sizeof(int) * n);
    for(i = 0; i < n; i++)
        scanf("%d", &arr[i]);
    scanf("%d", &m);
    init(1, 0, n - 1);
    while(m--){
        scanf("%d %d", &t, &a);
        if(t == 1){
            scanf("%d %d", &b, &c);
            update_tree(1, 0, n - 1, a, b, c);
        }
        else
            printf("%d\n", tree[1] ^ query(1, 0, n - 1, a));
    }

    return 0;
}
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

typedef struct{
    int f, s;
}Pair;

Pair *tree;
int *arr;

int min(int a, int b){
    return a < b ? a : b;
}

int max(int a, int b){
    return a > b ? a : b;
}

Pair init(int node, int start, int end){
    if(start == end){
        tree[node].f = tree[node].s = arr[start] = start;
        return tree[node];
    }
    Pair l, r;
    int mid = (start + end) >> 1;
    l = init(node * 2, start, mid);
    r = init(node * 2 + 1, mid + 1, end);
    return tree[node] = (Pair){max(l.f, r.f), min(l.s, r.s)};
}

Pair update(int node, int start, int end, int idx, int val){
    if(idx < start || idx > end)
        return tree[node];
    tree[node] = (Pair){max(tree[node].f, val), min(tree[node].s, val)};
    if(start == end){
        arr[idx] = tree[node].f = tree[node].s = val;
        return tree[node];
    }
    Pair l, r;
    int mid = (start + end) >> 1;
    l = update(node * 2, start, mid, idx, val);
    r = update(node * 2 + 1, mid + 1, end, idx, val);
    return tree[node] = (Pair){max(l.f, r.f), min(l.s, r.s)};
}

int query(int node, int start, int end, int left, int right){
    if(left > end || right < start)
        return 1;
    if(left <= start && right >= end)
        return left <= tree[node].s && right >= tree[node].f;
    int mid = (start + end) >> 1;
    return query(node * 2, start, mid, left, right) && query(node * 2 + 1, mid + 1, end, left, right);
}

int main(){
    int T, N, K, Q, A, B, temp;
    scanf("%d", &T);
    while(T--){
        scanf("%d %d", &N, &K);
        tree = (Pair*)calloc(1 << ((int)ceil(log2(N)) + 1), sizeof(Pair));
        arr = (int*)calloc(N, sizeof(int));
        init(1, 0, N - 1);
        while(K--){
            scanf("%d %d %d", &Q, &A, &B);
            if(!Q){
                temp = arr[A];
                update(1, 0, N - 1, A, arr[B]);
                update(1, 0, N - 1, B, temp);
            }
            else
                printf("%s\n", query(1, 0, N - 1, A, B) ? "YES" : "NO");
        }
    }

    return 0;
}
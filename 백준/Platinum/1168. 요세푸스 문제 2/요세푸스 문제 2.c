#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int *tree;

int init(int node, int start, int end){
    if(start == end)
        return tree[node] = 1;
    int mid = (start + end) >> 1;
    return tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
}

int update(int node, int start, int end, int val){
    tree[node]--;
    if(start == end)
        return 0;
    int mid = (start + end) >> 1;
    if(val <= mid)
        return update(node * 2, start, mid, val);
    return update(node * 2 + 1, mid + 1, end, val);
}

int query(int node, int start, int end, int idx){
    if(start == end)
        return start;
    int mid = (start + end) >> 1;
    if(idx <= tree[node * 2])
        return query(node * 2, start, mid, idx);
    return query(node * 2 + 1, mid + 1, end, idx - tree[node * 2]);
}

int main(){
    int N, K, i, idx, sz, n;
    scanf("%d %d", &N, &K);
    tree = (int*)calloc(1 << ((int)ceil(log2(N + 1)) + 1), sizeof(int));
    init(1, 1, N);
    idx = 1;
    K--;
    printf("<");
    for(i = 0; i < N; i++){
        sz = N - i;
        idx += K;
        if(idx % sz == 0)
            idx = sz;
        else if(idx > sz)
            idx %= sz;
        n = query(1, 1, N, idx);
        update(1, 1, N, n);
        printf("%d", n);
        if(i < N - 1)
            printf(", ");
    }
    puts(">");

    return 0;
}
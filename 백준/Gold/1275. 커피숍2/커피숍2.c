#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define ll long long

ll *tree;
int *arr;

ll init(int node, int start, int end){
    if(start == end)
        return tree[node] = arr[start];
    int mid = (start + end) >> 1;
    return tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
}

void update(int node, int start, int end, int idx, ll val){
    if(idx < start || idx > end)
        return;
    tree[node] += val;
    if(start != end){
        int mid = (start + end) >> 1;
        update(node * 2, start, mid, idx, val);
        update(node * 2 + 1, mid + 1, end, idx, val);
    }
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
    int N, Q, x, y, a, b, i;
    scanf("%d %d", &N, &Q);
    tree = (ll*)calloc(1 << ((int)ceil(log2(N)) + 1), sizeof(ll));
    arr = (int*)malloc(sizeof(int) * N);
    for(i = 0; i < N; i++)
        scanf("%d", &arr[i]);
    init(1, 0, N - 1);
    while(Q--){
        scanf("%d %d %d %d", &x, &y, &a, &b);
        if(x > y){
            i = x;
            x = y;
            y = i;
        }
        printf("%lld\n", query(1, 0, N - 1, x - 1, y - 1));
        ll val = (ll)b - arr[a - 1];
        arr[a - 1] = b;
        update(1, 0, N - 1, a - 1, val);
    }

    return 0;
}
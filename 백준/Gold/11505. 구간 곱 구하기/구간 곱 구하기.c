#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define ll long long

int *tree, *arr;
const int p = 1e9 + 7;

ll init(int node, int start, int end){
    if(start == end)
        return tree[node] = arr[start];
    int mid = (start + end) >> 1;
    return tree[node] = (init(node * 2, start, mid) * init(node * 2 + 1, mid + 1, end)) % p;
}

ll update(int node, int start, int end, int idx, int val){
    if(idx < start || idx > end)
        return tree[node];
    if(start == end)
        return tree[node] = val;
    int mid = (start + end) >> 1;
    return tree[node] = (update(node * 2, start, mid, idx, val) * update(node * 2 + 1, mid + 1, end, idx, val)) % p;
}

ll mul(int node, int start, int end, int left, int right){
    if(left > end || right < start)
        return 1;
    if(left <= start && right >= end)
        return tree[node];
    int mid = (start + end) >> 1;
    return (mul(node * 2, start, mid, left, right) * mul(node * 2 + 1, mid + 1, end, left, right)) % p;
}

int main(){
    int N, M, K, a, b, c, i, low, high;
    scanf("%d %d %d", &N, &M, &K);
    tree = (int*)calloc(1 << ((int)ceil(log2(N)) + 1), sizeof(int));
    arr = (int*)malloc(sizeof(int) * N);
    for(i = 0; i < N; i++)
        scanf("%d", &arr[i]);
    init(1, 0, N - 1);
    M += K;
    while(M--){
        scanf("%d %d %d", &a, &b, &c);
        if(a == 1){
            arr[b - 1] = c;
            update(1, 0, N - 1, b - 1, c);
        }
        else{
            low = b < c ? b : c;
            high = b > c ? b : c;
            printf("%d\n", mul(1, 0, N - 1, low - 1, high - 1));
        }
    }

    return 0;
}
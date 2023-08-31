#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define INF 2e9

int *tree, *arr;

int min(int a, int b){
    return a < b ? a : b;
}

int init(int node, int start, int end){
    if(start == end)
        return tree[node] = arr[start];
    int mid = (start + end) >> 1, l, r;
    l = init(node * 2, start, mid);
    r = init(node * 2 + 1, mid + 1, end);
    return tree[node] = min(l, r);
}

int query(int node, int start, int end, int left, int right){
    if(left > end || right < start)
        return INF;
    if(left <= start && right >= end)
        return tree[node];
    int mid = (start + end) >> 1, l, r;
    l = query(node * 2, start, mid, left, right);
    r = query(node * 2 + 1, mid + 1, end, left, right);
    return min(l, r);
}

int main(){
    int N, M, a, b, i;
    scanf("%d %d", &N, &M);
    tree = (int*)calloc(1 << ((int)ceil(log2(N)) + 1), sizeof(int));
    arr = (int*)malloc(sizeof(int) * N);
    for(i = 0; i < N; i++)
        scanf("%d", &arr[i]);
    init(1, 0, N - 1);
    while(M--){
        scanf("%d %d", &a, &b);
        printf("%d\n", query(1, 0, N - 1, a - 1, b - 1));
    }

    return 0;
}
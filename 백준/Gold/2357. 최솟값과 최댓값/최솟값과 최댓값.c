#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define INF 1 << 30

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
    if(start == end)
        return tree[node] = (Pair){arr[start], arr[start]};
    Pair l, r;
    int mid = (start + end) >> 1;
    l = init(node * 2, start, mid);
    r = init(node * 2 + 1, mid + 1, end);
    return tree[node] = (Pair){min(l.f, r.f), max(l.s, r.s)};
}

Pair query(int node, int start, int end, int left, int right){
    if(left > end || right < start)
        return (Pair){INF, 0};
    if(left <= start && right >= end)
        return tree[node];
    Pair l, r;
    int mid = (start + end) >> 1;
    l = query(node * 2, start, mid, left, right);
    r = query(node * 2 + 1, mid + 1, end, left, right);
    return (Pair){min(l.f, r.f), max(l.s, r.s)};
}

int main(){
    Pair res;
    int N, M, i, a, b;
    scanf("%d %d", &N, &M);
    tree = (Pair*)calloc(1 << ((int)ceil(log2(N)) + 1), sizeof(Pair));
    arr = (int*)malloc(sizeof(int) * N);
    for(i = 0; i < N; i++)
        scanf("%d", &arr[i]);
    init(1, 0, N - 1);
    while(M--){
        scanf("%d %d", &a, &b);
        res = query(1, 0, N - 1, a - 1, b - 1);
        printf("%d %d\n", res.f, res.s);
    }

    return 0;
}
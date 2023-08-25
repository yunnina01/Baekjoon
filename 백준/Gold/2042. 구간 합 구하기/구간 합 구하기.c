#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define ll long long

ll *tree, *arr;

ll init(int node, int start, int end){
    if(start == end)
        return tree[node] = arr[start];
    int mid = (start + end) / 2;
    return tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
}

ll sum(int node, int start, int end, int left, int right){
    if(left > end || right < start)
        return 0;
    if(left <= start && right >= end)
        return tree[node];
    int mid = (start + end) / 2;
    return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);
}

void update(int node, int start, int end, int idx, ll dif){
    if(idx < start || idx > end)
        return;
    tree[node] += dif;
    if(start == end)
        return;
    int mid = (start + end) / 2;
    update(node * 2, start, mid, idx, dif);
    update(node * 2 + 1, mid + 1, end, idx, dif);
}

int main(){
    int N, M, K, a, b, i;
    ll c, dif;
    scanf("%d %d %d", &N, &M, &K);
    tree = (ll*)calloc(1 << ((int)ceil(log2(N)) + 1), sizeof(ll));
    arr = (ll*)malloc(sizeof(ll) * N);
    for(i = 0; i < N; i++)
        scanf("%lld", &arr[i]);
    init(1, 0, N - 1);
    M += K;
    while(M--){
        scanf("%d %d %lld", &a, &b, &c);
        if(a == 1){
            dif = c - arr[b - 1];
            arr[b - 1] = c;
            update(1, 0, N - 1, b - 1, dif);
        }
        else
            printf("%lld\n", sum(1, 0, N - 1, b - 1, c - 1));
    }

    return 0;
}
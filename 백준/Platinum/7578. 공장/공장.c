#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define ll long long

ll *tree, res;
int A[500000], B[1000001];

void update(int node, int start, int end, int idx){
    if(idx < start || idx > end)
        return;
    tree[node]++;
    if(start != end){
        int mid = (start + end) >> 1;
        update(node * 2, start, mid, idx);
        update(node * 2 + 1, mid + 1, end, idx);
    }
}

ll sum(int node, int start, int end, int left, int right){
    if(left > end || right < start)
        return 0;
    if(left <= start && right >= end)
        return tree[node];
    int mid = (start + end) >> 1;
    return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);
}

int main(){
    int N, i, n;
    scanf("%d", &N);
    tree = (ll*)calloc(1 << ((int)ceil(log2(N)) + 1), sizeof(ll));
    for(i = 0; i < N; i++)
        scanf("%d", &A[i]);
    for(i = 1; i <= N; i++){
        scanf("%d", &n);
        B[n] = i;
    }
    for(i = 0; i < N; i++){
        n = B[A[i]];
        res += sum(1, 1, N, n + 1, N);
        update(1, 1, N, n);
    }
    printf("%lld\n", res);

    return 0;
}
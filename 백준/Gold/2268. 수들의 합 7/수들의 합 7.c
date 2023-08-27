#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define ll long long

ll *tree;
int *arr;

void update(int node, int start, int end, int idx, int val){
    if(idx > end || idx < start)
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
    int N, M, op, i, j, k;
    scanf("%d %d", &N, &M);
    tree = (ll*)calloc(1 << ((int)ceil(log2(N)) + 1), sizeof(ll));
    arr = (int*)calloc(N, sizeof(int));
    while(M--){
        scanf("%d %d %d", &op, &i, &j);
        if(op){
            k = j - arr[i - 1];
            arr[i - 1] = j;
            update(1, 0, N - 1, i - 1, k);
        }
        else{
            if(i > j){
                k = i;
                i = j;
                j = k;
            }
            printf("%lld\n", query(1, 0, N - 1, i - 1, j - 1));
        }
    }

    return 0;
}
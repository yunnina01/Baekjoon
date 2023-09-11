#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define ll long long

ll *tree;
int *arr;

void update(int node, int start, int end, int idx, int val){
    if(idx < start || idx > end)
        return;
    tree[node] += val;
    if(start != end){
        int mid = (start + end) >> 1;
        update(node * 2, start, mid, idx, val);
        update(node * 2 + 1, mid + 1, end, idx, val);
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
    int t, n, m, i, max;
    scanf("%d", &t);
    while(t--){
        scanf("%d %d", &n, &m);
        max = n + m;
        tree = (ll*)calloc(1 << ((int)ceil(log2(max)) + 1), sizeof(ll));
        arr = (int*)calloc(max + 1, sizeof(int));
        for(i = 1; i <= n; i++){
            update(1, 1, max, m + i, 1);
            arr[i] = m + i;
        }
        while(m){
            scanf("%d", &n);
            printf("%lld ", sum(1, 1, max, 1, arr[n] - 1));
            update(1, 1, max, arr[n], -1);
            arr[n] = m--;
            update(1, 1, max, arr[n], 1);
        }
        puts("");
    }

    return 0;
}
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define MAX 1e6 + 1

int *tree;

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

int query(int node, int start, int end, int idx){
    tree[node]--;
    if(start == end)
        return start;
    int mid = (start + end) >> 1;
    if(idx <= tree[node * 2])
        return query(node * 2, start, mid, idx);
    return query(node * 2 + 1, mid + 1, end, idx - tree[node * 2]);
}

int main(){
    int n, A, B, C;
    scanf("%d", &n);
    tree = (int*)calloc(1 << ((int)ceil(log2(MAX)) + 1), sizeof(int));
    while(n--){
        scanf("%d %d", &A, &B);
        if(A == 1)
            printf("%d\n", query(1, 1, MAX, B));
        else{
            scanf("%d", &C);
            update(1, 1, MAX, B, C);
        }
    }

    return 0;
}
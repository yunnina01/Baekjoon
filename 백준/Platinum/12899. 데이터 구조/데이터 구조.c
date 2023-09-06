#include <stdio.h>
#include <stdlib.h>

const int MAX = 2e6;
int *tree;

void update(int node, int start, int end, int idx){
    if(idx < start || idx > end)
        return;
    tree[node]++;
    if(start == end)
        return;
    int mid = (start + end) >> 1;
    update(node * 2, start, mid, idx);
    update(node * 2 + 1, mid + 1, end, idx);
}

int query(int node, int start, int end, int x){
    tree[node]--;
    if(start == end)
        return start;
    int mid = (start + end) >> 1;
    if(tree[node * 2] >= x)
        return query(node * 2, start, mid, x);
    return query(node * 2 + 1, mid + 1, end, x - tree[node * 2]);
}

int main(){
    int N, T, X;
    scanf("%d", &N);
    tree = (int*)calloc(MAX * 4, sizeof(int));
    while(N--){
        scanf("%d %d", &T, &X);
        if(T == 1)
            update(1, 0, MAX - 1, X);
        else
            printf("%d\n", query(1, 0, MAX - 1, X));
    }

    return 0;
}
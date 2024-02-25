#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define ll long long
#define MOD 1000000007

ll *tree;

ll init(int node, int start, int end) {
    if(start == end)
        return tree[node] = 0;
    int mid = (start + end) >> 1;
    return tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
}

void update(int node, int start, int end, int idx, int diff) {
    if(idx < start || idx > end)
        return;
    tree[node] += diff;
    if(start != end) {
        int mid = (start + end) >> 1;
        update(node * 2, start, mid, idx, diff);
        update(node * 2 + 1, mid + 1, end, idx, diff);
    }
}

ll query(int node, int start, int end, int left, int right) {
    if(left > end || right < start)
        return 0;
    if(left <= start && right >= end)
        return tree[node];
    int mid = (start + end) >> 1;
    return query(node * 2, start, mid, left, right) + query(node * 2 + 1, mid + 1, end, left, right);
}

int main() {
    int N, i, j;
    scanf("%d", &N);

    ll *dp[12];
    for(i = 0; i < 12; i++)
        dp[i] = (ll*)calloc(N + 1, sizeof(ll));
    tree = (ll*)calloc(1 << ((int)ceil(log2(N)) + 1), sizeof(ll));
    int *A = (int*)calloc(N + 1, sizeof(int));
    for(i = 1; i <= N; i++)
        scanf("%d", &A[i]);

    update(1, 0, N, 0, 1);
    for(i = 1; i <= 11; i++) {
        for(j = 1; j <= N; j++) {
            update(1, 0, N, A[j], dp[i - 1][j]);
            dp[i][j] = query(1, 0, N, 0, A[j] - 1);
            dp[i][j] %= MOD;
        }
        init(1, 0, N);
    }

    ll res = 0;
    for(int i = 1; i <= N; i++) {
        res += dp[11][i];
        res %= MOD;
    }
    printf("%lld\n", res);

    return 0;
}
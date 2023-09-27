#include <stdio.h>
#define ll long long

ll dp[100000], tree[400000], res;

ll max(ll a, ll b){
    return a > b ? a : b;
}

void update(int node, int start, int end, int idx, ll val){
    if(idx < start || idx > end)
        return;
    tree[node] = val;
    if(start != end){
        int mid = (start + end) >> 1;
        update(node * 2, start, mid, idx, val);
        update(node * 2 + 1, mid + 1, end, idx, val);
        tree[node] = max(tree[node * 2], tree[node * 2 + 1]);
    }
}

ll query(int node, int start, int end, int left, int right){
    if(left > end || right < start)
        return -1e9;
    if(left <= start && right >= end)
        return tree[node];
    int mid = (start + end) >> 1;
    ll l, r;
    l = query(node * 2, start, mid, left, right);
    r = query(node * 2 + 1, mid + 1, end, left, right);
    return max(l, r);
}

int main(){
    int N, D, i;
    scanf("%d %d", &N, &D);
    for(i = 0; i < N; i++)
        scanf("%lld", &dp[i]);
    for(i = 0; i < N; i++){
        dp[i] = max(dp[i], query(1, 0, N - 1, max(0, i - D), i - 1) + dp[i]);
        update(1, 0, N - 1, i, dp[i]);
    }
    res = dp[0];
    for(i = 1; i < N; i++){
        if(dp[i] > res)
            res = dp[i];
    }
    printf("%lld\n", res);

    return 0;
}
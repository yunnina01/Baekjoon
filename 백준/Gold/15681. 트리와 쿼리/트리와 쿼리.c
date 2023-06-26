#include <stdio.h>
#include <stdlib.h>

typedef struct Node{
    int u, v;
    struct Node *next;
}Node;

Node *tree[100001];
int dp[100001], visit[100001];

void insert(int u, int v){
    Node *new_node = (Node*)malloc(sizeof(Node));
    new_node->v = v;
    new_node->next = NULL;
    if(tree[u])
        new_node->next = tree[u];
    tree[u] = new_node;
}

int DFS(int x){
    if(dp[x])
        return dp[x];
    int cnt = 1;
    visit[x] = 1;
    for(Node *temp = tree[x]; temp; temp = temp->next){
        if(!visit[temp->v])
            cnt += DFS(temp->v);
    }
    dp[x] = cnt;
    return cnt;
}

int main(){
    int N, R, Q, U, V;
    scanf("%d %d %d", &N, &R, &Q);
    while(--N){
        scanf("%d %d", &U, &V);
        insert(U, V);
        insert(V, U);
    }
    dp[R] = DFS(R);
    while(Q--){
        scanf("%d", &U);
        printf("%d\n", dp[U]);
    }

    return 0;
}
#include <stdio.h>
#include <stdlib.h>
#define MAX_N 100001
#define MAX_LOG 16

typedef struct Node{
    int v, w;
    struct Node *next;
}Node;

Node *graph[MAX_N];
long long cost[MAX_N];
int parent[MAX_N][MAX_LOG + 1], depth[MAX_N];

void swap(int *a, int *b){
    int t = *a;
    *a = *b;
    *b = t;
}

void insert(int u, int v, int w){
    Node *new_node = (Node*)malloc(sizeof(Node));
    new_node->v = v;
    new_node->w = w;
    new_node->next = NULL;
    if(graph[u])
        new_node->next = graph[u];
    graph[u] = new_node;
}

void DFS(int par, int cur){
    parent[cur][0] = par;
    for(int i = 1; i <= MAX_LOG; i++)
        parent[cur][i] = parent[parent[cur][i - 1]][i - 1];
    for(Node *temp = graph[cur]; temp; temp = temp->next){
        if(temp->v != par){
            cost[temp->v] = cost[cur] + temp->w;
            depth[temp->v] = depth[cur] + 1;
            DFS(cur, temp->v);
        }
    }
}

int LCA(int u, int v){
    int i, dif;
    if(depth[u] < depth[v])
        swap(&u, &v);
    dif = depth[u] - depth[v];
    for(i = 0; dif; i++){
        if(dif & 1)
            u = parent[u][i];
        dif >>= 1;
    }
    if(u != v){
        for(i = MAX_LOG; i >= 0; i--){
            if(parent[u][i] != parent[v][i]){
                u = parent[u][i];
                v = parent[v][i];
            }
        }
        u = parent[u][0];
    }
    return u;
}

int get_kth(int u, int v, int k){
    int i, lca, du, dv;
    lca = LCA(u, v);
    du = depth[u] - depth[lca];
    dv = depth[v] - depth[lca];
    if(k == du)
        return lca;
    if(k > du){
        k = du + dv - k;
        swap(&u, &v);
    }
    for(i = 0; k; i++){
        if(k & 1)
            u = parent[u][i];
        k >>= 1;
    }
    return u;
}

int main(){
    int N, M, u, v, k, op, i, j;
    scanf("%d", &N);
    for(i = 1; i < N; i++){
        scanf("%d %d %d", &u, &v, &k);
        insert(u, v, k);
        insert(v, u, k);
    }
    DFS(0, 1);
    scanf("%d", &M);
    while(M--){
        scanf("%d %d %d", &op, &u, &v);
        if(op == 1)
            printf("%lld\n", cost[u] + cost[v] - 2 * cost[LCA(u, v)]);
        else{
            scanf("%d", &k);
            printf("%d\n", get_kth(u, v, k - 1));
        }
    }
    
    return 0;
}
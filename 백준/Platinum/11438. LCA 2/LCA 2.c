#include <stdio.h>
#include <stdlib.h>

typedef struct Node{
    int v;
    struct Node *next;
}Node;

Node *graph[100001];
int parent[100001][18], depth[100001], cnt;

void insert(int u, int v){
    Node *new_node = (Node*)malloc(sizeof(Node));
    new_node->v = v;
    new_node->next = NULL;
    if(graph[u])
        new_node->next = graph[u];
    graph[u] = new_node;
}

void DFS(int par, int cur, int dep){
    if(!graph[cur])
        return;
    parent[cur][0] = par;
    depth[cur] = dep;
    for(Node *temp = graph[cur]; temp; temp = temp->next){
        if(temp->v != par)
            DFS(cur, temp->v, dep + 1);
    }
}

int LCA(int a, int b){
    int i, dif;
    if(depth[a] < depth[b]){
        i = a;
        a = b;
        b = i;
    }
    dif = depth[a] - depth[b];
    for(i = 0; dif; i++){
        if(dif % 2)
            a = parent[a][i];
        dif >>= 1;
    }
    if(a != b){
        for(i = cnt; i >= 0; i--){
            if(parent[a][i] && parent[a][i] != parent[b][i]){
                a = parent[a][i];
                b = parent[b][i];
            }
        }
        a = parent[a][0];
    }
    return a;
}

int main(){
    int N, M, i, j, u, v;
    scanf("%d", &N);
    for(i = 1; i < N; i++){
        scanf("%d %d", &u, &v);
        insert(u, v);
        insert(v, u);
    }
    DFS(0, 1, 0);
    i = N;
    while(i){
        i >>= 1;
        cnt++;
    }
    for(i = 1; i <= cnt; i++){
        for(j = 2; j <= N; j++){
            if(parent[j][i - 1])
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
        }
    }
    scanf("%d", &M);
    while(M--){
        scanf("%d %d", &u, &v);
        printf("%d\n", LCA(u, v));
    }

    return 0;
}
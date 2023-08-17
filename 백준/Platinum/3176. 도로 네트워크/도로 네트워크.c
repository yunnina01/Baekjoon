#include <stdio.h>
#include <stdlib.h>

typedef struct Node{
    int v, w;
    struct Node *next;
}Node;

Node *graph[100001];
int parent[100001][20], depth[100001], res[2];
int min_road[100001][20], max_road[100001][20];

int min(int a, int b){
    return a < b ? a : b;
}

int max(int a, int b){
    return a > b ? a : b;
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

void DFS(int par, int cur, int dep, int len){
    depth[cur] = dep;
    parent[cur][0] = par;
    min_road[cur][0] = max_road[cur][0] = len;
    for(Node *temp = graph[cur]; temp; temp = temp->next){
        if(temp->v != par)
            DFS(cur, temp->v, dep + 1, temp->w);
    }
}

void LHCA(int a, int b){
    int i, dif;
    res[0] = 1000001, res[1] = 0;
    if(depth[a] < depth[b]){
        i = a;
        a = b;
        b = i;
    }
    dif = depth[a] - depth[b];
    for(i = 0; dif; i++){
        if(dif % 2){
            res[0] = min(res[0], min_road[a][i]);
            res[1] = max(res[1], max_road[a][i]);
            a = parent[a][i];
        }
        dif >>= 1;
    }
    if(a != b){
        for(i = 19; i >= 0; i--){
            if(parent[a][i] && parent[a][i] != parent[b][i]){
                res[0] = min(res[0], min_road[a][i]);
                res[0] = min(res[0], min_road[b][i]);
                res[1] = max(res[1], max_road[a][i]);
                res[1] = max(res[1], max_road[b][i]);
                a = parent[a][i];
                b = parent[b][i];
            }
        }
        res[0] = min(res[0], min_road[a][0]);
        res[0] = min(res[0], min_road[b][0]);
        res[1] = max(res[1], max_road[a][0]);
        res[1] = max(res[1], max_road[b][0]);
    }
}

int main(){
    int N, K, A, B, C, D, E, i, j;
    scanf("%d", &N);
    for(i = 1; i < N; i++){
        scanf("%d %d %d", &A, &B, &C);
        insert(A, B, C);
        insert(B, A, C);
    }
    for(i = 0; i < 100001; i++){
        for(j = 0; j < 20; j++)
            min_road[i][j] = 1000001;
    }
    DFS(0, 1, 0, 0);
    for(i = 1; i < 20; i++){
        for(j = 2; j <= N; j++){
            if(parent[j][i - 1]){
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
                min_road[j][i] = min(min_road[parent[j][i - 1]][i - 1], min_road[j][i - 1]);
                max_road[j][i] = max(max_road[parent[j][i - 1]][i - 1], max_road[j][i - 1]);
            }
        }
    }
    scanf("%d", &K);
    while(K--){
        scanf("%d %d", &D, &E);
        LHCA(D, E);
        printf("%d %d\n", res[0], res[1]);
    }

    return 0;
}
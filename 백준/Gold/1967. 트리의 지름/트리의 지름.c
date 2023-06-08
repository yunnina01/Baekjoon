#include <stdio.h>
#include <stdlib.h>

typedef struct Node{
    int v, w;
    struct Node *next;
}Node;

Node *graph[10001];
int visit[10001], max, max_node;

void insert(int u, int v, int w){
    Node *new_node = (Node*)malloc(sizeof(Node));
    new_node->v = v, new_node->w = w;
    new_node->next = NULL;
    if(graph[u])
        new_node->next = graph[u];
    graph[u] = new_node;
}

void DFS(int V, int dis){
    if(visit[V])
        return;
    if(dis > max){
        max = dis;
        max_node = V;
    }
    visit[V] = 1;
    for(Node *temp = graph[V]; temp; temp = temp->next)
        DFS(temp->v, dis + temp->w);
    visit[V] = 0;
}

int main(){
    int n, i, u, v, w;
    scanf("%d", &n);
    for(i = 1; i < n; i++){
        scanf("%d %d %d", &u, &v, &w);
        insert(u, v, w);
        insert(v, u, w);
    }
    DFS(1, 0);
    max = 0;
    DFS(max_node, 0);
    printf("%d\n", max);

    return 0;
}
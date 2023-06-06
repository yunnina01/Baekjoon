#include <stdio.h>
#include <stdlib.h>

typedef struct Node{
    int v;
    struct Node *next;
}Node;

Node *graph[100001];
int parent[100001];

void insert(int u, int v){
    Node *new_node = (Node*)malloc(sizeof(Node));
    new_node->v = v;
    new_node->next = NULL;
    if(graph[u])
        new_node->next = graph[u];
    graph[u] = new_node;
}

void DFS(int V){
    for(Node *temp = graph[V]; temp; temp = temp->next){
        if(!parent[temp->v]){
            parent[temp->v] = V;
            DFS(temp->v);
        }
    }
}

int main(){
    int N, i, u, v;
    scanf("%d", &N);
    for(i = 1; i < N; i++){
        scanf("%d %d", &u, &v);
        insert(u, v);
        insert(v, u);
    }
    DFS(1);
    for(i = 2; i <= N; i++)
        printf("%d\n", parent[i]);

    return 0;
}
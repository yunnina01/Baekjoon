#include <stdio.h>
#include <stdlib.h>

typedef struct Node{
    int v;
    struct Node *next;
}Node;

Node *graph[20001];
int visit[20001];

void init(int V){
    for(int i = 1; i <= V; i++){
        graph[i] = NULL;
        visit[i] = 0;
    }
}

void insert(int u, int v){
    Node *new_node = (Node*)malloc(sizeof(Node));
    new_node->v = v;
    new_node->next = NULL;
    if(graph[u])
        new_node->next = graph[u];
    graph[u] = new_node;
}

void DFS(int V){
    if(!visit[V])
        visit[V] = 1;
    for(Node *temp = graph[V]; temp; temp = temp->next){
        if(!visit[temp->v]){
            if(visit[V] == 1)
                visit[temp->v] = 2;
            else
                visit[temp->v] = 1;
            DFS(temp->v);
        }
    }
}

int check(int V){
    for(int i = 1; i <= V; i++){
        for(Node *temp = graph[i]; temp; temp = temp->next){
            if(visit[temp->v] == visit[i])
                return 0;
        }
    }
    return 1;
}

int main(){
    int K, V, E, i, u, v;
    scanf("%d", &K);
    while(K--){
        scanf("%d %d", &V, &E);
        init(V);
        while(E--){
            scanf("%d %d", &u, &v);
            insert(u, v);
            insert(v, u);
        }
        for(i = 1; i <= V; i++){
            if(!visit[i])
                DFS(i);
        }
        if(check(V))
            puts("YES");
        else
            puts("NO");
    }

    return 0;
}
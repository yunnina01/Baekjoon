#include <stdio.h>
#include <stdlib.h>

typedef struct Node{
    int v;
    struct Node *next;
}Node;

Node *graph[501];
int visit[501], T, check;

void insert(int u, int v){
    Node *new_node = (Node*)malloc(sizeof(Node));
    new_node->v = v;
    new_node->next = NULL;
    if(graph[u])
        new_node->next = graph[u];
    graph[u] = new_node;
}

void DFS(int V, int prev){
    if(visit[V]){
        check = 0;
        return;
    }
    visit[V] = check = 1;
    for(Node *temp = graph[V]; temp; temp = temp->next){
        if(temp->v != prev)
            DFS(temp->v, V);
    }
}

int main(){
    int i, n, m, u, v, t = 1;
    while(1){
        scanf("%d %d", &n, &m);
        if(!n && !m)
            break;
        while(m--){
            scanf("%d %d", &u, &v);
            insert(u, v);
            insert(v, u);
        }
        for(i = 1; i <= n; i++){
            DFS(i, 0);
            if(check)
                T++;
        }
        printf("Case %d: ", t++);
        if(!T)
            puts("No trees.");
        else if(T == 1)
            puts("There is one tree.");
        else
            printf("A forest of %d trees.\n", T);
        for(i = 1; i <= n; i++){
            graph[i] = NULL;
            visit[i] = 0;
        }
        T = 0;
    }

    return 0;
}
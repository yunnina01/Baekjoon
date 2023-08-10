#include <stdio.h>
#include <stdlib.h>

typedef struct Node{
    int v;
    struct Node *next;
}Node;

Node *graph[1001];
int seq[1000], degree[1001], res[1000], idx;
int queue[1000], front, rear;

void insert(int u, int v){
    Node *new_node = (Node*)malloc(sizeof(Node));
    new_node->v = v;
    new_node->next = NULL;
    if(graph[u])
        new_node->next = graph[u];
    graph[u] = new_node;
}

int main(){
    int N, M, i, n, a;
    scanf("%d %d", &N, &M);
    while(M--){
        scanf("%d", &n);
        for(i = 0; i < n; i++)
            scanf("%d", &seq[i]);
        for(i = 1; i < n; i++){
            insert(seq[i - 1], seq[i]);
            degree[seq[i]]++;
        }
    }
    for(i = 1; i <= N; i++){
        if(!degree[i])
            queue[rear++] = i;
    }
    while(front < rear){
        a = queue[front++];
        res[idx++] = a;
        for(Node *temp = graph[a]; temp; temp = temp->next){
            if(--degree[temp->v] == 0)
                queue[rear++] = temp->v;
        }
    }
    if(idx < N)
        puts("0");
    else{
        for(i = 0; i < N; i++)
            printf("%d\n", res[i]);
    }

    return 0;
}
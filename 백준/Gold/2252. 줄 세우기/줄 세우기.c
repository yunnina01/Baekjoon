#include <stdio.h>
#include <stdlib.h>

typedef struct Node{
    int v;
    struct Node *next;
}Node;

Node *graph[32001];
int degree[32001], queue[32000], front, rear;

void insert(int u, int v){
    Node *new_node = (Node*)malloc(sizeof(Node));
    new_node->v = v;
    new_node->next = NULL;
    if(graph[u])
        new_node->next = graph[u];
    graph[u] = new_node;
}

int main(){
    int N, M, A, B, i;
    scanf("%d %d", &N, &M);
    while(M--){
        scanf("%d %d", &A, &B);
        insert(A, B);
        degree[B]++;
    }
    for(i = 1; i <= N; i++){
        if(!degree[i])
            queue[rear++] = i;
    }
    while(front < rear){
        A = queue[front++];
        printf("%d ", A);
        for(Node *temp = graph[A]; temp; temp = temp->next){
            if(--degree[temp->v] == 0)
                queue[rear++] = temp->v;
        }
    }

    return 0;
}
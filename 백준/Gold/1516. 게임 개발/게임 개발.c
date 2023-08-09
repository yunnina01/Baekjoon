#include <stdio.h>
#include <stdlib.h>

typedef struct Node{
    int v;
    struct Node *next;
}Node;

Node *graph[501];
int time[501], degree[501], res[501];
int queue[500], front, rear;

void insert(int u, int v){
    Node *new_node = (Node*)malloc(sizeof(Node));
    new_node->v = v;
    new_node->next = NULL;
    if(graph[u])
        new_node->next = graph[u];
    graph[u] = new_node;
}

int main(){
    int N, i, a;
    scanf("%d", &N);
    for(i = 1; i <= N; i++){
        scanf("%d", &time[i]);
        while(1){
            scanf("%d", &a);
            if(a == -1)
                break;
            insert(a, i);
            degree[i]++;
        }
        res[i] = time[i];
    }
    for(i = 1; i <= N; i++){
        if(!degree[i])
            queue[rear++] = i;
    }
    while(front < rear){
        i = queue[front++];
        for(Node *temp = graph[i]; temp; temp = temp->next){
            a = res[i] + time[temp->v];
            if(a > res[temp->v])
                res[temp->v] = a;
            if(--degree[temp->v] == 0)
                queue[rear++] = temp->v;
        }
    }
    for(i = 1; i <= N; i++)
        printf("%d\n", res[i]);

    return 0;
}
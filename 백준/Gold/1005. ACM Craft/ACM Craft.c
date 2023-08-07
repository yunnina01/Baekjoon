#include <stdio.h>
#include <stdlib.h>

typedef struct Node{
    int v;
    struct Node *next;
}Node;

Node *graph[1001];
int D[1001], degree[1001], res[1001];
int queue[1000], front, rear;

void insert(int x, int y){
    Node *new_node = (Node*)malloc(sizeof(Node));
    new_node->v = y;
    new_node->next = NULL;
    if(graph[x])
        new_node->next = graph[x];
    graph[x] = new_node;
}

int main(){
    int T, N, K, X, Y, W, i;
    scanf("%d", &T);
    while(T--){
        scanf("%d %d", &N, &K);
        for(i = 1; i <= N; i++){
            scanf("%d", &D[i]);
            res[i] = D[i];
            graph[i] = NULL;
            degree[i] = 0;
        }
        front = rear = 0;
        while(K--){
            scanf("%d %d", &X, &Y);
            insert(X, Y);
            degree[Y]++;
        }
        scanf("%d", &W);
        for(i = 1; i <= N; i++){
            if(!degree[i])
                queue[rear++] = i;
        }
        while(front < rear){
            X = queue[front++];
            for(Node *temp = graph[X]; temp; temp = temp->next){
                Y = res[X] + D[temp->v];
                if(Y > res[temp->v])
                    res[temp->v] = Y;
                if(--degree[temp->v] == 0)
                    queue[rear++] = temp->v;
            }
        }
        printf("%d\n", res[W]);
    }

    return 0;
}
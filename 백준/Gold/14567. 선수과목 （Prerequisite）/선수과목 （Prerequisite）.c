#include <stdio.h>
#include <stdlib.h>

typedef struct Node{
    int v;
    struct Node *next;
}Node;

Node *graph[1001];
int degree[1001], res[1001], queue[1000], front, rear;

void insert(int a, int b){
    Node *new_node = (Node*)malloc(sizeof(Node));
    new_node->v = b;
    new_node->next = NULL;
    if(graph[a])
        new_node->next = graph[a];
    graph[a] = new_node;
}

int main(){
    int N, M, a, b, i;
    scanf("%d %d", &N, &M);
    while(M--){
        scanf("%d %d", &a, &b);
        insert(a, b);
        degree[b]++;
    }
    for(i = 1; i <= N; i++){
        if(!degree[i])
            queue[rear++] = i;
        res[i] = 1;
    }
    while(front < rear){
        a = queue[front++];
        for(Node *temp = graph[a]; temp; temp = temp->next){
            if(--degree[temp->v] == 0){
                queue[rear++] = temp->v;
                if(res[a] >= res[temp->v])
                    res[temp->v] = res[a] + 1;
            }
        }
    }
    for(i = 1; i <= N; i++)
        printf("%d ", res[i]);

    return 0;
}
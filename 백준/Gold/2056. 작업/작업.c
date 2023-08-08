#include <stdio.h>
#include <stdlib.h>

typedef struct Node{
    int v;
    struct Node *next;
}Node;

Node *graph[10001];
int T[10001], degree[10001], dp[10001], res;
int queue[10000], front, rear;

void insert(int u, int v){
    Node *new_node = (Node*)malloc(sizeof(Node));
    new_node->v = v;
    new_node->next = NULL;
    if(graph[u])
        new_node->next = graph[u];
    graph[u] = new_node;
}

int main(){
    int N, i, c, v;
    scanf("%d", &N);
    for(i = 1; i <= N; i++){
        scanf("%d %d", &T[i], &c);
        while(c--){
            scanf("%d", &v);
            insert(i, v);
            degree[v]++;
        }
        dp[i] = T[i];
    }
    for(i = 1; i <= N; i++){
        if(!degree[i])
            queue[rear++] = i;
    }
    while(front < rear){
        c = queue[front++];
        for(Node *temp = graph[c]; temp; temp = temp->next){
            v = dp[c] + T[temp->v];
            if(v > dp[temp->v])
                dp[temp->v] = v;
            if(--degree[temp->v] == 0)
                queue[rear++] = temp->v;
        }
    }
    for(i = 1; i <= N; i++){
        if(dp[i] > res)
            res = dp[i];
    }
    printf("%d\n", res);

    return 0;
}
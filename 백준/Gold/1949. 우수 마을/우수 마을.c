#include <stdio.h>
#include <stdlib.h>

typedef struct Node{
    int v;
    struct Node *next;
}Node;

Node *graph[10001];
int dp[10001][2], people[10001], visit[10001];

int max(int a, int b){
    return  a > b ? a : b;
}

void insert(int u, int v){
    Node *new_node = (Node*)malloc(sizeof(Node));
    new_node->v = v;
    new_node->next = NULL;
    if(graph[u]);
        new_node->next = graph[u];
    graph[u] = new_node;
}

void DFS(int x){
    dp[x][1] = people[x];
    visit[x] = 1;
    for(Node *temp = graph[x]; temp; temp = temp->next){
        int next = temp->v;
        if(!visit[next]){
            DFS(next);
            dp[x][0] += max(dp[next][0], dp[next][1]);
            dp[x][1] += dp[next][0];
        }
    }
}

int main(){
    int N, i, u, v;
    scanf("%d", &N);
    for(i = 1; i <= N; i++)
        scanf("%d", &people[i]);
    for(i = 1; i < N; i++){
        scanf("%d %d", &u, &v);
        insert(u, v);
        insert(v, u);
    }
    DFS(1);
    printf("%d\n", max(dp[1][0], dp[1][1]));

    return 0;
}
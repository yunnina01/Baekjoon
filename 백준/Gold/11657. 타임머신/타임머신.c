#include <stdio.h>
#include <stdlib.h>
#define INF 1 << 30

typedef struct Node{
    int v, w;
    struct Node *next;
}Node;

Node *graph[501];
long long dis[501];

void insert(int u, int v, int w){
    Node *new_node = (Node*)malloc(sizeof(Node));
    new_node->v = v, new_node->w = w;
    new_node->next = NULL;
    if(graph[u])
        new_node->next = graph[u];
    graph[u] = new_node;
}

int main(){
    int N, M, A, B, C, i, j, check = 0;
    scanf("%d %d", &N, &M);
    while(M--){
        scanf("%d %d %d", &A, &B, &C);
        insert(A, B, C);
    }
    for(i = 2; i <= N; i++)
        dis[i] = INF;
    for(i = 1; i <= N; i++){
        for(j = 1; j <= N; j++){
            for(Node *temp = graph[j]; temp; temp = temp->next){
                if(dis[j] != INF && dis[temp->v] > dis[j] + temp->w){
                    dis[temp->v] = dis[j] + temp->w;
                    if(i == N)
                        check = 1;
                }
            }
        }
    }
    if(check)
        puts("-1");
    else{
        for(i = 2; i <= N; i++)
            printf("%d\n", dis[i] != INF ? dis[i] : -1);
    }

    return 0;
}
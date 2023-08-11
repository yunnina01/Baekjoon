#include <stdio.h>
#include <stdlib.h>

typedef struct Node{
    int v, w;
    struct Node *next;
}Node;

Node *graph[101];
int degree[101], cnt[101], part[100], idx;
int queue[100], front, rear;

int asc(const void *a, const void *b){
    return *(int*)a - *(int*)b;
}

void insert(int u, int v, int w){
    Node *new_node = (Node*)malloc(sizeof(Node));
    new_node->v = v;
    new_node->w = w;
    new_node->next = NULL;
    if(graph[u])
        new_node->next = graph[u];
    graph[u] = new_node;
}

int main(){
    int N, M, X, Y, K, i;
    scanf("%d %d", &N, &M);
    while(M--){
        scanf("%d %d %d", &X, &Y, &K);
        insert(X, Y, K);
        degree[Y]++;
    }
    queue[rear++] = N;
    cnt[N] = 1;
    while(front < rear){
        X = queue[front++];
        if(!graph[X])
            part[idx++] = X;
        else{
            for(Node *temp = graph[X]; temp; temp = temp->next){
                cnt[temp->v] += cnt[X] * temp->w;
                if(--degree[temp->v] == 0)
                    queue[rear++] = temp->v;
            }
        }
    }
    qsort(part, idx, sizeof(int), asc);
    for(i = 0; i < idx; i++)
        printf("%d %d\n", part[i], cnt[part[i]]);

    return 0;
}
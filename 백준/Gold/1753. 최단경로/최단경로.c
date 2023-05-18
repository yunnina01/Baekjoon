#include <stdio.h>
#include <stdlib.h>
#define INF 1 << 30

typedef struct Node{
    int v, w;
    struct Node *next;
}Node;

typedef struct{
    int v, dis;
}Pair;

Node *graph[20001];
Pair heap[300001];
int dis[20001], size;

int min(int a, int b){
    return a < b ? a : b;
}

void insert(int u, int v, int w){
    Node *new_node = (Node*)malloc(sizeof(Node));
    new_node->v = v, new_node->w = w;
    new_node->next = NULL;
    if(graph[u])
        new_node->next = graph[u];
    graph[u] = new_node;
}

void push(Pair item){
    int i = ++size;
    while(i != 1 && item.dis < heap[i / 2].dis){
        heap[i] = heap[i / 2];
        i /= 2;
    }
    heap[i] = item;
}

Pair pop(){
    int parent = 1, child = 2;
    Pair min = heap[1], temp = heap[size--];
    while(child <= size){
        if(child < size && heap[child].dis > heap[child + 1].dis)
            child++;
        if(temp.dis <= heap[child].dis)
            break;
        heap[parent] = heap[child];
        parent = child;
        child *= 2;
    }
    heap[parent] = temp;
    return min;
}

int main(){
    int V, E, K, u, v, w, i;
    scanf("%d %d %d", &V, &E, &K);
    for(i = 1; i <= V; i++)
        dis[i] = INF;
    dis[K] = 0;
    while(E--){
        scanf("%d %d %d", &u, &v, &w);
        insert(u, v, w);
    }
    push((Pair){K, 0});
    while(size){
        Pair cur = pop();;
        for(Node *temp = graph[cur.v]; temp; temp = temp->next){
            v = temp->v, w = cur.dis + temp->w;
            if(w < dis[v]){
                dis[v] = w;
                push((Pair){v, w});
            }
        }
    }
    for(i = 1; i <= V; i++){
        if(dis[i] == INF)
            puts("INF");
        else
            printf("%d\n", dis[i]);
    }

    return 0;
}
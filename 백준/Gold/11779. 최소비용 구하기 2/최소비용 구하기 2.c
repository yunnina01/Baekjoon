#include <stdio.h>
#include <stdlib.h>
#define INF 1 << 30

typedef struct Node{
    int v, w;
    struct Node *next;
}Node;

typedef struct{
    int v, w;
}IDX;

Node *graph[1001];
IDX heap[100001];
int dis[1001], route[1001], res[1000], size, cnt;

void insert(int u, int v, int w){
    Node *new_node = (Node*)malloc(sizeof(Node));
    new_node->v = v, new_node->w = w;
    new_node->next = NULL;
    if(graph[u])
        new_node->next = graph[u];
    graph[u] = new_node;
}

void push(IDX item){
    int i = ++size;
    while(i != 1 && item.w < heap[i / 2].w){
        heap[i] = heap[i / 2];
        i /= 2;
    }
    heap[i] = item;
}

IDX pop(){
    int parent = 1, child = 2;
    IDX min = heap[1], temp = heap[size--];
    while(child <= size){
        if(child < size && heap[child].w > heap[child + 1].w)
            child++;
        if(temp.w <= heap[child].w)
            break;
        heap[parent] = heap[child];
        parent = child;
        child *= 2;
    }
    heap[parent] = temp;
    return min;
}

void dijkstra(int start, int end){
    push((IDX){start, 0});
    dis[start] = 0;
    while(size){
        IDX cur = pop();
        for(Node *temp = graph[cur.v]; temp; temp = temp->next){
            if(dis[temp->v] > -cur.w + temp->w){
                route[temp->v] = cur.v;
                dis[temp->v] = -cur.w + temp->w;
                push((IDX){temp->v, -dis[temp->v]});
            }
        }
    }
}

int main(){
    int n, m, u, v, w, i;
    scanf("%d %d", &n, &m);
    for(i = 0; i < m; i++){
        scanf("%d %d %d", &u, &v, &w);
        insert(u, v, w);
    }
    scanf("%d %d", &u, &v);
    for(i = 1; i <= n; i++)
        dis[i] = INF;
    dijkstra(u, v);
    for(i = v; i; i = route[i])
        res[cnt++] = i;
    printf("%d\n%d\n", dis[v], cnt);
    while(cnt--)
        printf("%d ", res[cnt]);

    return 0;
}
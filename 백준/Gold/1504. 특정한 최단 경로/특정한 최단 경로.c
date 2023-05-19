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

Node *graph[801];
Pair heap[200001];
int dis[801], N, size, res1, res2;

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

void dijkstra(int start){
    int i, v, w;
    for(i = 1; i <= N; i++)
        dis[i] = INF;
    dis[start] = size = 0;
    push((Pair){start, 0});
    while(size){
        Pair cur = pop();
        for(Node *temp = graph[cur.v]; temp; temp = temp->next){
            v = temp->v, w = cur.dis + temp->w;
            if(w < dis[v]){
                dis[v] =w;
                push((Pair){v, w});
            }
        }
    }
}

int main(){
    int E, a, b, c, v1, v2;
    scanf("%d %d", &N, &E);
    while(E--){
        scanf("%d %d %d", &a, &b, &c);
        insert(a, b, c);
        insert(b, a, c);
    }
    scanf("%d %d", &v1, &v2);
    dijkstra(1);
    a = dis[v1], b = dis[v2];
    dijkstra(v1);
    c = dis[v2];
    dijkstra(N);
    if(a == INF || c == INF || dis[v2] == INF)
        res1 = INF;
    else
        res1 = a + c + dis[v2];
    if(b == INF || c == INF || dis[v1] == INF)
        res2 = INF;
    else
        res2 = b + c + dis[v1];
    if(res1 == INF && res2 == INF)
        puts("-1");
    else
        printf("%d\n", min(res1, res2));

    return 0;
}
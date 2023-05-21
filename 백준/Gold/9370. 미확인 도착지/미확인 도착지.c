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

Node *graph[2001];
Pair heap[50001];
int S[2001], G[2001], H[2001], res[100], n, size;

int min(int a, int b){
    return a < b ? a : b;
}

int asc(const void *a, const void *b){
    return *(int*)a - *(int*)b;
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

void dijkstra(int *dis, int start){
    int i, v, w;
    for(i = 1; i <= n; i++)
        dis[i] = INF;
    dis[start] = size = 0;
    push((Pair){start, 0});
    while(size){
        Pair cur = pop();
        for(Node *temp = graph[cur.v]; temp; temp = temp->next){
            v = temp->v, w = cur.dis + temp->w;
            if(w < dis[v]){
                dis[v] = w;
                push((Pair){v, w});
            }
        }
    }
}

int main(){
    int T, m, t, s, g, h, a, b, d, i, cnt;
    scanf("%d", &T);
    while(T--){
        scanf("%d %d %d %d %d %d", &n, &m, &t, &s, &g, &h);
        for(i = 1; i <= n; i++)
            graph[i] = NULL;
        while(m--){
            scanf("%d %d %d", &a, &b, &d);
            insert(a, b, d);
            insert(b, a, d);
        }
        dijkstra(S, s);
        dijkstra(G, g);
        dijkstra(H, h);
        for(i = cnt = 0; i < t; i++){
            scanf("%d", &a);
            if(S[a] == S[g] + G[h] + H[a] || S[a] == S[h] + H[g] + G[a])
                res[cnt++] = a;
        }
        qsort(res, cnt, sizeof(int), asc);
        for(i = 0; i < cnt; i++)
            printf("%d ", res[i]);
        puts("");
    }

    return 0;
}
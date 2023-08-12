#include <stdio.h>
#include <stdlib.h>

typedef struct Node{
    int x, w;
    struct Node *next;
}Node;

typedef struct{
    int n, t;
}Pair;

Node *graph[10001], *re_graph[10001];
Pair queue[10000];
int degree[10001], time[10001], visit[10001], front, rear, cnt;

Node *get_node(int v, int w){
    Node *new_node = (Node*)malloc(sizeof(Node));
    new_node->x = v;
    new_node->w = w;
    new_node->next = NULL;
    return new_node;
}

void insert1(int u, int v, int w){
    Node *new_node = get_node(v, w);
    if(graph[u])
        new_node->next = graph[u];
    graph[u] = new_node;
}

void insert2(int u, int v, int w){
    Node *new_node = get_node(u, w);
    if(re_graph[v])
        new_node->next = re_graph[v];
    re_graph[v] = new_node;
}

void get_time(int S){
    queue[rear++] = (Pair){S, 0};
    while(front < rear){
        Pair cur = queue[front++];
        for(Node *temp = graph[cur.n]; temp; temp = temp->next){
            int val = cur.t + temp->w;
            if(val > time[temp->x])
                time[temp->x] = val;
            if(--degree[temp->x] == 0)
                queue[rear++] = (Pair){temp->x, time[temp->x]};
        }
    }
}

void get_load_cnt(int E){
    front = rear = 0;
    queue[rear++] = (Pair){E, 0};
    visit[E] = 1;
    while(front < rear){
        int cur = queue[front++].n;
        for(Node *temp = re_graph[cur]; temp; temp = temp->next){
            if(time[cur] - temp->w == time[temp->x]){
                cnt++;
                if(!visit[temp->x]){
                    visit[temp->x] = 1;
                    queue[rear++] = (Pair){temp->x, 0};
                }
            }
        }
    }
}

int main(){
    int n, m, u, v, w, s, e;
    scanf("%d %d", &n, &m);
    while(m--){
        scanf("%d %d %d", &u, &v, &w);
        insert1(u, v, w);
        insert2(u, v, w);
        degree[v]++;
    }
    scanf("%d %d", &s, &e);
    get_time(s);
    get_load_cnt(e);
    printf("%d\n%d\n", time[e], cnt);

    return 0;
}
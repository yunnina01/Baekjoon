#include <stdio.h>
#include <stdlib.h>

typedef struct Node{
    int v;
    struct Node *next;
}Node;

Node *graph[20001], *re_graph[20001];
int visit[20001], sn[20001], scc_cnt;
int stack[20000], top;

Node *get_node(int v){
    Node *new_node = (Node*)malloc(sizeof(Node));
    new_node->v = v;
    new_node->next = NULL;
    return new_node;
}

void insert(int u, int v){
    Node *new_node = get_node(v);
    if(graph[u])
        new_node->next = graph[u];
    graph[u] = new_node;
}

void re_insert(int u, int v){
    Node *new_node = get_node(v);
    if(re_graph[u])
        new_node->next = re_graph[u];
    re_graph[u] = new_node;
}

void DFS(int x){
    visit[x] = 1;
    for(Node *temp = graph[x]; temp; temp = temp->next){
        if(!visit[temp->v])
            DFS(temp->v);
    }
    stack[top++] = x;
}

void RE_DFS(int x, int num){
    visit[x] = 1;
    sn[x] = num;
    for(Node *temp = re_graph[x]; temp; temp = temp->next){
        if(!visit[temp->v])
            RE_DFS(temp->v, num);
    }
}

int trans(int x, int N){
    return x > N ? x - N : x + N;
}

int main(){
    int N, M, i, j;
    scanf("%d %d", &N, &M);
    while(M--){
        scanf("%d %d", &i, &j);
        if(i < 0)
            i = -i + N;
        if(j < 0)
            j = -j + N;
        insert(trans(i, N), j);
        insert(trans(j, N), i);
        re_insert(i, trans(j, N));
        re_insert(j, trans(i, N));
    }
    for(i = 1; i <= 2 * N; i++){
        if(!visit[i])
            DFS(i);
    }
    for(i = 1; i <= 2 * N; i++)
        visit[i] = 0;
    while(top){
        i = stack[--top];
        if(!visit[i])
            RE_DFS(i, ++scc_cnt);
    }
    for(i = 1; i <= N; i++){
        if(sn[i] == sn[i + N]){
            puts("0");
            break;
        }
    }
    if(i > N)
        puts("1");

    return 0;
}
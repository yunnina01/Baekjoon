#include <stdio.h>
#include <stdlib.h>

typedef struct Node{
    int v;
    struct Node *next;
}Node;

Node *graph[10001], *re_graph[10001];
int sn[10001], visit[10001], k, n, scc_cnt;
int stack[10000], top;

int trans(int x){
    return x > k ? x - k : x + k;
}

Node *get_node(int v){
    Node *new_node = (Node*)malloc(sizeof(Node));
    new_node->v = v;
    new_node->next = NULL;
    return new_node;
}

void adj_insert(int u, int v){
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

void insert(int u, int v){
    adj_insert(trans(u), v);
    adj_insert(trans(v), u);
    re_insert(u, trans(v));
    re_insert(v, trans(u));
}

void DFS(int x){
    visit[x] = 1;
    for(Node *temp = graph[x]; temp; temp = temp->next){
        if(!visit[temp->v])
            DFS(temp->v);
    }
    stack[top++] = x;
}

void RE_DFS(int x){
    visit[x] = 1;
    for(Node *temp = re_graph[x]; temp; temp = temp->next){
        if(!visit[temp->v])   
            RE_DFS(temp->v);
    }
    sn[x] = scc_cnt;
}

int main(){
    char a, b, c;
    int i, x, y, z;
    scanf("%d %d", &k, &n);
    while(n--){
        scanf("%d %c %d %c %d %c", &x, &a, &y, &b, &z, &c);
        if(a == 'B')
            x = trans(x);
        if(b == 'B')
            y = trans(y);
        if(c == 'B')
            z = trans(z);
        insert(x, y);
        insert(y, z);
        insert(z, x);
    }
    for(i = 1; i <= 2 * k; i++){
        if(!visit[i])
            DFS(i);
    }
    for(i = 1; i <= 2 * k; i++)
        visit[i] = 0;
    while(top){
        x = stack[--top];
        scc_cnt++;
        if(!visit[x])
            RE_DFS(x);
    }
    for(i = 1; i <= k; i++){
        if(sn[i] == sn[i + k]){
            puts("-1");
            break;
        }
    }
    if(i > k){
        for(i = 1; i <= k; i++)
            printf("%c", sn[i] > sn[i + k] ? 'R' : 'B');
    }

    return 0;
}
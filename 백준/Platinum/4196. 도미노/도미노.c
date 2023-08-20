#include <stdio.h>
#include <stdlib.h>

typedef struct Node{
    int v;
    struct Node *next;
}Node;

Node *graph[100001];
int degree[100000], sn[100001], visit[100001], v_cnt, scc_cnt, res;
int stack[100000], top;

int min(int a, int b){
    return a < b ? a : b;
}

void insert(int u, int v){
    Node *new_node = (Node*)malloc(sizeof(Node));
    new_node->v = v;
    new_node->next = NULL;
    if(graph[u])
        new_node->next = graph[u];
    graph[u] = new_node;
}

int SCC(int x){
    int ret = visit[x] = ++v_cnt;
    stack[top++] = x;
    for(Node *temp = graph[x]; temp; temp = temp->next){
        if(!visit[temp->v])
            ret = min(ret, SCC(temp->v));
        else if(!sn[temp->v])
            ret = min(ret, visit[temp->v]);
    }
    if(ret == visit[x]){
        scc_cnt++;
        while(1){
            int v = stack[--top];
            sn[v] = scc_cnt;
            if(v == x)
                break;
        }
    }
    return ret;
}

int main(){
    int T, N, M, x, y, i;
    scanf("%d", &T);
    while(T--){
        scanf("%d %d", &N, &M);
        for(i = 1; i <= N; i++){
            graph[i] = NULL;
            degree[i] = sn[i] = visit[i] = 0;
        }
        v_cnt = scc_cnt = res = 0;
        while(M--){
            scanf("%d %d", &x, &y);
            insert(x, y);
        }
        for(i = 1; i <= N; i++){
            if(!visit[i])
                SCC(i);
        }
        for(i = 1; i <= N; i++){
            for(Node *temp = graph[i]; temp; temp = temp->next){
                if(sn[temp->v] != sn[i])
                    degree[sn[temp->v]]++;
            }
        }
        for(i = 0; i < scc_cnt; i++){
            if(!degree[i])
                res++;
        }
        printf("%d\n", res);
    }

    return 0;
}
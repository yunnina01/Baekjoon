#include <stdio.h>
#include <stdlib.h>

typedef struct Node{
    int v;
    struct Node *next;
}Node;

Node *graph[2001];
int sn[2001], visit[2001], N, M, v_cnt, scc_cnt;
int stack[2000], top;

int min(int a, int b){
    return a < b ? a : b;
}

int trans(int x){
    return x > N ? x - N : x + N;
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
    int ret = visit[x] = v_cnt++;
    stack[top++] = x;
    for(Node *temp = graph[x]; temp; temp = temp->next){
        if(visit[temp->v] == -1)
            ret = min(ret, SCC(temp->v));
        else if(sn[temp->v] == -1)
            ret = min(ret, visit[temp->v]);
    }
    if(ret == visit[x]){
        while(1){
            int v = stack[--top];
            sn[v] = scc_cnt;
            if(v == x)
                break;
        }
        scc_cnt++;
    }
    return ret;
}

int main(){
    int a, b, i, chk;
    while(scanf("%d %d", &N, &M) != EOF){
        for(i = 1; i <= 2 * N; i++){
            graph[i] = NULL;
            sn[i] = visit[i] = -1;
        }
        top = v_cnt = scc_cnt = 0;
        chk = 1;
        while(M--){
            scanf("%d %d", &a, &b);
            a = a < 0 ? trans(-a) : a;
            b = b < 0 ? trans(-b) : b;
            insert(trans(a), b);
            insert(trans(b), a);
        }
        for(i = 1; i <= 2 * N; i++){
            if(visit[i] == -1)
                SCC(i);
        }
        for(i = 1; i <= N; i++){
            if(sn[i] == sn[trans(i)]){
                chk = 0;
                break;
            }
        }
        if(sn[trans(1)] < sn[1])
            chk = 0;
        printf("%s\n", chk ? "yes" : "no");
    }

    return 0;
}
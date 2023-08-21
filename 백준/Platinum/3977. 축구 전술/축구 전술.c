#include <stdio.h>
#include <stdlib.h>

typedef struct Node{
    int v;
    struct Node *next;
}Node;

Node *graph[100000];
int degree[100000], sn[100000], visit[100000], v_cnt, scc_cnt;
int stack[100000], top;
int *res[100000], size[100000];

int min(int a, int b){
    return a < b ? a : b;
}

int asc(const void *a, const void *b){
    return *(int*)a - *(int*)b;
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
        else if(sn[temp->v] == -1)
            ret= min(ret, visit[temp->v]);
    }
    if(ret == visit[x]){
        while(1){
            int v = stack[--top];
            res[scc_cnt] = (int*)realloc(res[scc_cnt], sizeof(int) * (size[scc_cnt] + 1));
            res[scc_cnt][size[scc_cnt]++] = v;
            sn[v] = scc_cnt;
            if(v == x)
                break;
        }
        scc_cnt++;
    }
    return ret;
}

int main(){
    int T, N, M, A, B, i, idx, chk;
    scanf("%d", &T);
    while(T--){
        scanf("%d %d", &N, &M);
        for(i = 0; i < N; i++){
            graph[i] = NULL;
            res[i] = NULL;
            degree[i] = visit[i] = size[i] = 0;
            sn[i] = -1;
        }
        v_cnt = scc_cnt = chk = 0;
        idx = -1;
        while(M--){
            scanf("%d %d", &A, &B);
            insert(A, B);
        }
        for(i = 0; i < N; i++){
            if(!visit[i])
                SCC(i);
        }
        for(i = 0; i < N; i++){
            for(Node *temp = graph[i]; temp; temp = temp->next){
                if(sn[temp->v] != sn[i])
                    degree[sn[temp->v]]++;
            }
        }
        for(i = 0; i < scc_cnt; i++){
            if(!degree[i]){
                if(idx == -1)
                    idx = i;
                else
                    chk = 1;
            }
        }
        if(chk)
            puts("Confused");
        else{
            qsort(res[idx], size[idx], sizeof(int), asc);
            for(i = 0; i < size[idx]; i++)
                printf("%d\n", res[idx][i]);
        }
        puts("");
    }

    return 0;
}
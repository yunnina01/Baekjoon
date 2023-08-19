#include <stdio.h>
#include <stdlib.h>

int *graph[10001], *re_graph[10001], *res[10000], cnt;
int size[10001], re_size[10001], visit[10001], idx[10000];
int stack[10000], top;

int asc(const void *a, const void *b){
    return *(int*)a - *(int*)b;
}

int arr_asc(const void *a, const void *b){
    return res[*(int*)a][0] - res[*(int*)b][0];
}

void DFS(int x){
    visit[x] = 1;
    for(int i = 0; i < size[x]; i++){
        if(!visit[graph[x][i]])
            DFS(graph[x][i]);
    }
    stack[top++] = x;
}

void RE_DFS(int x){
    visit[x] = 1;
    res[cnt] = (int*)realloc(res[cnt], sizeof(int) * (size[cnt] + 1));
    res[cnt][size[cnt]++] = x;
    for(int i = 0; i < re_size[x]; i++){
        if(!visit[re_graph[x][i]])
            RE_DFS(re_graph[x][i]);
    }
}

int main(){
    int V, E, A, B, i, j;
    scanf("%d %d", &V, &E);
    while(E--){
        scanf("%d %d", &A, &B);
        graph[A] = (int*)realloc(graph[A], sizeof(int) * (size[A] + 1));
        graph[A][size[A]++] = B;
        re_graph[B] = (int*)realloc(re_graph[B], sizeof(int) * (re_size[B] + 1));
        re_graph[B][re_size[B]++] = A;
    }
    for(i = 1; i <= V; i++){
        qsort(graph[i], size[i], sizeof(int), asc);
        qsort(re_graph[i], re_size[i], sizeof(int), asc);
    }
    for(i = 1; i <= V; i++){
        if(!visit[i])
            DFS(i);
    }
    for(i = 1; i <= V; i++)
        visit[i] = size[i] = 0;
    while(top){
        A = stack[--top];
        if(!visit[A]){
            RE_DFS(A);
            qsort(res[cnt], size[cnt], sizeof(int), asc);
            idx[cnt] = cnt;
            cnt++;
        }
    }
    qsort(idx, cnt, sizeof(int), arr_asc);
    printf("%d\n", cnt);
    for(i = 0; i < cnt; i++){
        for(j = 0; j < size[idx[i]]; j++)
            printf("%d ", res[idx[i]][j]);
        puts("-1");
    }

    return 0;
}
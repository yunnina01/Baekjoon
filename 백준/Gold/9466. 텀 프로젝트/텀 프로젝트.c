#include <stdio.h>

int student[100001], visit[100001], cycle[100001], cnt;

void DFS(int x){
    int i, next = student[x];
    visit[x] = 1;
    if(!visit[next])
        DFS(next);
    else if(!cycle[next]){
        for(i = next; i != x; i = student[i])
            cnt++;
        cnt++;
    }
    cycle[x] = 1;
}

int main(){
    int T, i, n;
    scanf("%d", &T);
    while(T--){
        scanf("%d", &n);
        for(i = 1; i <= n; i++){
            scanf("%d", &student[i]);
            visit[i] = cycle[i] = 0;
        }
        cnt = 0;
        for(i = 1; i <= n; i++){
            if(!visit[i])
                DFS(i);
        }
        printf("%d\n", n - cnt);
    }

    return 0;
}
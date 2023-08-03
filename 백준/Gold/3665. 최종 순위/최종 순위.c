#include <stdio.h>
#include <string.h>

int adj[501][501], t[500], res[500];
int degree[501], queue[501], front, rear;

int main(){
    int T, n, m, a, b, i, j;
    scanf("%d", &T);
    while(T--){
        memset(adj, 0, sizeof(adj));
        memset(degree, 0, sizeof(degree));
        scanf("%d", &n);
        for(i = 0; i < n; i++)
            scanf("%d", &t[i]);
        for(i = 0; i < n; i++){
            for(j = i + 1; j < n; j++){
                adj[t[i]][t[j]] = 1;
                degree[t[j]]++;
            }
        }
        scanf("%d", &m);
        while(m--){
            scanf("%d %d", &a, &b);
            if(adj[a][b]){
                adj[a][b] = 0;
                degree[b]--;
                adj[b][a] = 1;
                degree[a]++;
            }
            else{
                adj[b][a] = 0;
                degree[a]--;
                adj[a][b] = 1;
                degree[b]++;
            }
        }
        j = front = rear = 0;
        for(i = 1; i <= n; i++){
            if(!degree[i])
                queue[rear++] = i;
        }
        while(front < rear){
            if(rear - front >= 2)
                break;
            a = queue[front++];
            res[j++] = a;
            for(i = 1; i <= n; i++){
                if(adj[a][i]){
                    if(--degree[i] == 0)
                        queue[rear++] = i;
                }
            }
        }
        if(rear - front >= 2)
            puts("?");
        else if(j != n)
            puts("IMPOSSIBLE");
        else{
            for(i = 0; i < j; i++)
                printf("%d ", res[i]);
            puts("");
        }
    }

    return 0;
}
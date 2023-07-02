#include <stdio.h>
#include <math.h>

int graph[8], arr[8], visit[8], res;

void DFS(int cnt){
    int a, b, c, i;
    if(cnt == 8){
        for(i = 0; i < 8; i++){
            a = i, b = (i + 1) % 8, c = (i + 2) % 8;
            if(arr[a] * arr[c] * sqrt(2) > arr[b] * (arr[a] + arr[c]))
                break;
        }
        if(i == 8)
            res++;
    }
    for(i = 0; i < 8; i++){
        if(!visit[i]){
            visit[i] = 1;
            arr[cnt] = graph[i];
            DFS(cnt + 1);
            visit[i] = 0;
        }
    }
}

int main(){
    for(int i = 0; i < 8; i++)
        scanf("%d", &graph[i]);
    DFS(0);
    printf("%d\n", res);

    return 0;
}
#include <stdio.h>

int parent[200001], queue[150000], front, rear;

int main(){
    int N, K, x, cnt = 0, check = 0;
    scanf("%d %d", &N, &K);   
    parent[N] = queue[rear++] = N;
    while(1){
        int back = rear;
        while(front < back){
            x = queue[front++];
            if(x > 100000)
                continue;
            if(x == K){
                check = 1;
                break;
            }
            if(!parent[x - 1] && x){
                queue[rear++] = x - 1;
                parent[x - 1] = x;
            }
            if(!parent[x + 1] && x < K){
                queue[rear++] = x + 1;
                parent[x + 1] = x;
            }
            if(!parent[x * 2] && x < K){
                queue[rear++] = x * 2;
                parent[x * 2] = x;
            }
        }
        if(check)
            break;
        cnt++;
    }
    int res[cnt + 1], i = cnt;
    while(i){
        res[i--] = K;
        K = parent[K];
    }
    res[i] = N;
    printf("%d\n", cnt);
    for(; i <= cnt; i++)
        printf("%d ", res[i]);

    return 0;
}
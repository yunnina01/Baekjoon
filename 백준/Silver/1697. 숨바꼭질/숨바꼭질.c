#include <stdio.h>

int visit[200001], queue[150000], front, rear, check, res;

int main(){
    int N, K, x, back;
    scanf("%d %d", &N, &K);
    visit[N] = 1;
    queue[rear++] = N;
    while(1){
        back = rear;
        while(front < back){
            x = queue[front++];
            if(x > 100000)
                continue;
            if(x == K){
                check = 1;
                break;
            }
            if(!visit[x - 1] && x){
                queue[rear++] = x - 1;
                visit[x - 1] = 1;
            }
            if(!visit[x + 1] && x < K){
                queue[rear++] = x + 1;
                visit[x + 1] = 1;
            }
            if(!visit[x * 2] && x < K){
                queue[rear++] = x * 2;
                visit[x * 2] = 1;
            }
        }
        if(check)
            break;
        res++;
    }
    printf("%d\n", res);

    return 0;
}
#include <stdio.h>

int visit[200001], queue[2500000], front, rear, time, cnt;

int main(){
    int N, K, x, back;
    scanf("%d %d", &N, &K);
    queue[rear++] = N;
    while(1){
        back = rear;
        while(front < back){
            x = queue[front++];
            if(x > 100000)
                continue;
            visit[x] = 1;
            if(x == K)
                cnt++;
            else{
                if(!visit[x - 1] && x)
                    queue[rear++] = x - 1;
                if(!visit[x + 1] && x < K)
                    queue[rear++] = x + 1;
                if(!visit[x * 2] && x < K)
                    queue[rear++] = x * 2;
            }
        }
        if(cnt)
            break;
        time++;
    }
    printf("%d\n%d\n", time, cnt);

    return 0;
}
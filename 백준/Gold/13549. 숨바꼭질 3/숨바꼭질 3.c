#include <stdio.h>

typedef struct{
    int x, s;
}POS;

POS queue[110001];
int visit[110001], front, rear;

int main(){
    int N, K, i;
    scanf("%d %d", &N, &K);
    queue[rear++] = (POS){N, 0};
    visit[N] = 1;
    while(front < rear){
        POS cur = queue[front++];
        for(i = cur.x; i < 110000; i *= 2){
            if(i == K){
                printf("%d\n", cur.s);
                return 0;
            }
            if(i && !visit[i - 1]){
                queue[rear++] = ((POS){i - 1, cur.s + 1});
                visit[i - 1] = 1;
            }
            if(!visit[i + 1]){
                queue[rear++] = ((POS){i + 1, cur.s + 1});
                visit[i + 1] = 1;
            }
            if(!i)
                break;
            visit[i] = 1;
        }
    }

    return 0;
}
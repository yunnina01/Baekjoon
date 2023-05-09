#include <stdio.h>

int visit[101] = {0, 1}, move[101];
int queue[100] = {1}, front, rear = 1, cnt;

int main(){
    int N, M, i, cur, next, back, check = 0;
    scanf("%d %d", &N, &M);
    for(i = 0; i < N + M; i++){
        scanf("%d %d", &cur, &next);
        move[cur] = next;
    }
    while(1){
        back = rear, cnt++;
        while(front < back){
            cur = queue[front++];
            for(i = 1; i < 7; i++){
                if(cur + i == 100){
                    check = 1;
                    break;
                }
                if(!visit[cur + i]){
                    if(move[cur + i]){
                        visit[cur + i] = 1;
                        next = move[cur + i];
                    }
                    else
                        next = cur + i;
                    visit[next] = 1;
                    queue[rear++] = next;
                }
            }
        }
        if(check)
            break;
    }
    printf("%d\n", cnt);

    return 0;
}
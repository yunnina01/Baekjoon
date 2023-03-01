#include <stdio.h>

int front, rear;

int main(){
    int N, K, i = 1;
    scanf("%d %d", &N, &K);
    int queue[N * N];
    while(i <= N)
        queue[rear++] = i++;
    printf("<");
    while(--N){
        for(i = 1; i < K; i++)
            queue[rear++] = queue[front++];
        printf("%d, ", queue[front++]);
    }
    printf("%d>\n", queue[front]);

    return 0;
}
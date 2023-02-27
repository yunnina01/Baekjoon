#include <stdio.h>

int queue[1000000];
int front, rear;

int main(){
    int N, i = 1;
    scanf("%d", &N);
    while(i <= N)
        queue[rear++] = i++;
    while(front + 1 != rear){
        front++;
        queue[rear++] = queue[front++];
    }
    printf("%d\n", queue[front]);

    return 0;
}
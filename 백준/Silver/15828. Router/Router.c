#include <stdio.h>

int front, rear;

int main(){
    int N, n;
    scanf("%d", &N);
    int queue[++N];
    while(1){
        scanf("%d", &n);
        if(n == -1)
            break;
        if(!n)
            front = ++front % N;
        else{    
            if(front == (rear + 1) % N)
                continue;
            queue[rear++] = n;
            rear %= N;
        }
    }
    if(front == rear)
        puts("empty");
    else{
        while(front != rear){
            printf("%d ", queue[front++]);
            front %= N;
        }
    }

    return 0;
}
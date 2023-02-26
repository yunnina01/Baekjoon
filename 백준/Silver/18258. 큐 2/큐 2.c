#include <stdio.h>
#include <string.h>

int queue[2000000];
int head, tail;

int empty(){
    if(head == tail)
        return 1;
    return 0;
}

void push(int item){
    queue[tail++] = item;
}

void pop(){
    if(empty())
        puts("-1");
    else
        printf("%d\n", queue[head++]);
}

void front(){
    if(empty())
        puts("-1");
    else
        printf("%d\n", queue[head]);
}

void back(){
    if(empty())
        puts("-1");
    else
        printf("%d\n", queue[tail - 1]);
}

int main(){
    int N, item;
    char op[6];
    scanf("%d", &N);
    while(N--){
        scanf("%s", op);
        if(!strcmp(op, "push")){
            scanf("%d", &item);
            push(item);
        }
        else if(!strcmp(op, "pop"))
            pop();
        else if(!strcmp(op, "size"))
            printf("%d\n", tail - head);
        else if(!strcmp(op, "empty"))
            printf("%d\n", empty());
        else if(!strcmp(op, "front"))
            front();
        else
            back();
    }

    return 0;
}
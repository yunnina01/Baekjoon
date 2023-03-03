#include <stdio.h>
#include <string.h>

int deque[10000];
int N, head, tail;

int empty(){
    if(head == tail)
        return 1;
    return 0;
}

void push_front(int item){
    deque[(--head + N) % N] = item;
}

void push_back(int item){
    deque[(tail++ + N) % N] = item;
}

void pop_front(){
    if(empty())
        puts("-1");
    else
        printf("%d\n", deque[(head++ + N) % N]);
}

void pop_back(){
    if(empty())
        puts("-1");
    else
        printf("%d\n", deque[(--tail + N) % N]);
}

void front(){
    if(empty())
        puts("-1");
    else
        printf("%d\n", deque[(head + N) % N]);
}

void back(){
    if(empty())
        puts("-1");
    else
        printf("%d\n", deque[(tail - 1 + N) % N]);
}

int main(){
    char op[11];
    int i, item;
    scanf("%d", &N);
    for(i = 0; i < N; i++){
        scanf("%s", op);
        if(!strcmp(op, "push_front")){
            scanf("%d", &item);
            push_front(item);
        }
        else if(!strcmp(op, "push_back")){
            scanf("%d", &item);
            push_back(item);
        }
        else if(!strcmp(op, "pop_front"))
            pop_front();
        else if(!strcmp(op, "pop_back"))
            pop_back();
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
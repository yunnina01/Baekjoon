#include <stdio.h>
#include <string.h>

int stack[10000];
int cnt;

void push(int item){
    stack[cnt++] = item;
}

void pop(){
    if(!cnt)
        puts("-1");
    else
        printf("%d\n", stack[--cnt]);
}

void empty(){
    if(!cnt)
        puts("1");
    else
        puts("0");
}

void top(){
    if(!cnt)
        puts("-1");
    else
        printf("%d\n", stack[cnt - 1]);
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
            printf("%d\n", cnt);
        else if(!strcmp(op, "empty"))
            empty();
        else
            top();
    }

    return 0;
}
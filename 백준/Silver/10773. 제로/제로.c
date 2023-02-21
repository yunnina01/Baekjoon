#include <stdio.h>

int stack[100000], top, res;

int main(){
    int K, op;
    scanf("%d", &K);
    while(K--){
        scanf("%d", &op);
        if(op)
            stack[top++] = op;
        else
            top--;
    }
    while(top)
        res += stack[--top];
    printf("%d\n", res);

    return 0;
}
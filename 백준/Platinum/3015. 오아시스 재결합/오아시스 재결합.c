#include <stdio.h>

typedef struct{
    int n, c;
}Pair;

Pair stack[500001], new;
int top;
long long res;

int main(){
    int N, i;
    scanf("%d", &N);
    for(i = 0; i < N; i++){
        scanf("%d", &new.n);
        new.c = 1;
        while(top && new.n >= stack[top].n){
            res += stack[top].c;
            if(new.n == stack[top].n)
                new.c += stack[top].c;
            top--;
        }
        if(top)
            res++;
        stack[++top] = new;
    }
    printf("%lld\n", res);

    return 0;
}
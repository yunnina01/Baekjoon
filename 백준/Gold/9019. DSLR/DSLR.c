#include <stdio.h>

typedef struct{
    char op;
    int p;
}Pair;

Pair parent[10000];
int queue[10000];
char op[] = "DSLR";

void DSLR(int A, int B){
    int front = 0, rear = 0, i, cur, next, temp;
    for(i = 0; i < 10000; i++)
        parent[i].op = '\0';
    parent[A].op = 1;
    queue[rear++] = A;
    while(1){
        cur = queue[front++];
        if(cur == B)
            break;
        for(i = 0; i < 4; i++){
            if(!i)
                next = cur * 2 % 10000;
            else if(i == 1)
                next = (cur + 9999) % 10000;
            else if(i == 2){
                temp = cur / 1000;
                next = cur % 1000 * 10;
                next += temp;
            }
            else{
                temp = cur % 10;
                next = cur / 10;
                next += temp * 1000;
            }
            if(!parent[next].op){
                parent[next].p = cur;
                parent[next].op = op[i];
                queue[rear++] = next;
            }
        }
    }
}

void back(int A, int B){
    if(A == B)
        return;
    back(A, parent[B].p);
    printf("%c", parent[B].op);
}

int main(){
    int T, A, B;
    scanf("%d", &T);
    while(T--){
        scanf("%d %d", &A, &B);
        DSLR(A, B);
        back(A, B);
        puts("");
    }

    return 0;
}
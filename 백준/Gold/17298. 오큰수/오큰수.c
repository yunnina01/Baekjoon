#include <stdio.h>

int stack[1000000], A[1000000], res[1000000], top;

int main(){
    int N, i;
    scanf("%d", &N);
    for(i = 0; i < N; i++)
        scanf("%d", &A[i]);
    for(i = 0; i < N; i++){
        while(top){
            if(A[stack[top - 1]] >= A[i])
                break;
            res[stack[--top]] = A[i];
        }
        stack[top++] = i;
    }
    while(top)
        res[stack[--top]] = -1;
    for(i = 0; i < N; i++)
        printf("%d ", res[i]);

    return 0;
}
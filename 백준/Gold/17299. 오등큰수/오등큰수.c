#include <stdio.h>

int stack[1000000], A[1000000], fre[1000001], res[1000000], top;

int main(){
    int N, i;
    scanf("%d", &N);
    for(i = 0; i < N; i++){
        scanf("%d", &A[i]);
        fre[A[i]]++;
    }
    for(i = 0; i < N; i++){
        while(top){
            if(fre[A[stack[top - 1]]] < fre[A[i]])
                res[stack[--top]] = A[i];
            else
                break;
        }
        stack[top++] = i;
    }
    for(i = 0; i < N; i++)
        printf("%d ", res[i] ? res[i] : -1);

    return 0;
}
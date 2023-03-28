#include <stdio.h>
#include <stdlib.h>

int bob[100000], res;

int desc(const void *a, const void *b){
    return *(int*)b - *(int*)a;
}

int main(){
    int N, X, A, B, i;
    scanf("%d %d", &N, &X);
    for(i = 0; i < N; i++){
        scanf("%d %d", &A, &B);
        bob[i] = A - B;
        res += B;
        X -= 1000;
    }
    qsort(bob, N, sizeof(int), desc);
    for(i = 0; i < N; i++){
        if(X < 4000 || bob[i] < 0)
            break;
        res += bob[i];
        X -= 4000;
    }
    printf("%d\n", res);

    return 0;
}
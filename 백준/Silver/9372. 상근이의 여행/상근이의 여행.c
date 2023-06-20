#include <stdio.h>

int main(){
    int T, N, M, a, b;
    scanf("%d", &T);
    while(T--){
        scanf("%d %d", &N, &M);
        while(M--)
            scanf("%d %d", &a, &b);
        printf("%d\n", N - 1);
    }

    return 0;
}
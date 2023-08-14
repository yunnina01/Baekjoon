#include <stdio.h>

int parent[10001], visit[10001];

int main(){
    int T, N, A, B, i;
    scanf("%d", &T);
    while(T--){
        scanf("%d", &N);
        for(i = 1; i <= N; i++){
            parent[i] = i;
            visit[i] = 0;
        }
        while(--N){
            scanf("%d %d", &A, &B);
            parent[B] = A;
        }
        scanf("%d %d", &A, &B);
        visit[A] = 1;
        while(A != parent[A]){
            A = parent[A];
            visit[A] = 1;
        }
        while(1){
            if(visit[B]){
                printf("%d\n", B);
                break;
            }
            B = parent[B];
        }
    }

    return 0;
}
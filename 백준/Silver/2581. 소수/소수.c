#include <stdio.h>

int main(){
    int M, N, i, sum = 0, min, flags;
    scanf("%d %d", &M, &N);
    min = N;
    for(; M <= N; M++){
        if(M == 1)
            continue;
        flags = 0;
        for(i = 2; i <= M / 2; i++){
            if(M % i == 0){
                flags++;
                break;
            }
        }
        if(flags == 0){
            if(min > M)
                min = M;
            sum += M;
        }
    }
    if(sum == 0)
        puts("-1");
    else
        printf("%d\n%d\n", sum, min);

    return 0;
}
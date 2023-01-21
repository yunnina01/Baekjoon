#include <stdio.h>

int main(){
    int fibo[41] = {0, 1}, N, i;
    scanf("%d", &N);
    for(i = 2; i <= N; i++)
        fibo[i] = fibo[i - 2] + fibo[i - 1];
    printf("%d %d\n", fibo[N], N - 2);

    return 0;
}
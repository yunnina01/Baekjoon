#include <stdio.h>

int main(){
    int i, N;
    scanf("%d", &N);
    char a[N], M = 0;
    double sum = 0;
    for(i = 0; i < N; i++){
        scanf("%d", &a[i]);
        M = M > a[i] ? M : a[i];
    }
    for(i = 0; i < N; i++)
        sum += (double)a[i] / M * 100;
    printf("%g\n", sum / N);

    return 0;
}
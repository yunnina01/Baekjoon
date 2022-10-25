#include <stdio.h>

int main(){
    int N, i, sum = 0;
    scanf("%d", &N);
    char a[N];
    scanf("%s", a);
    for(i = 0; i < N; i++)
        sum += a[i] - 48;
    printf("%d\n", sum);

    return 0;
}
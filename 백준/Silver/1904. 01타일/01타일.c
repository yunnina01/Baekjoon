#include <stdio.h>

int main(){
    int N, i;
    scanf("%d", &N);
    int arr[N + 1];
    arr[0] = arr[1] = 1;
    for(i = 2; i <= N; i++)
        arr[i] = (arr[i - 2] + arr[i - 1]) % 15746;
    printf("%d\n", arr[N]);

    return 0;
}
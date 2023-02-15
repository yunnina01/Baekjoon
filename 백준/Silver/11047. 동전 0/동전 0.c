#include <stdio.h>

int main(){
    int N, K, i, res = 0;;
    scanf("%d %d", &N, &K);
    int arr[N];
    for(i = 0; i < N; i++)
        scanf("%d", &arr[i]);
    while(K){
        res += K / arr[--i];
        K %= arr[i];
    }
    printf("%d\n", res);

    return 0;
}
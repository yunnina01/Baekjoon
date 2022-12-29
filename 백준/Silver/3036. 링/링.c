#include <stdio.h>

int get_gcd(int a, int b){
    while(a && b){
        if(a > b)
            a %= b;
        else
            b %= a;
    }
    return a ? a : b;
}

int main(){
    int N, i, gcd;
    scanf("%d", &N);
    int arr[N];
    for(i = 0; i < N; i++)
        scanf("%d", &arr[i]);
    for(i = 1; i < N; i++){
        gcd = get_gcd(arr[0], arr[i]);
        printf("%d/%d\n", arr[0] / gcd, arr[i] / gcd);
    }

    return 0;
}
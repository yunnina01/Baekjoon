#include <stdio.h>

int p = 1000000007;

void fibo(int res[][2], int arr[][2]){
    int temp[2][2] = {}, i, j, k;
    for(i = 0; i < 2; i++){
        for(j = 0; j < 2; j++){
            for(k = 0; k < 2; k++)
                temp[i][j] += (long long)res[i][k] * arr[k][j] % p;
        }
    }
    for(i = 0; i < 2; i++){
        for(j = 0; j < 2; j++)
            res[i][j] = temp[i][j] % p;
    }
}

int main(){
    long long n;
    int arr[2][2] = {1, 1, 1, 0}, res[2][2] = {1, 0, 0, 1};
    scanf("%lld", &n);
    while(n){
        if(n % 2)
            fibo(res, arr);
        fibo(arr, arr);
        n /= 2;
    }
    printf("%d\n", res[0][1]);

    return 0;
}
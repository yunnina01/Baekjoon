#include <stdio.h>

int N;

void power(int res[][5], int A[][5]){
    int i, j, k, temp[5][5] = {};
    for(i = 0; i < N; i++){
        for(j = 0; j < N; j++){
            for(k = 0; k < N; k++)
                temp[i][j] += res[i][k] * A[k][j] % 1000;
        }
    }
    for(i = 0; i < N; i++){
        for(j = 0; j < N; j++)
            res[i][j] = temp[i][j] % 1000;
    }
}

int main(){
    int A[5][5], res[5][5] = {}, i, j;
    long long B;
    scanf("%d %lld", &N, &B);
    for(i = 0; i < N; i++){
        for(j = 0; j < N; j++)
            scanf("%d", &A[i][j]);
        res[i][i] = 1;
    }
    while(B){
        if(B % 2)
            power(res, A);
        power(A, A);
        B /= 2;
    }
    for(i = 0; i < N; i++){
        for(j = 0; j < N; j++)
            printf("%d ", res[i][j]);
        puts("");
    }
}
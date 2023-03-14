#include <stdio.h>

int p = 1000000007;

long long fac(int N){
    long long F = 1;
    while(N > 1)
        F = F * N-- % p;
    return F;
}

int power(int A, int B){
    if(B == 1)
        return A;
    long long C = power(A, B / 2);
    C = C * C % p;
    if(B % 2)
        return C * A % p;
    return C;
}

int main(){
    int N, K;
    scanf("%d %d", &N, &K);
    printf("%lld\n", fac(N) * power(fac(N - K) * fac(K) % p, p - 2) % p);

    return 0;
}
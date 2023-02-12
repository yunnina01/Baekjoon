#include <stdio.h>

int mod[1000];

int main(){
    int N, M, n, ps = 0;
    scanf("%d %d", &N, &M);
    while(N--){
        scanf("%d", &n);
        ps = (ps + n) % M;
        mod[ps]++;
    }
    long long res = mod[0];
    while(M--)
        res += (long long)mod[M] * (mod[M] - 1) / 2;
    printf("%lld\n", res);

    return 0;
}
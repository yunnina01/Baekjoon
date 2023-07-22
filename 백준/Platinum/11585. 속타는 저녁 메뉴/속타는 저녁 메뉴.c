#include <stdio.h>

char T[2000001], P[1000001];
int SP[1000000], N, cnt;

int GCD(int a, int b){
    if(!b)
        return a;
    return GCD(b, a % b);
}

void preset(){
    for(int i = 1, j = 0; P[i]; i++){
        while(j && P[i] != P[j])
            j = SP[j - 1];
        if(P[i] == P[j])
            SP[i] = ++j;
    }
}

void KMP(){
    for(int i = 0, j = 0; T[i]; i++){
        while(j && T[i] != P[j])
            j = SP[j - 1];
        if(T[i] == P[j]){
            if(j == N - 1){
                cnt++;
                j = SP[j];
            }
            else
                j++;
        }
    }
}

int main(){
    int i, gcd;
    scanf("%d", &N);
    for(i = 0; i < N; i++)
        scanf(" %c", &P[i]);
    for(i = 0; i < N; i++)
        scanf(" %c", &T[i]);
    for(i = 0; i < N - 1; i++)
        T[i + N] = T[i];
    preset();
    KMP();
    gcd = GCD(N, cnt);
    printf("%d/%d\n", cnt / gcd, N / gcd);

    return 0;
}
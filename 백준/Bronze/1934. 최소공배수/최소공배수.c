#include <stdio.h>

int main(){
    int T, A, B, a, b, gcd;
    scanf("%d", &T);
    while(T--){
        scanf("%d %d", &A, &B);
        a = A;
        b = B;
        while(1){
            if(!a || !b){
                gcd = a ? a : b;
                break;
            }
            if(a > b)
                a %= b;
            else
                b %= a;
        }
        printf("%d\n", A * B / gcd);
    }

    return 0;
}
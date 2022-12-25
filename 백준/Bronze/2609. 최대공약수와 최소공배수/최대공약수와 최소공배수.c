#include <stdio.h>

int main(){
    int A, B, i, gcd;
    scanf("%d %d", &A, &B);
    for(i = 1; i <= A && i <= B; i++){
        if(!(A % i) && !(B % i))
            gcd = i;
    }
    printf("%d\n%d\n", gcd, A * B / gcd);

    return 0;
}
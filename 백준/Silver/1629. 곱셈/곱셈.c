#include <stdio.h>

int power(int A, int B, int C){
    if(B == 1)
        return A % C;
    long long D = power(A, B / 2, C);
    D = D * D % C;
    if(B % 2)
        return D * A % C;
    return D;
}

int main(){
    int A, B, C;
    scanf("%d %d %d", &A, &B, &C);
    printf("%d\n", power(A, B, C));

    return 0;
}
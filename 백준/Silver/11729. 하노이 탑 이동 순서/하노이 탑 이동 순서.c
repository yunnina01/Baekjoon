#include <stdio.h>
#include <math.h>

void Hanoi(int N, int A, int B, int C){
    if(N == 1)
        printf("%d %d\n", A, C);
    else{
        Hanoi(N - 1, A, C, B);
        printf("%d %d\n", A, C);
        Hanoi(N - 1, B, A, C);
    }
}

int main(){
    int N;
    scanf("%d", &N);
    printf("%d\n", (int)pow(2, N) - 1);
    Hanoi(N, 1, 2, 3);
    
    return 0;
}
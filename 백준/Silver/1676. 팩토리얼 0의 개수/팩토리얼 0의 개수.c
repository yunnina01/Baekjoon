#include <stdio.h>

int main(){
    int N, cnt = 0;
    scanf("%d", &N);
    while(N >= 5){
        cnt += N / 5;
        N /= 5;
    }
    printf("%d\n", cnt);

    return 0;
}
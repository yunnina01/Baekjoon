#include <stdio.h>

int main(){
    int N, i;
    scanf("%d", &N);
    while(N > 1){
        for(i = 2; i <= N; i++){
            if(N % i == 0){
                printf("%d\n", i);
                N /= i;
                break;
            }
        }
    }

    return 0;
}
#include <stdio.h>

int D(int N){
    int i, temp, sum;
    for(i = 1; i < N; i++){
        temp = sum = i;
        while(temp > 0){
            sum += temp % 10;
            temp /= 10;
        }
        if(sum == N)
            return i;
    }
    return 0;
}

int main(){
    int N;
    scanf("%d", &N);
    printf("%d\n", D(N));

    return 0;
}
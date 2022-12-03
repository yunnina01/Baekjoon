#include <stdio.h>

void S(int x, int y, int N){
    if((x / N) % 3 == 1 && (y / N) % 3 == 1)
        printf(" ");
    else{
        if(N / 3 == 0)
            printf("*");
        else
            S(x, y, N / 3);
    }
}

int main(){
    int N, i, j;
    scanf("%d", &N);
    for(i = 0; i < N; i++){
        for(j = 0; j < N; j++)
            S(i, j, N);
        puts("");
    }

    return 0;
}
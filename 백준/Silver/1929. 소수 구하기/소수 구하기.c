#include <stdio.h>

int main(){
    int M, N, i, j;
    char arr[1000002] = {1, 1};
    for(i = 2; i < 1000002 / i; i++){
        if(arr[i])
            continue;
        for(j = i * i; j < 1000002; j += i){
            if(j % i == 0)
                arr[j] = 1;
        }
    }
    scanf("%d %d", &M, &N);
    for(i = M; i <= N; i++){
        if(!arr[i])
            printf("%d\n", i);
    }

    return 0;
}
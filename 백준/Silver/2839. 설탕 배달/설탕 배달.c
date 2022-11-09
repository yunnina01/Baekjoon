#include <stdio.h>

int main(){
    int N, cnt = 0;
    scanf("%d", &N);
    while(N > 0){
        if(N % 5 == 0){
            cnt += N / 5;
            break;
        }
        N -= 3;
        cnt++;
    }
    if(N < 0)
        puts("-1");
    else
        printf("%d\n", cnt);

    return 0;
}
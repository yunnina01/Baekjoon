#include <stdio.h>

int main(){
    int N, i, j, num;
    char flags, cnt = 0;
    scanf("%d", &N);
    for(i = 0; i < N; i++){
        scanf("%d", &num);
        if(num == 1)
            continue;
        flags = 0;
        for(j = 2; j <= num / 2; j++){
            if(num % j == 0){
                flags++;
                break;
            }
        }
        if(flags == 0)
            cnt++;
    }
    printf("%d\n", cnt);

    return 0;
}
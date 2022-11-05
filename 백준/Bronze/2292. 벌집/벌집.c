#include <stdio.h>

int H(int N){
    int i, cnt;
    for(i = cnt = 0; 1 + 6 * i < N; i += cnt)
        cnt++;
    return ++cnt;
}

int main(){
    int N;
    scanf("%d", &N);
    printf("%d\n", H(N));

    return 0;
}
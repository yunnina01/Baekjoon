#include <stdio.h>

int h(int x){
    char a, b, c, d;
    a = x / 1000;
    b = x % 1000 / 100;
    c = x % 100 / 10;
    d = x % 10;
    if(a == 0){
        if(b == 0 || c - b == d - c)
            return 1;
    }
    return 0;
}

int main(){
    int N, i, cnt = 0;
    scanf("%d", &N);
    for(i = 1; i <= N; i++){
        if(h(i) == 1)
            cnt++;
    }
    printf("%d\n", cnt);
}
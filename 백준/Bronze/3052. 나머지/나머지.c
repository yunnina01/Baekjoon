#include <stdio.h>

int main(){
    int a[10];
    char i, n = 0, b[42] = {};
    for(i = 0; i < 10; i++){
        scanf("%d", &a[i]);
        b[a[i] % 42]++;
    }
    for(i = 0; i < 42; i++){
        if(b[i] != 0)
            n++;
    }
    printf("%d\n", n);

    return 0;
}
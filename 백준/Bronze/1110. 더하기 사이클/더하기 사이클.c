#include <stdio.h>

int main(){
    int a, b, n = 0;
    scanf("%d", &a);
    b = a;
    do{
        n++;
        a = a % 10 * 10 + (a / 10 + a % 10) % 10;
    }while(a != b);
    printf("%d\n", n);

    return 0;
}
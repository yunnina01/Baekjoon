#include <stdio.h>

int main(){
    int a, b, c, r;
    scanf("%d %d %d", &a, &b, &c);
    if(a != b && a != c && b != c){
        int max = a > b ? a : b;
        max = max > c ? max : c;
        r = max * 100;
    }
    else if(a != b)
        r = 1000 + c * 100;
    else if(a != c)
        r = 1000 + b * 100;
    else
        r = 10000 + a * 1000;
    printf("%d\n", r);

    return 0;
}
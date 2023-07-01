#include <stdio.h>

int main(){
    int x1, x2, x3, y1, y2, y3, cp;
    scanf("%d %d %d %d %d %d", &x1, &y1, &x2, &y2, &x3, &y3);
    cp = x1 * y2 + x2 * y3 + x3 * y1;
    cp -= y1 * x2 + y2 * x3 + y3 * x1;
    if(cp > 0)
        puts("1");
    else if(cp < 0)
        puts("-1");
    else
        puts("0");

    return 0;
}
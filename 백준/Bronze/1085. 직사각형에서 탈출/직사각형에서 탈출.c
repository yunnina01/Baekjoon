#include <stdio.h>

int main(){
    int x, y, w, h, min;
    scanf("%d %d %d %d", &x, &y, &w, &h);
    x = x < w - x ? x : w - x;
    y = y < h - y ? y : h - y;
    min = x < y ? x : y;
    printf("%d\n", min);

    return 0;
}
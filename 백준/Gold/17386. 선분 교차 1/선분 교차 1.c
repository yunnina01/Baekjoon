#include <stdio.h>

typedef struct{
    long long x, y;
}Pair;

int ccw(Pair p1, Pair p2, Pair p3){
    long long cp = p1.x * p2.y + p2.x * p3.y + p3.x * p1.y;
    cp -= p1.y * p2.x + p2.y * p3.x + p3.y * p1.x;
    if(cp < 0)
        return 1;
    return -1;
}

int main(){
    Pair p1, p2, p3, p4;
    scanf("%lld %lld %lld %lld %lld %lld %lld %lld", &p1.x, &p1.y, &p2.x, &p2.y, &p3.x, &p3.y, &p4.x, &p4.y);
    if(ccw(p1, p2, p3) * ccw(p1, p2, p4) < 0 && ccw(p3, p4, p1) * ccw(p3, p4, p2) < 0)
        puts("1");
    else
        puts("0");

    return 0;
}
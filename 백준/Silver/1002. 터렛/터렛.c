#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main(){
    int T, i, x1, y1, r1, x2, y2, r2;
    double D;
    scanf("%d", &T);
    for(i = 0; i < T; i++){
        scanf("%d %d %d %d %d %d", &x1, &y1, &r1, &x2, &y2, &r2);
        D = sqrt((double)(x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        if(!D && r1 == r2)
            puts("-1");
        else if(D > r1 + r2 || D < abs(r1 - r2))
            puts("0");
        else if(D == (r1 + r2) || D == abs(r1 - r2))
            puts("1");
        else
            puts("2");
    }

    return 0;
}
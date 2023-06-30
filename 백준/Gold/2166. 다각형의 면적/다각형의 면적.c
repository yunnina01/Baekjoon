#include <stdio.h>
#define abs(x) ((x > 0) ? (x) : (-x))

typedef struct{
    double x, y;
}Pair;

Pair p[10000];
double res;

double ccw(Pair p1, Pair p2, Pair p3){
    double cp = p1.x * p2.y + p2.x * p3.y + p3.x * p1.y;
    cp -= p1.y * p2.x + p2.y * p3.x + p3.y * p1.x;
    return cp / 2;
}

int main(){
    int N, i;
    scanf("%d", &N);
    for(i = 0; i < N; i++)
        scanf("%lf %lf", &p[i].x, &p[i].y);
    for(i = 2; i < N; i++)
        res += ccw(p[0], p[i - 1], p[i]);
    printf("%.1f\n", abs(res));

    return 0;
}
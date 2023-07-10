#include <stdio.h>
#include <math.h>

double min(double a, double b){
    return a < b ? a : b;
}

int main(){
    double X, Y, D, T, d, res;
    scanf("%lf %lf %lf %lf", &X, &Y, &D, &T);
    d = sqrt(X * X + Y * Y);
    if(D <= T)
        printf("%.9f\n", d);
    else{
        int jump = d / D;
        res = d;
        d -= jump * D;
        if(jump)
            res = min(res, min(jump * T + d, (jump + 1) * T));
        else
            res = min(res, min(T + D - d, 2 * T));
        printf("%.9f\n", res);
    }

    return 0;
}
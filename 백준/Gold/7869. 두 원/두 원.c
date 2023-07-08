#include <stdio.h>
#include <math.h>
#define PI 3.14159265358979

void swap(double *a, double *b){
    double temp = *a;
    *a = *b;
    *b = temp;
}

int main(){
    double x1, y1, r1, x2, y2, r2, d;
    scanf("%lf %lf %lf %lf %lf %lf", &x1, &y1, &r1, &x2, &y2, &r2);
    if(r1 < r2){
        swap(&x1, &x2);
        swap(&y1, &y2);
        swap(&r1, &r2);
    }
    d = sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    if(d >= r1 + r2)
        puts("0.000");
    else if(d + r2 <= r1)
        printf("%.3f", r2 * r2 * PI);
    else{
        double theta1, theta2, S1, S2;
        theta1 = acos((r1 * r1 + d * d - r2 * r2) / (2 * r1 * d));
        theta2 = acos((r2 * r2 + d * d - r1 * r1) / (2 * r2 * d));
        S1 = (r1 * r1 * 2 * theta1) / 2 - r1 * r1 * sin(2 * theta1) / 2;
        S2 = (r2 * r2 * 2 * theta2) / 2 - r2 * r2 * sin(2 * theta2) / 2;
        printf("%.3f", S1 + S2);
    }

    return 0;
}
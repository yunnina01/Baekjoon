#include <stdio.h>

typedef struct{
    long long x, y;
}Pair;

void swap(Pair *p, int a, int b){
    Pair temp = p[a];
    p[a] = p[b];
    p[b] = temp;
}

int ccw(Pair p1, Pair p2, Pair p3){
    long long cp = p1.x * p2.y + p2.x * p3.y + p3.x * p1.y;
    cp -= p1.y * p2.x + p2.y * p3.x + p3.y * p1.x;
    if(cp < 0)
        return 1;
    if(cp > 0)
        return -1;
    return 0;
}

int main(){
    Pair p[4];
    int i, abc, abd, cda, cdb;
    for(i = 0; i < 4; i++)
        scanf("%lld %lld", &p[i].x, &p[i].y);
    abc = ccw(p[0], p[1], p[2]);
    abd = ccw(p[0], p[1], p[3]);
    cda = ccw(p[2], p[3], p[0]);
    cdb = ccw(p[2], p[3], p[1]);
    if(abc * abd == 0 && cda * cdb == 0){
        if(p[0].x == p[1].x){
            if(p[0].y > p[1].y)
                swap(p, 0, 1);
            if(p[2].y > p[3].y)
                swap(p, 2, 3);
            if(p[0].y <= p[3].y && p[2].y <= p[1].y)
                puts("1");
            else
                puts("0");
        }
        else{
            if(p[0].x > p[1].x)
                swap(p, 0, 1);
            if(p[2].x > p[3].x)
                swap(p, 2, 3);
            if(p[0].x <= p[3].x && p[2].x <= p[1].x)
                puts("1");
            else
                puts("0");
        }        
    }
    else if(abc * abd <= 0 && cda * cdb <= 0)
        puts("1");
    else
        puts("0");

    return 0;
}
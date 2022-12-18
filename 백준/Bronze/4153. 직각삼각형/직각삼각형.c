#include <stdio.h>

int is_right(int a, int b, int c){
    int x, y, r;
    if(a >= b && a >= c){
        r = a;
        x = b;
        y = c;
    }
    else if(b >= c){
        r = b;
        x = a;
        y = c;
    }
    else{
        r = c;
        x = a;
        y = b;
    }
    if(r * r == x * x + y * y)
        return 1;
    return 0;
}

int main(){
    int a, b, c;
    while(1){
        scanf("%d %d %d", &a, &b, &c);
        if(!a && !b && !c)
            break;
        if(is_right(a, b, c))
            puts("right");
        else
            puts("wrong");
    }

    return 0;
}
#include <stdio.h>

int main(){
    int a, b, c;
    scanf("%d %d %d", &a, &b, &c);
    a += c / 60;
    b += c % 60;
    if(b >= 60){
        a++;
        b -= 60;
    }
    if(a >= 24)
        a -= 24;
    printf("%d %d\n", a, b);

    return 0;
}
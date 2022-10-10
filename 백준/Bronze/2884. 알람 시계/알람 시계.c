#include <stdio.h>

int main(){
    int a, b;
    scanf("%d %d", &a, &b);
    b -= 45;
    if(b < 0){
        a--;
        b += 60;
    }
    if(a < 0)
        a = 23;
    printf("%d %d", a, b);

    return 0;
}
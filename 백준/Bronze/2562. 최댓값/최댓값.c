#include <stdio.h>

int main(){
    int a[9], i, max = 0;
    for(i = 0; i < 9; i++){
        scanf("%d", &a[i]);
        max = a[max] > a[i] ? max : i;
    }
    printf("%d\n%d\n", a[max], max + 1);

    return 0;
}
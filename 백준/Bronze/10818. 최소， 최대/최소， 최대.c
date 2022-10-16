#include <stdio.h>

int main(){
    int i, n, min = 0, max = 0;
    scanf("%d", &n);
    int a[n];
    for(i = 0; i < n; i++){
        scanf("%d", &a[i]);
        min = a[min] < a[i] ? min : i;
        max = a[max] > a[i] ? max : i;
    }
    printf("%d %d\n", a[min], a[max]);

    return 0;
}
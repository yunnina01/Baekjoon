#include <stdio.h>

int main(){
    char i, j, n;
    scanf("%d", &n);
    for(i = 1; i <= n; i++){
        for(j = n - i; j > 0; j--)
            printf(" ");
        for(j = 0; j < i; j++)
            printf("*");
        puts("");
    }

    return 0;
}
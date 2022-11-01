#include <stdio.h>

int T(char *a){
    char t[] = {3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 8, 8, 8, 8, 9 ,9, 9, 10, 10, 10, 10};
    int i, r = 0;
    for(i = 0; a[i] != '\0'; i++)
        r += t[a[i] - 'A'];
    return r;
}

int main(){
    char a[15];
    scanf("%s", &a);
    printf("%d\n", T(a));
    
    return 0;
}
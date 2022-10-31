#include <stdio.h>
#include <string.h>

void R(char *arr){
    char temp = arr[0];
    arr[0] = arr[2];
    arr[2] = temp;
}

int main(){
    char a[4], b[4];
    scanf("%s %s", &a, &b);
    R(a);
    R(b);
    if(strcmp(a, b) > 0)
        printf("%s\n", a);
    else
        printf("%s\n", b);

    return 0;
}
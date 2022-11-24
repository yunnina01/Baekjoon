#include <stdio.h>
#include <string.h>

int main(){
    char a[11], i, j, len, temp;
    scanf("%s", a);
    len = strlen(a);
    for(i = 0; i < len - 1; i++){
        for(j = i; j < len; j++){
            if(a[i] < a[j]){
                temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
    }
    printf("%s\n", a);

    return 0;
}
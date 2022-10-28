#include <stdio.h>

int C(char *a){
    int i, cnt = 0;
    if(a[0] != ' ' && a[0] != '\0')
        cnt++;
    for(i = 0; a[i] != '\0'; i++){
        if(a[i] == ' ' && a[i+1] != '\0')
            cnt++;
    }
    return cnt;
}

int main(){
    char a[1000000];
    scanf("%[^\n]", a);
    printf("%d\n", C(a));

    return 0;
}
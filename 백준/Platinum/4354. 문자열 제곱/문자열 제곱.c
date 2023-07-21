#include <stdio.h>
#include <string.h>

char s[1000001];
int SP[1000000];

void preset(){
    for(int i = 1, j = 0; s[i]; i++){
        while(j && s[i] != s[j])
            j = SP[j - 1];
        if(s[i] == s[j])
            SP[i] = ++j;
    }
}

int main(){
    int i, len, div;
    while(1){
        scanf("%s", s);
        if(s[0] == '.')
            break;
        len = strlen(s);
        for(i = 0; i < len; i++)
            SP[i] = 0;
        preset();
        div = len - SP[len - 1];
        if(len % div)
            puts("1");
        else
            printf("%d\n", len / div);
    }

    return 0;
}
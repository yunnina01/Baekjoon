#include <stdio.h>
#include <string.h>

int main(){
    char stack[1000001], str[1000001], bomb[37];
    int i, j, k, top = 0, len;
    scanf("%s %s", str, bomb);
    len = strlen(bomb);
    for(i = 0; str[i] != '\0'; i++){
        stack[top++] = str[i];
        if(top >= len){
            for(j = top - len, k = 0; j < top; j++){
                if(stack[j] != bomb[k++])
                    break;
            }
            if(j == top)
                top -= len;
        }
    }
    if(!top)
        puts("FRULA");
    else{
        stack[top] = '\0';
        puts(stack);
    }

    return 0;
}
#include <stdio.h>

int main(){
    char str[33];
    scanf("%s", str);
    int i, col = 0, bar = 0;
    for(i = 0; str[i] != '\0'; i++){
        if(str[i] == ':')
            col++;
        if(str[i] == '_')
            bar++;
    }
    printf("%d\n", i + col + bar * 5);

    return 0;
}
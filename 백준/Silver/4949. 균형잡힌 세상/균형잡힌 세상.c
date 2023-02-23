#include <stdio.h>

int main(){
    char str[101], stack[101];
    while(1){
        scanf("%[^\n]s", str);
        getchar();
        if(str[0] == '.')
            break;
        int i, top = 0;
        for(i = 0; str[i] != '.'; i++){
            if(str[i] != '(' && str[i] != ')' && str[i] != '[' && str[i] != ']')
                continue;
            else if(str[i] == ')' && stack[top - 1] == '(')
                top--;
            else if(str[i] == ']' && stack[top - 1] == '[')
                top--;
            else
                stack[top++] = str[i];
        }
        if(!top)
            puts("yes");
        else
            puts("no");
    }

    return 0;
}
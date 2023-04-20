#include <stdio.h>

int prec(char op){
    switch(op){
        case '(': case')':
            return 0;
        case '+': case '-':
            return 1;
        case '*': case '/':
            return 2;
    }
}

int main(){
    char str[101], stack[100];
    int i, top = 0;
    scanf("%s", str);
    for(i = 0; str[i] != '\0'; i++){
        switch(str[i]){
            case '+': case '-': case '*': case '/':
                while(top && prec(str[i]) <= prec(stack[top - 1]))
                    printf("%c", stack[--top]);
                stack[top++] = str[i];
                break;
            case '(':
                stack[top++] = '(';
                break;
            case ')':
                while(stack[top - 1] != '(')
                    printf("%c", stack[--top]);
                top--;
                break;
            default:
                printf("%c", str[i]);
        }
    }
    while(top--)
        printf("%c", stack[top]);

    return 0;
}
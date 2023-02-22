#include <stdio.h>

int main(){
    int T, i, top;
    char ps[51], stack[51];
    scanf("%d", &T);
    while(T--){
        top = 0;
        scanf("%s", ps);
        for(i = 0; ps[i] != '\0'; i++){
            if(ps[i] == ')' && stack[top - 1] == '(')
                top--;
            else
                stack[top++] = ps[i];
        }
        if(!top)
            puts("YES");
        else
            puts("NO");
    }

    return 0;
}
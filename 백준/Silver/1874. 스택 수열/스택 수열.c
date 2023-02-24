#include <stdio.h>

int stack[100000], top, idx;
char res[200001];

int main(){
    int n, m, i = 1;
    scanf("%d", &n);
    while(n--){
        scanf("%d", &m);
        while(i <= m){
            stack[top++] = i++;
            res[idx++] = '+';
        }
        if(stack[top - 1] == m){
            top--;
            res[idx++] = '-';
        }
    }
    if(!top){
        for(i = 0; i < idx; i++)
            printf("%c\n", res[i]);
    }
    else
        puts("NO");

    return 0;
}
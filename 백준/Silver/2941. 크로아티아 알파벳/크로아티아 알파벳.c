#include <stdio.h>

int CA(char *a){
    char i, cnt = 0;
    for(i = 0; a[i] != '\0'; i++){
        switch(a[i]){
            case 'c':
                if(a[i+1] == '=' || a[i+1] == '-')
                    i++;
                break;
            case 'd':
                if(a[i+1] == 'z' && a[i+2] == '=')
                    i += 2;
                else if(a[i+1] == '-')
                    i++;
                break;
            case 'l': case 'n':
                if(a[i+1] == 'j')
                    i++;
                break;
            case 's': case 'z':
                if(a[i+1] == '=')
                    i++;
        }
        cnt++;
    }
    return cnt;
}

int main(){
    char a[101];
    scanf("%s", a);
    printf("%d\n", CA(a));

    return 0;
}
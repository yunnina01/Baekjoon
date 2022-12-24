#include <stdio.h>

int main(){
    int A, B;
    while(1){
        scanf("%d %d", &A, &B);
        if(!A && !B)
            break;
        if(!(B % A))
            puts("factor");
        else if(!(A % B))
            puts("multiple");
        else
            puts("neither");
    }

    return 0;
}
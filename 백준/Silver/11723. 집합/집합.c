#include <stdio.h>
#include <string.h>

int mask[21];

int main(){
    int M, i, x;
    char op[7];
    scanf("%d", &M);
    while(M--){
        scanf("%s", op);
        if(!strcmp(op, "add")){
            scanf("%d", &x);
            mask[x] = 1;
        }
        else if(!strcmp(op, "remove")){
            scanf("%d", &x);
            mask[x] = 0;
        }
        else if(!strcmp(op, "check")){
            scanf("%d", &x);
            printf("%d\n", mask[x]);
        }
        else if(!strcmp(op, "toggle")){
            scanf("%d", &x);
            mask[x] = !mask[x];
        }
        else if(!strcmp(op, "all")){
            for(i = 1; i <= 20; i++)
                mask[i] = 1;
        }
        else
            memset(mask, 0, sizeof(mask));
    }

    return 0;
}
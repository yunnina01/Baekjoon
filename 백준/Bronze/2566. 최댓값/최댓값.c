#include <stdio.h>

int main(){
    char a[10][10], i, j, Mi = 0, Mj = 0;
    for(i = 0; i < 9; i++){
        for(j = 0; j < 9; j++){
            scanf("%d", &a[i][j]);
            if(a[i][j] > a[Mi][Mj]){
                Mi = i;
                Mj = j;
            }
        }
    }
    printf("%d\n%d %d", a[Mi][Mj], Mi+1, Mj+1);

    return 0;
}
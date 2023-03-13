#include <stdio.h>

int arr[64][65];

void check_block(int x, int y, int block){
    int i, j;
    for(i = x; i < x + block; i++){
        for(j = y; j < y + block; j++){
            if(arr[i][j] != arr[x][y])
                break;
        }
        if(j != y + block)
            break;
    }
    if(i == x + block)
        printf("%d", arr[x][y]);
    else{
        printf("(");
        for(i = 0; i < 2; i++){
            for(j = 0; j < 2; j++)
                check_block(x + block * i / 2, y + block * j / 2, block / 2);
        }
        printf(")");
    }
}

int main(){
    int N, i, j;
    scanf("%d", &N);
    for(i = 0; i < N; i++){
        for(j = 0; j < N; j++)
            scanf("%1d", &arr[i][j]);
    }
    check_block(0, 0, N);

    return 0;
}
#include <stdio.h>

int arr[2187][2187], res[3];

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
        res[arr[x][y] + 1]++;
    else{
        for(i = 0; i < 3; i++){
            for(j = 0; j < 3; j++)
                check_block(x + block * i / 3, y + block * j / 3, block / 3);
        }
    }
}

int main(){
    int N, i, j;
    scanf("%d", &N);
    for(i = 0; i < N; i++){
        for(j = 0; j < N; j++)
            scanf("%d", &arr[i][j]);
    }
    check_block(0, 0, N);
    printf("%d\n%d\n%d\n", res[0], res[1], res[2]);

    return 0;
}
#include <stdio.h>
#include <stdlib.h>

void back(int arr[][9], int cnt){
    int i, j;
    if(cnt == 81){
        for(i = 0; i < 9; i++){
            for(j = 0; j < 9; j++)
                printf("%d ", arr[i][j]);
            puts("");
        }
        exit(0);
    }
    else{
        int row = cnt / 9, col = cnt % 9;
        if(arr[row][col])
            back(arr, cnt + 1);
        else{
            for(i = 1; i <= 9; i++){
                int k, r_row, r_col, check = 0;
                arr[row][col] = i;
                for(j = 0; j < 9; j++){
                    if((arr[row][j] == i && j != col) || (arr[j][col] == i && j != row))
                        check = 1;
                }
                for(j = 1; j <= 7; j += 3){
                    for(k = 1; k <= 7; k += 3){
                        if(abs(j - row) <= 1 && abs(k - col) <= 1){
                            r_row = j;
                            r_col = k;
                        }
                    }
                }
                for(j = r_row - 1; j <= r_row + 1; j++){
                    for(k = r_col - 1; k <= r_col + 1; k++){
                        if(j == row && k == col)
                            continue;
                        if(arr[j][k] == i)
                            check = 1;
                    }
                }
                if(!check)
                    back(arr, cnt + 1);
                arr[row][col] = 0;
            }
        }
    }
}

int main(){
    int arr[9][9], i, j;
    for(i = 0; i < 9; i++){
        for(j = 0; j < 9; j++)
            scanf("%d", &arr[i][j]);
    }
    back(arr, 0);

    return 0;
}
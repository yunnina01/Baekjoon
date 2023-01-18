#include <stdio.h>
#include <stdlib.h>

int arr[9][9], row[9][10], col[9][10], rec[9][10];

void back(int cnt){
    int i, j;
    if(cnt == 81){
        for(i = 0; i < 9; i++){
            for(j = 0; j < 9; j++)
                printf("%d ", arr[i][j]);
            puts("");
        }
        exit(0);
    }
    int x = cnt / 9, y = cnt % 9;
    if(arr[x][y])
        back(cnt + 1);
    else{
        for(i = 1; i <= 9; i++){
            if(!row[x][i] && !col[y][i] && !rec[x / 3 * 3 + y / 3][i]){
                arr[x][y] = i;
                row[x][i] = col[y][i] = rec[x / 3 * 3 + y / 3][i] = 1;
                back(cnt + 1);
                arr[x][y] = 0;
                row[x][i] = col[y][i] = rec[x / 3 * 3 + y / 3][i] = 0;
            }
        }
    }
}

int main(){
    int i, j;
    for(i = 0; i < 9; i++){
        for(j = 0; j < 9; j++){
            scanf("%d", &arr[i][j]);
            row[i][arr[i][j]] = col[j][arr[i][j]] = rec[i / 3 * 3 + j / 3][arr[i][j]] = 1;
        }
    }
    back(0);

    return 0;
}
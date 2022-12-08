#include <stdio.h>

int main(){
    int N, M, i, j, k, l;
    scanf("%d %d", &N, &M);
    char ch[N][M + 1], min = 32, cntB, cntW;
    for(i = 0; i < N; i++)
        scanf("%s", ch[i]);
    for(i = 0; i < N - 7; i++){
        for(j = 0; j < M - 7; j++){
            cntB = cntW = 0;
            for(k = i; k < i + 8; k++){
                for(l = j; l < j + 8; l++){
                    if((k + l) % 2 == 0){
                        if(ch[k][l] == 'B')
                            cntW++;
                        else
                            cntB++;
                    }
                    else{
                        if(ch[k][l] == 'B')
                            cntB++;
                        else
                            cntW++;
                    }
                }
            }
            min = min < cntB ? min : cntB;
            min = min < cntW ? min : cntW;
        }
    }
    printf("%d\n", min);

    return 0;
}
#include <stdio.h>

int program[100][1000], size[100], N, K;

void preset(int *P, int *SP){
    for(int i = 1, j = 0; i < K; i++){
        while(j && P[i] != P[j])
            j = SP[j - 1];
        if(P[i] == P[j])
            SP[i] = ++j;
    }
}

int KMP(int *P, int *SP, int idx){
    for(int i = 0, j = 0; i < size[idx]; i++){
        while(j && program[idx][i] != P[j])
            j = SP[j - 1];
        if(program[idx][i] == P[j])
            j++;
        if(j == K)
            return 1;
    }
    return 0;
}

int virus(){
    int P[K], RP[K], SP[K], RSP[K];
    int i, j, cnt;
    for(i = K - 1; i < size[0]; i++){
        for(j = 0; j < K; j++){
            RP[j] = program[0][i - j];
            P[K - j - 1] = RP[j];
            SP[j] = RSP[j] = 0;
        }
        preset(P, SP);
        preset(RP, RSP);
        cnt = 1;
        for(j = 1; j < N; j++){
            if(KMP(P, SP, j) || KMP(RP, RSP, j))
                cnt++;
            else
                break;
        }
        if(cnt == N)
            return 1;
    }
    return 0;
}

int main(){
    int i, j;
    scanf("%d %d", &N, &K);
    for(i = 0; i < N; i++){
        scanf("%d", &size[i]);
        for(j = 0; j < size[i]; j++)
            scanf("%d", &program[i][j]);
    }
    printf("%s\n", virus() ? "YES" : "NO");
}
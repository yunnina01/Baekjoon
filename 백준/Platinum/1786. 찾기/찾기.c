#include <stdio.h>

char T[1000001], P[1000001];
int SP[1000000], pos[1000000], cnt;

void preset(){
    int i, j;
    for(i = 1, j = 0; P[i]; i++){
        while(j && P[i] != P[j])
            j = SP[j - 1];
        if(P[i] == P[j])
            SP[i] = ++j;
    }
}

void KMP(){
    int i, j;
    for(i = j = 0; T[i]; i++){
        while(j && T[i] != P[j])
            j = SP[j - 1];
        if(T[i] == P[j]){
            if(!P[j + 1]){
                pos[cnt++] = i - j + 1;
                j = SP[j];
            }
            else
                j++;
        }
    }
}

int main(){
    scanf("%[^\n]s", T);
    getchar();
    scanf("%[^\n]s", P);
    preset();
    KMP();
    printf("%d\n", cnt);
    for(int i = 0; i < cnt; i++)
        printf("%d ", pos[i]);

    return 0;
}
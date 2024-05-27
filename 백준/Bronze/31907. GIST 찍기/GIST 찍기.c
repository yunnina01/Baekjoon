#include <stdio.h>

int main() {
    char GIST[3][5] = {{"G..."}, {".I.T"}, {"..S."}};
    int K;
    scanf("%d", &K);

    for(int i = 0; i < 3; i++) {
        for(int j = 0; j < K; j++) {
            for(int k = 0; k < 4; k++) {
                for(int l = 0; l < K; l++)
                    printf("%c", GIST[i][k]);
            }
            puts("");
        }
    }
    return 0;
}
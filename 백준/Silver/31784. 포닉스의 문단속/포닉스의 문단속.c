#include <stdio.h>

int main() {
    int N, K;
    scanf("%d %d", &N, &K);

    char S[N + 1];
    scanf("%s", S);

    for(int i = 0; i < N; i++) {
        int cnt = ('Z' - S[i] + 1) % 26;
        if(K >= cnt) {
            K -= cnt;
            S[i] = 'A';
        }
    }
    S[N - 1] = 'A' + (S[N - 1] - 'A' + K) % 26;

    printf("%s\n", S);
    return 0;
}
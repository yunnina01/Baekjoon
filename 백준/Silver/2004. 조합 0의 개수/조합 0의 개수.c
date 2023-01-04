#include <stdio.h>

int zero(int N, int num){
    int cnt = 0;
    while(N >= num){
        cnt += N / num;
        N /= num;
    }
    return cnt;
}

int main(){
    int n, m, c5, c2;
    scanf("%d %d", &n, &m);
    c5 = zero(n, 5) - zero(n - m, 5) - zero(m, 5);
    c2 = zero(n, 2) - zero(n - m, 2) - zero(m, 2);
    printf("%d\n", c5 < c2 ? c5 : c2);

    return 0;
}
#include <stdio.h>
#include <string.h>

int dp[2501], palindrome[2501][2501];
char str[2501];

int min(int a, int b){
    return a < b ? a : b;
}

void make_palindrome(int len){
    int i, j, end;
    for(i = 1; i <= len; i++)
        palindrome[i][i] = 1;
    for(i = 1; i < len; i++){
        if(str[i] == str[i + 1])
            palindrome[i][i + 1] = 1;
    }
    for(i = 3; i <= len; i++){
        for(j = 1; i + j - 1 <= len; j++){
            end = i + j - 1;
            if(str[j] == str[end] && palindrome[j + 1][end - 1])
                palindrome[j][end] = 1;
        }
    }
}

int main(){
    int i, j, len;
    scanf("%s", str + 1);
    str[0] = ' ';
    len = strlen(str) - 1;
    make_palindrome(len);
    for(i = 1; i <= len; i++){
        dp[i] = 2e9;
        for(j = 1; j <= i; j++){
            if(palindrome[j][i])
                dp[i] = min(dp[i], dp[j - 1] + 1);
        }
    }
    printf("%d\n", dp[len]);

    return 0;
}
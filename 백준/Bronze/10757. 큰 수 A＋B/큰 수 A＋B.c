#include <stdio.h>
#include <string.h>

void reverse(char *arr){
    int len = strlen(arr), i;
    for(i = 0; i < len / 2; i++){
        char temp = arr[i];
        arr[i] = arr[len - i - 1];
        arr[len - i - 1] = temp;
    }
}

int main(){
    char A[10002] = {}, B[10002] = {}, r[10003] = {}, carry = 0, sum;
    int i, len;
    scanf("%s %s", A, B);
    reverse(A);
    reverse(B);
    len = strlen(A) > strlen(B) ? strlen(A) : strlen(B);
    for(i = 0 ; i < len; i++){
        sum = A[i] - '0' + B[i] - '0' + carry;
        if(sum < 0)
            sum += '0';
        if(sum > 9)
            carry = 1;
        else
            carry = 0;
        r[i] = sum % 10 + '0';
    }
    if(carry == 1)
        r[len] = '1';
    reverse(r);
    printf("%s\n", r);

    return 0;
}
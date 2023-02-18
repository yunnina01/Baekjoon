#include <stdio.h>

int main(){
    char str[51];
    int arr[25], i, j = 0, temp = 0, sum = 0, res;
    scanf("%s", str);
    for(i = 0; str[i] != '\0'; i++){
        if(str[i] == '-'){
            sum += temp;
            arr[j++] = sum;
            sum = temp = 0;
        }
        else if(str[i] == '+'){
            sum += temp;
            temp = 0;
        }
        else{
            temp *= 10;
            temp += str[i] - '0';
        }
    }
    sum += temp;
    arr[j++] = sum;
    res = arr[0];
    for(i = 1; i < j; i++)
        res -= arr[i];
    printf("%d\n", res);

    return 0;
}
#include <stdio.h>

int main(){
    int i, j, n;
    char a[80];
    scanf("%d", &n);
    for(i = 0; i < n; i++){
        int cnt = 1, sum = 0;
        scanf("%s", a);
        for(j = 0; a[j] != '\0'; j++){
            if(a[j] == 'O')
                sum += cnt++;
            else
                cnt = 1;
        }
        printf("%d\n", sum);
    }

    return 0;
}
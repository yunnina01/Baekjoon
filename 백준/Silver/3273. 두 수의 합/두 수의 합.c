#include <stdio.h>
#include <stdlib.h>

int a[100000], cnt;

int asc(const void *a, const void *b){
    return *(int*)a - *(int*)b;
}

int main(){
    int i, n, x;
    scanf("%d", &n);
    for(i = 0; i < n; i++)
        scanf("%d", &a[i]);
    scanf("%d", &x);
    qsort(a, n, sizeof(int), asc);
    int left = 0, right = n - 1, val;
    while(left < right){
        val = a[left] + a[right];
        if(val == x){
            left++;
            right--;
            cnt++;
        }
        else if(val < x)
            left++;
        else
            right--;
    }
    printf("%d\n", cnt);

    return 0;
}
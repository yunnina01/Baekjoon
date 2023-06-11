#include <stdio.h>

int pre[10000], cnt;

void postorder(int start, int end){
    if(start >= end)
        return;
    if(start == end - 1){
        printf("%d\n", pre[start]);
        return;
    }
    int idx = start;
    while(++idx < end){
        if(pre[start] < pre[idx])
            break;
    }
    postorder(start + 1, idx);
    postorder(idx, end);
    printf("%d\n", pre[start]);
}

int main(){
    while(scanf("%d", &pre[cnt++]) != EOF);
    postorder(0, cnt - 1);

    return 0;
}
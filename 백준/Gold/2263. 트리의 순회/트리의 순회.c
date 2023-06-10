#include <stdio.h>

int in[100000], post[100000], idx[100001];

void preorder(int instart, int inend, int poststart, int postend){
    if(instart > inend || poststart > postend)
        return;
    int root = idx[post[postend]], left = root - instart;
    printf("%d ", in[root]);
    preorder(instart, root - 1, poststart, poststart + left - 1);
    preorder(root + 1, inend, poststart + left, postend - 1);
}

int main(){
    int n, i, j;
    scanf("%d", &n);
    for(i = 0; i < n; i++){
        scanf("%d", &in[i]);
        idx[in[i]] = i;
    }
    for(i = 0; i < n; i++)
        scanf("%d", &post[i]);
    preorder(0, n - 1, 0, n - 1);

    return 0;
}
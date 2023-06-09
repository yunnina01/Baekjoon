#include <stdio.h>

typedef struct{
    char left, right;
}Node;

Node tree[26];

void insert(int v, char l, char r){
    tree[v].left = l, tree[v].right = r;
}

void preorder(char R){
    if(R != '.'){
        printf("%c", R);
        preorder(tree[R - 'A'].left);
        preorder(tree[R - 'A'].right);
    }
}

void inorder(char R){
    if(R != '.'){
        inorder(tree[R - 'A'].left);
        printf("%c", R);
        inorder(tree[R - 'A'].right);
    }
}

void postorder(char R){
    if(R != '.'){
        postorder(tree[R - 'A'].left);
        postorder(tree[R - 'A'].right);
        printf("%c", R);
    }
}

int main(){
    int N;
    char v, l, r;
    scanf("%d", &N);
    while(N--){
        scanf(" %c %c %c", &v, &l, &r);
        insert(v - 'A', l, r);
    }
    preorder('A');
    puts("");
    inorder('A');
    puts("");
    postorder('A');

    return 0;
}
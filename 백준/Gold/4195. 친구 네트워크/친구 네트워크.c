#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Node{
    char name[21];
    int idx;
    struct Node *next;
}Node;

Node *map[100000];
int parent[200000], rank[200000], cnt;

int get_hash(char *name){
    int hash = 0;
    for(int i = 0; name[i]; i++)
        hash = (hash * 100 + name[i]) % 100000;
    return hash;
}

int search(char *name){
    int hash = get_hash(name);
    for(Node *temp = map[hash]; temp; temp = temp->next){
        if(!strcmp(temp->name, name))
            return temp->idx;
    }
    return -1;
}

void insert(char *name){
    Node *new_node = (Node*)malloc(sizeof(Node));
    int hash = get_hash(name);
    strcpy(new_node->name, name);
    new_node->idx = cnt++;
    new_node->next = NULL;
    if(map[hash] != NULL)
        new_node->next = map[hash];
    map[hash] = new_node;
}

int find(int x){
    if(parent[x] == x)
        return x;
    return parent[x] = find(parent[x]);
}

void unions(int x, int y){
    if(rank[x] < rank[y]){
        int temp = x;
        x = y, y = temp;
    }
    rank[x] += rank[y];
    parent[y] = x;
    printf("%d\n", rank[x]);
}

int main(){
    int T, F, i, x, y;
    char str1[21], str2[21];
    scanf("%d", &T);
    while(T--){
        scanf("%d", &F);
        for(i = 0; i < F; i++)
            map[i] = NULL;
        for(i = 0; i < F * 2; i++){
            parent[i] = i;
            rank[i] = 1;
        }
        cnt = 0;
        while(F--){
            scanf("%s %s", str1, str2);
            x = search(str1), y = search(str2);
            if(x == -1){
                insert(str1);
                x = cnt - 1;
            }
            if(y == -1){
                insert(str2);
                y = cnt - 1;
            }
            x = find(x), y = find(y);
            if(x != y)
                unions(x, y);
            else
                printf("%d\n", rank[x]);
        }
    }

    return 0;
}
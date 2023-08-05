#include <stdio.h>
#include <stdlib.h>

typedef struct Node{
    int v;
    struct Node *next;
}Node;

Node *graph[32001];
int degree[32001], heap[32001], size;

void insert(int a, int b){
    Node *new_node = (Node*)malloc(sizeof(Node));
    new_node->v = b;
    new_node->next = NULL;
    if(graph[a])
        new_node->next = graph[a];
    graph[a] = new_node;
}

void push(int item){
    int i = ++size;
    while(i != 1 && item < heap[i / 2]){
        heap[i] = heap[i / 2];
        i /= 2;
    }
    heap[i] = item;
}

int pop(){
    int parent = 1, child = 2, min = heap[1], temp = heap[size--];
    while(child <= size){
        if(child < size && heap[child] > heap[child + 1])
            child++;
        if(temp <= heap[child])
            break;
        heap[parent] = heap[child];
        parent = child;
        child *= 2;
    }
    heap[parent] = temp;
    return min;
}

int main(){
    int N, M, a, b, i;
    scanf("%d %d", &N, &M);
    while(M--){
        scanf("%d %d", &a, &b);
        insert(a, b);
        degree[b]++;
    }
    for(i = 1; i <= N; i++){
        if(!degree[i])
            push(i);
    }
    while(size){
        a = pop();
        printf("%d ", a);
        for(Node *temp = graph[a]; temp; temp = temp->next){
            if(--degree[temp->v] == 0)
                push(temp->v);
        }
    }

    return 0;
}
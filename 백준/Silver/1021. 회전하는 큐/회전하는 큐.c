#include <stdio.h>
#include <stdlib.h>

typedef struct Node{
    int num;
    struct Node *prev, *next;
}Node;

void init(Node *head){
    head->prev = head->next = head;
}

Node* get_node(int item){
    Node *new_node = (Node*)malloc(sizeof(Node));
    new_node->num = item;
    return new_node;
}

void push_front(Node *head, int item){
    Node *new_node = get_node(item);
    new_node->prev = head;
    new_node->next = head->next;
    head->next->prev = new_node;
    head->next = new_node;
}

void push_rear(Node *head, int item){
    Node *new_node = get_node(item);
    new_node->prev = head->prev;
    new_node->next = head;
    head->prev->next = new_node;
    head->prev = new_node;
}

int pop_front(Node *head){
    Node *temp = head->next;
    int data = temp->num;
    head->next = temp->next;
    temp->next->prev = head;
    free(temp);
    return data;
}

int pop_rear(Node *head){
    Node *temp = head->prev;
    int data = temp->num;
    head->prev = temp->prev;
    temp->prev->next = head;
    free(temp);
    return data;
}

int search_left(Node *head, int key){
    int cnt = 1;
    for(Node *temp = head->prev; temp->num != key; temp = temp->prev)
        cnt++;
    return cnt;
}

int search_right(Node *head, int key){
    int cnt = 0;
    for(Node *temp = head->next; temp->num != key; temp = temp->next)
        cnt++;
    return cnt;
}

int main(){
    Node head;
    int N, M, n, left, right, res = 0;
    init(&head);
    scanf("%d %d", &N, &M);
    while(N)
        push_front(&head, N--);
    while(M--){
        scanf("%d", &n);
        left = search_left(&head, n), right = search_right(&head, n);
        if(left < right){
            res += left;
            while(left--)
                push_front(&head, pop_rear(&head));
        }
        else{
            res += right;
            while(right--)
                push_rear(&head, pop_front(&head));
        }
        pop_front(&head);
    }
    printf("%d\n", res);

    return 0;
}
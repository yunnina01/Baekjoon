# C (C99)

백준 문제를 풀면서 알게 되거나 유용할 것 같은 C 언어의 기능들을 정리하였습니다.
<br><br>

***
* **qsort()**  
C언어에 내장되어 있는 정렬 함수로, 시간복잡도가 O(NlogN) 이다.  

``` C
#include <stdio.h>
#include <stdlib.h>

int asc(const void *a, const void *b){
    return *(int*a) - *(int*b);
}

int main(){
    int arr[5] = {5, 3, 2, 4, 1};
    qsort(arr, 5, sizeof(int), asc);

    return 0;
}
```
<br>

* **memset()**  
메모리의 내용(값)을 원하는 크기만큼 특정 값으로 초기화하는 함수이다.  
1바이트 단위로 초기화 하므로 0이나 char타입으로만 초기화할 수 있다.

``` C
#include <stdio.h>
#include <stdlib.h>

int main(){
    int arr[10000];
    memset(arr, 0, sizeof(arr));

    return 0;
}
```
<br>

* **bsearch()**  
C언어에 내장되어 있는 이진 탐색 함수이다.  
이진 탐색 함수이므로 탐색하고자 하는 배열은 정렬되어 있어야 한다.  
key가 존재할 경우 key의 주소값을, 존재하지 않는다면 NULL을 반환한다

``` C
#include <stdio.h>
#include <stdlib.h>

int asc(const void *a, const void *b){
    return *(int*)a - *(int*)b;
}

int main(){
    int arr[10] = {1, 8, 5, 6, 10, 3, 4, 7, 9, 2};
    int key = 5;
    qsort(arr, 10, sizeof(int), asc);
    if(bsearch(&key, arr, 10, sizeof(int), asc))
        puts("YES");
    else
        puts("NO");

    return 0;
}
```
<br>

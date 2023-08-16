# C (C99)

백준 문제를 풀면서 알게 되거나 유용할 것 같은 C 언어의 기능들을 정리하였습니다.
<br><br><br>

***
* **qsort()**  
C언어에 내장되어 있는 정렬 함수로, 시간복잡도가 O(NlogN) 이다.
  * void qsort(void* base , size_t nel , size_t width , int (* compare)(const void* , const void* ))
    * base : 정렬하고자 하는 배열의 포인터
    * nel : 정렬하고자 하는 배열의 원소의 수
    * width : 정렬하고자 하는 배열의 원소 하나의 크기
    * compare : 비교 논리
  * int compare(const void* , const void* )  
    정렬 방법을 정할 수 있으며, 숫자나 문자열, 구조체 등을 정렬할 수 있다.  
    실수값들을 정렬하기 위해서는 주의해서 사용하여야 한다.
<br><br>

* **memset()**  
메모리의 내용(값)을 원하는 크기만큼 특정 값으로 초기화하는 함수이다.  
1바이트 단위로 초기화 하므로 0이나 char타입으로만 초기화할 수 있다.
  * void* memset(void* ptr, int value, size_t num)
    * ptr : 초기화하고자 하는 메모리의 시작 주소
    * value : 초기화할 값
    * num : 초기화할 메모리의 크기
<br><br>

* **bsearch()**  
C언어에 내장되어 있는 이진 탐색 함수이다.
  * void* bsearch(const void* key , const void* base , size_t nmemb , size_t size , int (* compare)(const void* , const void* ))
    * key : 찾을 값
    * base : 정렬된 배열의 포인터
    * nmemb : 정렬된 배열의 원소의 수
    * size : 정렬된 배열의 원소 하나의 크기
    * compare : 비교 논리
<br><br>

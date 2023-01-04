# Baekjoon

백준에서 해결한 문제에 대한 답을 업로드하는 저장소입니다.  
C언어(C99)를 사용하여 문제를 해결하였습니다.  

백준 : https://www.acmicpc.net  
백준 프로필 : https://www.acmicpc.net/user/yunnina01  
solved.ac : https://solved.ac/profile/yunnina01
<br/><br/><br/>

## 백준 알고리즘을 풀면서 배운 점 (부족한 점)

* **10757번 큼 수 A + B**  
최대 만 자리 숫자 2개를 더하는 문제이다.  
정수 자료형은 모두 오버플로우가 발생하므로 문자열로 받는다.  
문자열 2개를 뒤집어서 계산한 뒤 얻은 결과를 다시 뒤집는다.
<br/><br/>

* **에라토스테네스의 체**  
소수를 빠르게 구할 수 있는 알고리즘이다.  
이론 : [에라토스테네스의 체](https://ko.wikipedia.org/wiki/%EC%97%90%EB%9D%BC%ED%86%A0%EC%8A%A4%ED%85%8C%EB%84%A4%EC%8A%A4%EC%9D%98_%EC%B2%B4)
<br/><br/>

* **qsort()**  
C언어에 내장되어 있는 정렬 함수로, 시간복잡도가 O(NlogN) 이다.
  * void qsort(void* base , size_t nel , size_t width , int (* compare)(const void* , const void* ))
    * base : 정렬하고자 하는 배열의 포인터
    * nel : 정렬하고자 하는 배열의 원소의 수
    * width : 정렬하고자 하는 배열의 원소 하나의 크기
    * compare : 비교 논리
  * int compare(const void* , const void* )  
    정렬 방법을 정할 수 있으며, 숫자나 문자열, 구조체 등을 정렬할 수 있다.
<br/><br/>

* **2981번 검문**  
여러 개의 수를 나누었을 때 나머지가 같은 수를 구하는 문제이다.  
수학적 이론 : [검문](https://user-images.githubusercontent.com/100751725/210517191-39f8b011-bcbb-47ff-9463-d2c1883f4ad0.png)
<br/><br/>

* **백트래킹**  
해를 찾는 도중 해가 아니면 다시 되돌아가서 해를 찾아가는 기법이다.  
재귀를 이용해서 구현하는데 추가적인 이해(복습)이 필요할 것 같다.
<br/><br/>

* **동적 계획법 (Dynamic Programming)**  
특정 범위까지의 값을 구하기 위해서 그것과 다른 범위까지의 값을 이용하여 효율적으로 값을 구하는 알고리즘 설계 기법이다.
<br/><br/>

* **누적 합**  
앞에서부터 차례대로 누적된 합을 구해놓고 이를 이용해서 구간의 합을 구하는 기법이다.
<br/><br/>

* **그리디 알고리즘**  
현재 단계에서 최적의 해를 선택해나가는 기법이다.
<br/><br/>

* **분할 정복 (Divide and Conquer)**  
크고 방대한 문제를 조금씩 나눠가면서 용이하게 해결할 수 있는 단위로 나눈 다음 다시 합치면서 해결하는 기법이다.
<br/><br/>

* **bsearch()**  
C언어에 내장되어 있는 이진 탐색 함수이다.
  * void* bsearch(const void* key , const void* base , size_t nmemb , size_t size , int (* compare)(const void* , const void* ))
    * key : 찾을 값
    * base : 정렬된 배열의 포인터
    * nmemb : 정렬된 배열의 원소의 수
    * size : 정렬된 배열의 원소 하나의 크기
    * compare : 비교 
<br/><br/>

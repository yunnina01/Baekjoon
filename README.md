# Baekjoon

백준에서 해결한 알고리즘 문제에 대한 답을 업로드하는 저장소입니다.  
주로 C언어(C99)를 사용하여 문제를 해결하였습니다.  
Python(Python3) 연습용으로도 사용하였습니다.  

백준 : https://www.acmicpc.net  
백준 프로필 : https://www.acmicpc.net/user/yunnina01  
solved.ac : https://solved.ac/profile/yunnina01
<br><br><br>

## 백준 문제를 풀면서 배운 점

* **프로그래밍 언어 정리**  
[C](https://github.com/yunnina01/Baekjoon/tree/main/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D%20%EC%96%B8%EC%96%B4%20%EC%A0%95%EB%A6%AC/C)
<br><br>

* **10757번 : 큰 수 A + B**  
최대 만 자리 숫자 2개를 더하는 문제이다.  
정수 자료형은 모두 오버플로우가 발생하므로 문자열로 받는다.  
문자열 2개를 뒤집어서 계산한 뒤 얻은 결과를 다시 뒤집는다.
<br><br>

* **에라토스테네스의 체**  
소수를 빠르게 구할 수 있는 알고리즘이다.  
이론 : [에라토스테네스의 체](https://ko.wikipedia.org/wiki/%EC%97%90%EB%9D%BC%ED%86%A0%EC%8A%A4%ED%85%8C%EB%84%A4%EC%8A%A4%EC%9D%98_%EC%B2%B4)
<br><br>

* **백트래킹**  
해를 찾는 도중 해가 아니면 다시 되돌아가서 해를 찾아가는 기법이다.
<br><br>

* **동적 계획법 (Dynamic Programming)**  
특정 범위의 값을 구하기 위해 그것과 다른 범위의 값을 이용해 효율적으로 값을 얻는 알고리즘 설계 기법이다.
<br><br>

* **냅색 문제 (Knapsack Problem)**  
조합 최적화 문제의 일종으로, 배낭에 담을 수 있는 물건들의 가치가 최대인 부분집합을 찾는 문제이다.  
일반적으로 그리디 알고리즘이나 동적 계획법으로 해결한다.  
참고 : [배낭 문제](https://namu.wiki/w/%EB%B0%B0%EB%82%AD%20%EB%AC%B8%EC%A0%9C#s-2)
<br><br>

* **누적 합**  
앞에서부터 차례대로 누적된 합을 구해놓고 이를 이용해서 구간의 합을 구하는 기법이다.
<br><br>

* **그리디 알고리즘**  
현재 단계에서 최적의 해를 선택해나가는 기법이다.
<br><br>

* **분할 정복 (Divide and Conquer)**  
크고 방대한 문제를 나눠가면서 해결하기 용이한 단위까지 나눈 다음 다시 합치면서 문제를 해결하는 기법이다.
<br><br>

* **페르마의 소정리**  
임의의 소수 p와 서로소인 한 수의 (p - 1)제곱을 p로 나눈 나머지가 1이라는 정리로, 정수론의 기본이 된다.  
이론 : [페르마의 소정리](https://namu.wiki/w/%ED%8E%98%EB%A5%B4%EB%A7%88%EC%9D%98%20%EC%86%8C%EC%A0%95%EB%A6%AC)
<br><br>

* **Lower Bound / Upper Bound**  
Lower Bound는 key보다 크거나 같은 값이 처음 나타나는 위치이고,  
Upper Bound는 key보다 큰 값이 처음 나타나는 위치이다.  
이론 및 구현 : [Lower Bound / Upper Bound](https://yoongrammer.tistory.com/105)
<br><br>

* **그래프와 순회**  
그래프란 연결되어 있는 원소간의 관계를 표현한 자료구조이다.  
순회 방법에는 깊이 우선 탐색(DFS)과 너비 우선 탐색(BFS)이 있다.  
그래프 순회 알고리즘 :[그래프 순회](https://user-images.githubusercontent.com/100751725/221105356-fca74b74-f903-4444-b974-e433757fa02d.png)
<br><br>

* **최단 경로**  
가중치 그래프에서 가중치 합이 최소가 되는 경로를 찾는 알고리즘이다.  
최단 경로 알고리즘 :
[다익스트라](https://namu.wiki/w/%EB%8B%A4%EC%9D%B5%EC%8A%A4%ED%8A%B8%EB%9D%BC%20%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98) , 
[벨먼-포드](https://namu.wiki/w/%EB%B2%A8%EB%A8%BC-%ED%8F%AC%EB%93%9C%20%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98?from=%EB%B2%A8%EB%A7%8C-%ED%8F%AC%EB%93%9C%20%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98) , 
[플로이드-워셜](https://namu.wiki/w/%ED%94%8C%EB%A1%9C%EC%9D%B4%EB%93%9C-%EC%9B%8C%EC%85%9C%20%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98)
<br><br>

* **투 포인터**  
1차원 배열에서 각자 다른 원소를 가리키는 두 개의 포인터를 조작하여 원하는 결과를 얻는 기법이다.  
대표적으로 특정 합을 가지는 부분 수열을 찾는 문제에 사용된다.
<br><br>

* **트리와 순회**  
트리란 그래프의 일종으로, 사이클이 없는 연결 그래프이다.  
순회 방법에는 전위 순회(Preorder), 중위 순회(Inorder), 후위 순회(Postorder)가 있다.  
트리 순회 알고리즘 : [트리 순회](https://ko.wikipedia.org/wiki/%ED%8A%B8%EB%A6%AC_%EC%88%9C%ED%9A%8C)
<br><br>

* **유니온 파인드 (Union-Find)**  
두 노드가 같은 그래프에 속하는지 판별하는 알고리즘이다.  
서로소 집합이나 상호 배타적 집합(Disjoint-Set)으로도 불린다.
<br><br>

* **최소 신장 트리 (Minimum Spanning Tree)**  
신장 트리는 그래프에서 모든 정점에 대한 최소한의 연결만을 남긴 그래프이다.  
최소 신장 트리는 신장 트리들 중에서 간선의 가중치 합이 최소인 트리를 뜻한다.  
특징 :
[신장 트리](https://user-images.githubusercontent.com/100751725/221528323-91f2d166-8116-4134-b828-6fdfb8eced8c.png) , 
[최소 신장 트리](https://user-images.githubusercontent.com/100751725/221528652-2cfa39d3-272e-480e-b679-1c8ef628162b.png)  
최소 신장 트리 알고리즘 :
[크루스칼 (Kruskal's Algorithm)](https://namu.wiki/w/%ED%81%AC%EB%A3%A8%EC%8A%A4%EC%B9%BC%20%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98) , 
[프림 (Prim's Algorithm)](https://namu.wiki/w/%ED%94%84%EB%A6%BC%20%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98)
<br><br>

* **CCW 알고리즘 (신발끈 공식)**  
세 점의 방향 관계나 선분의 교차판별을 하는데 사용하는 알고리즘이다.  
이론 : [신발끈 공식](https://namu.wiki/w/%EC%8B%A0%EB%B0%9C%EB%81%88%20%EA%B3%B5%EC%8B%9D)  
세 점의 방향 관계에서 결과값이 양수이면 반시계방향, 0이면 평행, 음수이면 시계방향이다.  
선분교차판별에서는 값이 0보다 작거나 같으면 일반적으로 교차한다.  
반례 : [선분교차판별 반례](https://user-images.githubusercontent.com/100751725/226580880-932b1ee9-c394-4b38-9487-0daf4b44787e.png)  
참고 : [삼각형의 넓이 공식 응용](https://user-images.githubusercontent.com/100751725/226583296-024643a6-03f4-42f1-9fc6-9dd788956204.png)
<br><br>

* **비트마스크 (BitMask)**  
이진수를 사용하는 컴퓨터의 연산 방식을 이용해, 정수의 이진수 표현을 자료구조로 쓰는 기법이다.  
장점 : 수행 시간이 빠르고, 메모리 사용량이 적다.  
비트 연산자 : AND(&), OR(|), XOR(^), NOT(~), Shift(<<, >>)  
집합 구현 : [연산](https://github.com/jws1218/insta-clone/assets/100751725/d60da524-88a4-49df-9f6f-463462ad8fad)
<br><br>

* **KMP 알고리즘**  
문자열 검색 알고리즘으로, 불필요한 비교를 없애 성능을 개선시킨 알고리즘이다.  
이론 : [KMP 알고리즘](https://chanhuiseok.github.io/posts/algo-14/)
<br><br>

* **위상 정렬(Topological Sorting)**  
방향 그래프에서 간선이 한 방향을 가르키도록 정점들을 나열하는 알고리즘이다.  
순서가 정해져 있는 작업을 차례대로 수행해야 할 때 사용할 수 있다.
<br><br>

* **최소 공통 조상(Lowest Common Ancestor)**  
트리에서 임의의 두 정점이 갖는 가장 가까운 조상 정점을 의미한다.
<br><br>

* **강한 연결 요소(Strongly Connected Component)**  
방향 그래프에서 모든 정점이 모든 다른 정점에 도달 가능한 경우, 강하게 연결(상호 연결) 되었다고 한다.  
강한 연결 요소는 부분 그래프의 모든 정점이 강하게 연결된 임의의 방향 그래프를 말한다.  
강한 연결 요소 알고리즘 :
[코사라주](https://ko.wikipedia.org/wiki/%EC%BD%94%EC%82%AC%EB%9D%BC%EC%A3%BC_%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98) ,
[타잔](https://ko.wikipedia.org/wiki/%EA%B0%95%ED%95%9C_%EC%97%B0%EA%B2%B0_%EC%9A%94%EC%86%8C)
<br><br>

* **세그먼트 트리(Segment Tree)**  
여러 개의 데이터가 존재할 때, 특정 구간의 합(최댓값, 최솟값, 곱 등)을 구하는 데 사용하는 자료구조이다.  
이진 트리 형태이며, 특정 구간의 합을 가장 빠르게 구할 수 있다.  
이론 : [세그먼트 트리](https://book.acmicpc.net/ds/segment-tree)  
참고 : [느리게 갱신되는 세그먼트 트리 (Segment Tree With Lazy Propagation)](https://book.acmicpc.net/ds/segment-tree-lazy-propagation)
<br><br>

* **스위핑 (Sweeping)**  
휩쓸고 지나가며 문제를 해결하는 방식으로, 특정 기준에 따라 정렬한 후 순서대로 처리하는 알고리즘이다.
<br><br>


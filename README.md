# Baekjoon

백준에서 해결한 알고리즘 문제에 대한 답을 업로드하는 저장소입니다.  
C(C99), Java(Java11), Python(Python3)을 사용하였습니다.  

백준 : https://www.acmicpc.net  
백준 프로필 : https://www.acmicpc.net/user/yunnina01  
solved.ac : https://solved.ac/profile/yunnina01
<br><br>

***
### 백준 문제를 풀면서 배운 알고리즘

* **프로그래밍 언어 정리**  
[C](https://github.com/yunnina01/PrivateStudy/blob/main/%EC%96%B8%EC%96%B4/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D%20%EC%96%B8%EC%96%B4/C.md) /
[Java](https://github.com/yunnina01/PrivateStudy/blob/main/%EC%96%B8%EC%96%B4/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D%20%EC%96%B8%EC%96%B4/Java.md) / 
[Python](https://github.com/yunnina01/PrivateStudy/blob/main/%EC%96%B8%EC%96%B4/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D%20%EC%96%B8%EC%96%B4/Python.md)
<br><br>

* **큰 수 덧셈**  
자료형의 범위를 벗어나는 숫자 2개를 더하는 문제이다.  
정수 자료형은 모두 오버플로우가 발생하므로 문자열로 받는다.  
문자열 2개를 뒤집어서 계산한 뒤 얻은 결과를 다시 뒤집는다.
<br><br>

* **에라토스테네스의 체 (Sieve of Eratosthenes)**  
소수를 빠르게 구할 수 있는 알고리즘이다.  
이론 : [에라토스테네스의 체](https://ko.wikipedia.org/wiki/%EC%97%90%EB%9D%BC%ED%86%A0%EC%8A%A4%ED%85%8C%EB%84%A4%EC%8A%A4%EC%9D%98_%EC%B2%B4)
<br><br>

* **백트래킹 (Backtracking)**  
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

* **누적 합 (Prefix Sum)**  
앞에서부터 차례대로 누적된 합을 구해놓고 이를 이용해서 구간의 합을 구하는 기법이다.
<br><br>

* **그리디 알고리즘 (Greedy Algorithm)**  
현재 단계에서 최적의 해를 선택해나가는 기법이다.
<br><br>

* **분할 정복 (Divide and Conquer)**  
크고 방대한 문제를 나눠가면서 해결하기 용이한 단위까지 나눈 다음 다시 합치면서 문제를 해결하는 기법이다.
<br><br>

* **페르마의 소정리 (Fermat's little Theorem)**  
임의의 소수 p와 서로소인 한 수의 (p - 1)제곱을 p로 나눈 나머지가 1이라는 정리로, 정수론의 기본이 된다.  
이론 : [페르마의 소정리](https://namu.wiki/w/%ED%8E%98%EB%A5%B4%EB%A7%88%EC%9D%98%20%EC%86%8C%EC%A0%95%EB%A6%AC)
<br><br>

* **Lower Bound / Upper Bound**  
Lower Bound는 key보다 크거나 같은 값이 처음 나타나는 위치이고,  
Upper Bound는 key보다 큰 값이 처음 나타나는 위치이다.  
이론 : [Lower Bound / Upper Bound](https://yoongrammer.tistory.com/105)
<br><br>

* **그래프 (Graph)**  
그래프란 연결되어 있는 원소간의 관계를 표현한 자료구조이다.  
순회 방법에는 깊이 우선 탐색(DFS)과 너비 우선 탐색(BFS)이 있다.  
알고리즘 : [그래프 순회](https://user-images.githubusercontent.com/100751725/221105356-fca74b74-f903-4444-b974-e433757fa02d.png)
<br><br>

* **최단 경로 (Shortest Path)**  
가중치 그래프에서 가중치 합이 최소가 되는 경로를 찾는 알고리즘이다.  
알고리즘 :
[다익스트라](https://namu.wiki/w/%EB%8B%A4%EC%9D%B5%EC%8A%A4%ED%8A%B8%EB%9D%BC%20%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98) / 
[벨먼-포드](https://namu.wiki/w/%EB%B2%A8%EB%A8%BC-%ED%8F%AC%EB%93%9C%20%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98?from=%EB%B2%A8%EB%A7%8C-%ED%8F%AC%EB%93%9C%20%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98) / 
[플로이드-워셜](https://namu.wiki/w/%ED%94%8C%EB%A1%9C%EC%9D%B4%EB%93%9C-%EC%9B%8C%EC%85%9C%20%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98)
<br><br>

* **투 포인터 (Two Pointer)**  
1차원 배열에서 각자 다른 원소를 가리키는 두 개의 포인터를 조작하여 원하는 결과를 얻는 기법이다.  
대표적으로 특정 합을 가지는 부분 수열을 찾는 문제에 사용된다.
<br><br>

* **트리 (Tree)**  
트리란 그래프의 일종으로, 사이클이 없는 연결 그래프이다.  
순회 방법에는 전위 순회(Preorder), 중위 순회(Inorder), 후위 순회(Postorder)가 있다.  
알고리즘 : [트리 순회](https://ko.wikipedia.org/wiki/%ED%8A%B8%EB%A6%AC_%EC%88%9C%ED%9A%8C)
<br><br>

* **분리 집합 (Disjoint Set)**  
두 노드가 같은 그래프에 속하는지 판별하는 알고리즘이다.  
서로소 집합이나 상호 배타적 집합으로도 불린다.  
알고리즘 : [Union-Find](https://velog.io/@mu1616/%EB%B6%84%EB%A6%AC%EC%A7%91%ED%95%A9Disjoint-set)
<br><br>

* **최소 신장 트리 (Minimum Spanning Tree)**  
신장 트리는 그래프에서 모든 정점에 대한 최소한의 연결만을 남긴 그래프이다.  
최소 신장 트리는 신장 트리들 중에서 간선의 가중치 합이 최소인 트리를 뜻한다.  
특징 :
[신장 트리](https://user-images.githubusercontent.com/100751725/221528323-91f2d166-8116-4134-b828-6fdfb8eced8c.png) / 
[최소 신장 트리](https://user-images.githubusercontent.com/100751725/221528652-2cfa39d3-272e-480e-b679-1c8ef628162b.png)  
알고리즘 :
[크루스칼](https://namu.wiki/w/%ED%81%AC%EB%A3%A8%EC%8A%A4%EC%B9%BC%20%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98) / 
[프림](https://namu.wiki/w/%ED%94%84%EB%A6%BC%20%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98)
<br><br>

* **CCW (신발끈 공식)**  
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
비트 연산자 : AND(&) , OR(|) , XOR(^) , NOT(~) , Shift(<< , >>)  
참고 : [연산](https://github.com/jws1218/insta-clone/assets/100751725/d60da524-88a4-49df-9f6f-463462ad8fad)
<br><br>

* **KMP (Knuth-Morris-Pratt Algorithm)**  
문자열 검색 알고리즘으로, 불필요한 비교를 없애 성능을 개선시킨 알고리즘이다.  
시간과 공간 복잡도 측면에서 모두 효율적이다.  
이론 : [KMP](https://chanhuiseok.github.io/posts/algo-14/)
<br><br>

* **트라이 (Trie)**  
문자열을 저장하고 효율적으로 탐색하기 위한 트리 형태의 자료구조이다.  
시간 복잡도 측면에서는 효율적이나 공간 복잡도는 비효율적일 수 있다.  
이론 : [트라이](https://velog.io/@kimdukbae/%EC%9E%90%EB%A3%8C%EA%B5%AC%EC%A1%B0-%ED%8A%B8%EB%9D%BC%EC%9D%B4-Trie)
<br><br>

* **위상 정렬 (Topological Sorting)**  
방향 그래프에서 간선이 한 방향을 가르키도록 정점들을 나열하는 알고리즘이다.  
순서가 정해져 있는 작업을 차례대로 수행해야 할 때 사용할 수 있다.
<br><br>

* **최소 공통 조상 (Lowest Common Ancestor)**  
트리에서 임의의 두 정점이 갖는 가장 가까운 조상 정점을 의미한다.
<br><br>

* **강한 연결 요소 (Strongly Connected Component)**  
방향 그래프에서 모든 정점이 모든 다른 정점에 도달 가능한 경우, 강하게 연결(상호 연결) 되었다고 한다.  
강한 연결 요소는 부분 그래프의 모든 정점이 강하게 연결된 임의의 방향 그래프를 말한다.  
알고리즘 :
[코사라주](https://ko.wikipedia.org/wiki/%EC%BD%94%EC%82%AC%EB%9D%BC%EC%A3%BC_%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98) / 
[타잔](https://ko.wikipedia.org/wiki/%EA%B0%95%ED%95%9C_%EC%97%B0%EA%B2%B0_%EC%9A%94%EC%86%8C)  
참고 : [2-SAT](https://justicehui.github.io/hard-algorithm/2019/05/17/2SAT/)
<br><br>

* **세그먼트 트리 (Segment Tree)**  
여러 개의 데이터가 존재할 때, 특정 구간의 합(최댓값, 최솟값, 곱 등)을 구하는 데 사용하는 자료구조이다.  
이진 트리 형태이며, 특정 구간의 합을 가장 빠르게 구할 수 있다.  
이론 : [세그먼트 트리](https://book.acmicpc.net/ds/segment-tree)  
참고 :
[느리게 갱신되는 세그먼트 트리](https://book.acmicpc.net/ds/segment-tree-lazy-propagation) /
[머지 소트 트리](https://justicehui.github.io/medium-algorithm/2020/02/25/merge-sort-tree/)
<br><br>

* **펜윅 트리 (Fenwick Tree , Binary Indexed Tree)**  
세그먼트 트리의 변형으로 수열의 구간 합을 빠르게 구할 수 있도록 고안된 자료구조이다.  
BIT(Binary Indexed Tree) 라고도 한다.  
이론 : [펜윅 트리](https://www.acmicpc.net/blog/view/21)
<br><br>

* **스위핑 (Sweeping)**  
휩쓸고 지나가며 문제를 해결하는 방식으로, 특정 기준에 따라 정렬한 후 순서대로 처리하는 알고리즘이다.
<br><br>

* **볼록 껍질 (Convex Hull)**  
집합으로 주어진 점이나 영역을 모두 포함하는 가장 작은 볼록 집합(다각형)이다.  
알고리즘 :
[그레이엄 스캔](https://ko.wikipedia.org/wiki/%EA%B7%B8%EB%A0%88%EC%9D%B4%EC%97%84_%EC%8A%A4%EC%BA%94) /
[선물 포장 (짐꾸리기)](https://ko.wikipedia.org/wiki/%EC%84%A0%EB%AC%BC_%ED%8F%AC%EC%9E%A5_%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98)
<br><br>

* **회전하는 캘리퍼스 (Rotating Calipers)**  
가장 먼 두 점의 거리를 구할 때 사용하는 알고리즘이다.  
볼록 껍질(Convex Hull)을 기반으로 동작한다.  
알고리즘 : [회전하는 캘리퍼스](https://hellogaon.tistory.com/40)
<br><br>

* **이분 매칭 (Bipartite Matching)**  
이분 그래프에서 가능한 가장 많은 매칭 수를 찾는 알고리즘이다.  
알고리즘 : [이분 매칭](https://loosie.tistory.com/643)
<br><br>

* **최대 유량 (Network Flow)**  
그래프에서 두 정점 사이에 얼마나 많은 유량(flow)을 보낼 수 있는지 계산하는 알고리즘이다.  
최대 유량 (Maximum Flow) 혹은 네트워크 유량 (Network Flow)이라고 한다.  
알고리즘 : [포드-풀커슨 , 에드몬드 카프](https://unorderedmap.tistory.com/6) / [디닉](https://www.crocus.co.kr/1088)  
참고 : [최대 유량 최소 컷 정리](https://m.blog.naver.com/jqkt15/222063980106)
<br><br>

* **최소 비용 최대 유량 (Minimum Cost Maximum Flow)**  
최소 비용을 구하여 최소 비용에 해당하는 간선으로 가능한 최대 유량을 구하는 알고리즘이다.  
즉, 최단 거리에서의 최대 유량을 구하는 알고리즘이다.  
이론 : [MCMF](https://www.crocus.co.kr/1090)  
알고리즘 : [SPFA](https://gina65.tistory.com/26)
<br><br>

* **게임  이론 (Game Theory)**  
게임 이론은 상호 의존적인 의사 결정에 관한 이론이다.  
이론 : [스프라그-그런디 정리](https://anz1217.tistory.com/110)
<br><br>

* **오일러 경로 테크닉 (Euler Tour Technique)**  
트리의 정점들에서 연속성을 찾기 어려워 트리에 구간 쿼리를 적용하는 것은 어렵다.  
오일러 경로 테크닉은 특정 형태의 쿼리를 처리하기 위한 알고리즘이다.  
이론 : [오일러 경로 테크닉](https://david0506.tistory.com/55)
<br><br>

* **오프라인 쿼리 (Offline Query)**  
쿼리의 순서를 재배치해서 효율적으로 쿼리를 수행하는 문제이다.    
알고리즘 :
[Mo's](https://justicehui.github.io/hard-algorithm/2019/06/17/MoAlgorithm/) /
[제곱근 분할법](https://book.acmicpc.net/algorithm/sqrt-decomposition)
<br><br>

* **뤼카 정리 (Lucas Theorem)**  
이항계수를 구하는 정리로, 빠른 시간복잡도를 가진다.  
이론 : [뤼카 정리](https://ps.mjstudio.net/lucas)
<br><br>

* **확장된 유클리드 호제법 (Extended Euclidean Algorithm)**  
유클리드 알고리즘은 a, b의 최대공약수 GCD(a, b)를 구하는 알고리즘이다.  
확장 유클리드 알고리즘은 as + bt = GCD(a, b)를 만족하게 하는 정수 s, t를 구하는 알고리즘이다.  
gcd가 1인 경우는 모듈러 연산에서의 곱셈의 역원을 구하는데 사용하기도 한다.  
알고리즘 : [확장된 유클리드](https://thfist-1071.tistory.com/251)
<br><br>

* **중국인의 나머지 정리 (Chinese Remainder Theorem)**  
연립 합동식의 해의 존재성과 유일성을 증명하는 정리이다.  
이론 : [중국인의 나머지 정리](https://ohgym.tistory.com/20)
<br><br>

* **밀러-라빈 소수판별법 (Miller-Rabin Primality Test)**  
주어진 수가 소수인지 아닌지를 판별하는 알고리즘이다.  
이론 : [밀러-라빈 소수판별법](https://goodbyefin.tistory.com/47)
<br><br>

* **폴라드 로 알고리즘 (Pollard's rho Algorithm)**  
저장 공간을 적게 사용하면서 빠르게 소인수분해를 할 수 있는 알고리즘이다.  
이론 : [폴라드 로 소인수분해](https://blog.naver.com/mym0404/222515973684)
<br><br>

* **고속 푸리에 변환 (Fast Fourier Transform)**  
두 벡터의 합성곱을 계산하는 데 유용한 알고리즘이다.  
즉, 두 N차 다항식의 곱의 계수들을 빠르게 계산할 수 있는 알고리즘이다.  
이론 : [고속 푸리에 변환](https://tistory.joonhyung.xyz/6)  
알고리즘 : [FFT](https://restudycafe.tistory.com/563) / [NTT](https://blog.naver.com/yyhjin12/223044578555)
<br><br>

* **Manacher**  
문자열의 부분 문자열이 팰린드롬인지 빠르게 판단하는 알고리즘이다.  
알고리즘 : [Manacher](https://www.crocus.co.kr/1075)
<br><br>

* **Z**  
문자열에서 특정 패턴을 찾는 패턴 검색 알고리즘이다.  
알고리즘 : [Z](https://sgc109.tistory.com/208)
<br><br>

* **LCP 배열 (Longest Common Prefix Array)**  
접미사 배열 상에서 이웃한 두 접미사 간의 최장 공통 접두사의 길이를 저장한 배열이다.
이론 : [LCP 배열](https://sgc109.tistory.com/208)
<br><br>

* **아호-코라식 알고리즘**  

<br><br>
 
* **센트로이드 분할**  

<br><br>

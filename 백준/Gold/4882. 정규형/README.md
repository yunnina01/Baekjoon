# [Gold III] 정규형 - 4882 

[문제 링크](https://www.acmicpc.net/problem/4882) 

### 성능 요약

메모리: 17432 KB, 시간: 168 ms

### 분류

자료 구조, 스택, 트리

### 제출 일자

2024년 9월 28일 11:51:35

### 문제 설명

<p><img alt="" src="https://www.acmicpc.net/upload/images/andortree.png" style="float:right; height:172px; width:164px">모든 불리언 식은 Disjunctive Normal Form(DNF)나 Conjunctive Normal Form(CNF)로 나타낼 수 있다. DNF는 하나 또는 그 이상의 CNF식을 OR로 연결한 식이고, CNF는 DNF식을 AND로 연결한 식이다.</p>

<p>AND/OR 트리는 DNF나 CNF 불리언 식을 트리와 같은 형태로 표현한 것이다. DNF나 CNF는 서로를 부분식으로 포함하기 때문에, 서브 트리의 레벨만 알면 그 서브 트리가 AND트리인지 OR트리인지를 알 수 있다.</p>

<p>오른쪽 그림은 (A∨(B∧C))∧(D∨E)를 트리로 나타낸 것이다. 레벨 1(가장 위)과 3은 AND트리이다.</p>

<p>AND/OR 트리가 주어졌을 때, 식을 계산하는 프로그램을 작성하시오.</p>

### 입력 

 <p>입력은 여러 개의 테스트 케이스로 이루어져 있다. 각 테스트 케이스는 한 줄로 이루어져 있고, 32,000글자를 넘지 않는다.</p>

<pre>(E<sub>1</sub> E<sub>2</sub> ... E<sub>n</sub>)</pre>

<p>항상 n > 0을 만족하고, E<sub>i</sub>가 T인 경우에는 true, F인 경우에는 false이다. 부분식도 이와 같은 형식으로 주어진다.</p>

<p>가장 낮은 레벨에 있는 트리는 AND 트리이다. 입력의 마지막 줄에는 ()가 주어진다.</p>

### 출력 

 <p>각 테스트 케이스에 대해서, 다음을 출력한다.</p>

<pre>k. E</pre>

<p>k는 테스트 케이스의 번호이고, E는 입력으로 주어진 식의 값 true 또는 false이다.</p>


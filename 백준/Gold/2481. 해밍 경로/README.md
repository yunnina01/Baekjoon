# [Gold II] 해밍 경로 - 2481 

[문제 링크](https://www.acmicpc.net/problem/2481) 

### 성능 요약

메모리: 74856 KB, 시간: 1080 ms

### 분류

그래프 이론, 자료 구조, 그래프 탐색, 집합과 맵, 너비 우선 탐색, 비트마스킹, 역추적

### 제출 일자

2025년 7월 8일 06:05:08

### 문제 설명

<p>길이가 같은 두 개의 이진수 코드 w<sub>1</sub>과 w<sub>2</sub>가 있다고 하자. 이 두 코드 사이의 해밍 거리(Hamming distance)는 w<sub>1</sub>과 w<sub>2</sub>의 각 비트를 왼쪽부터 오른쪽으로 차례대로 비교할 때 서로 다른 값을 가진 비트의 수이다. 예를 들어, w<sub>1</sub> = 010010, w<sub>2</sub> = 011011 이라고 하면, 세 번째 비트와 여섯 번째 비트만 서로 다르므로 해밍 거리는 2이다.</p>

<p>KOI 연구소는 특정 암호문에서 사용되는 총 N개의 이진 코드를 가지고 있다. 각 코드의 길이는 K로 일정하다. 연구소는 이 코드들에 대해 여러 가지 특징을 분석하고 있다. 예를 들어, 다음과 같이 길이가 3인 5개의 이진 코드들이 있다고 하자.</p>

<ul>
	<li>w<sub>1</sub> = 000</li>
	<li>w<sub>2</sub> = 111</li>
	<li>w<sub>3</sub> = 010</li>
	<li>w<sub>4</sub> = 110</li>
	<li>w<sub>5</sub> = 001</li>
</ul>

<p>두 코드 w<sub>i</sub>와 w<sub>j</sub> 사이의 해밍 거리를 hd(w<sub>i</sub>, w<sub>j</sub>)로 표현한다. 그러면, hd(w<sub>1</sub>, w<sub>2</sub>) = 3, hd(w<sub>1</sub>, w<sub>3</sub>) = 1, hd(w<sub>1</sub>, w<sub>4</sub>) = 2, hd(w<sub>1</sub>, w<sub>5</sub>) = 1 이다.</p>

<p>당신은 이진 코드들에 대해 해밍 경로(Hamming path)를 찾고자한다. 해밍 경로는 모든 인접한 두 코드사이의 해밍 거리가 1인 경로이다. 위의 예에서 (w<sub>1</sub>, w<sub>3</sub>, w<sub>4</sub>)는 길이가 3인 해밍 경로이지만, (w<sub>1</sub>, w<sub>5</sub>, w<sub>2</sub>)는 해밍 경로가 아니다. 두 코드 사이에 해밍 경로가 여러 개가 있을 경우 가장 짧은 경로를 찾고자 한다.</p>

<p>이 문제는 1번 코드에서부터 질의로 주어진 여러 개의 코드까지의 해밍 경로를 각각 구하는 프로그램을 작성하는 것이다.</p>

### 입력 

 <p>첫째 줄에는 두 개의 정수 N과 K가 빈칸을 사이에 두고 주어진다(3 ≤ N ≤ 100,000, 2 ≤ K ≤ 30). 둘째 줄부터 N개의 줄에는 각 줄마다 길이가 K인 이진 코드가 하나씩 주어진다. 하나의 코드는 빈칸이 없이 주어진다. 코드들은 주어진 순서대로 1부터 N까지의 번호로 구분된다. 코드가 같은 경우는 없다. 그 다음 줄에는 해밍 경로를 찾고자하는 질의의 수인 하나의 정수 M이 주어진다. (2 ≤ M ≤ 50) 그 다음 M개의 줄에는 각 줄마다 한 개의 양의 정수 J가 주어진다. (2 ≤ J ≤ N) J는 1번 코드와 J번 코드 사이의 해밍 경로를 구하라는 질의에 해당한다. 주어지는 J는 모두 다르다.</p>

### 출력 

 <p>출력은 M개의 줄로 구성된다. 각 줄에는 입력으로 주어진 각 질의에 대한 답을 순서대로 출력한다. 만일 두 코드 사이에 해밍 경로가 존재하면 가장 짧은 경로에 있는 코드들의 번호를 1번 코드부터 차례로 하나의 빈칸을 사이에 두고 출력한다. 답이 여러 개 있으면 그 중에 하나만 출력하고, 경로가 존재하지 않으면 -1을 출력한다.</p>


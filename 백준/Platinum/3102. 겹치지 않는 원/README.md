# [Platinum IV] 겹치지 않는 원 - 3102 

[문제 링크](https://www.acmicpc.net/problem/3102) 

### 성능 요약

메모리: 15944 KB, 시간: 148 ms

### 분류

다이나믹 프로그래밍

### 제출 일자

2025년 6월 14일 09:04:16

### 문제 설명

<p>X축 위에 중심 좌표가 C<sub>i</sub>이고 반지름이 R<sub>i</sub>인 원이 N개 있다.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/88bc7f25-27a5-48be-b181-c44b26ee248b/-/preview/" style="width: 365px; height: 235px;"></p>

<p>여기서 원을 몇 개를 지우면 모든 원이 서로 교차하지 않을까?</p>

<p>두 원이 접하는 경우는 교차하지 않는 것으로 생각한다.</p>

<p>원이 주어졌을 때, 모든 원이 서로 교차하지 않기 위해 지워야 하는 원의 최소 개수를 구하는 프로그램을 작성하시오.</p>

### 입력 

 <p>첫째 줄에 원의 개수 N이 주어진다. (1 ≤ N ≤ 1,000)</p>

<p>다음 N개의 줄에는 두 정수 C<sub>i</sub>와 R<sub>i</sub>가 주어진다. C<sub>i</sub>는 i번째 원의 중심 좌표이고, R<sub>i</sub>는 그 원의 반지름이다. (1 ≤ C<sub>i</sub>, R<sub>i</sub> ≤ 100) </p>

<p>두 원이 반지름과 중심 좌표가 모두 같은 경우는 없다.</p>

### 출력 

 <p>첫째 줄에 원이 서로 교차하지 않기 위해 지워야 하는 원의 최소 개수를 출력한다.</p>


# [Platinum II] Blowing Candles - 15420 

[문제 링크](https://www.acmicpc.net/problem/15420) 

### 성능 요약

메모리: 103080 KB, 시간: 972 ms

### 분류

볼록 껍질, 기하학, 회전하는 캘리퍼스

### 제출 일자

2024년 8월 20일 18:41:49

### 문제 설명

<p style="text-align:center"><img alt="" src="https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/15420/1.png" style="height:268px; width:268px"></p>

<p>As Jacques-Édouard really likes birthday cakes, he celebrates his birthday every hour, instead of every year. His friends ordered him a round cake from a famous pastry shop, and placed candles on its top surface. The number of candles equals the age of Jacques-Édouard in hours. As a result, there is a huge amount of candles burning on the top of the cake. Jacques-Édouard wants to blow all the candles out in one single breath.</p>

<p>You can think of the flames of the candles as being points in the same plane, all within a disk of radius R (in nanometers) centered at the origin. On that same plane, the air blown by Jacques-Édouard follows a trajectory that can be described by a straight strip of width W, which comprises the area between two parallel lines at distance W, the lines themselves being included in that area. What is the minimum width W such that Jacques-Édouard can blow all the candles out if he chooses the best orientation to blow?</p>

### 입력 

 <p>The first line consists of the integers N and R, separated with a space, where N is Jacques-Édouard’s age in hours. Then N lines follow, each of them consisting of the two integer coordinates xi and yi of the ith candle in nanometers, separated with a space.</p>

<p>Limits</p>

<ul>
	<li>3 ≤ N ≤ 2 · 10<sup>5</sup>;</li>
	<li>10 ≤ R ≤ 2 · 10<sup>8</sup>;</li>
	<li>for 1 ≤ i ≤ N, x<sub>i</sub><sup>2</sup> + y<sub>i</sub><sup>2</sup> ≤ R<sup>2</sup>;</li>
	<li>all points have distinct coordinates.</li>
</ul>

### 출력 

 <p>Print the value W as a floating point number. An additive or multiplicative error of 10<sup>−5</sup> is tolerated: if y is the answer, any number either within [y − 10<sup>−5</sup> ; y + 10<sup>−5</sup>] or within [(1 − 10<sup>−5</sup>)y;(1 + 10<sup>−5</sup>)y] is accepted.</p>


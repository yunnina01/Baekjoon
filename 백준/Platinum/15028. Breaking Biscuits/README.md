# [Platinum III] Breaking Biscuits - 15028 

[문제 링크](https://www.acmicpc.net/problem/15028) 

### 성능 요약

메모리: 14248 KB, 시간: 104 ms

### 분류

볼록 껍질, 기하학, 회전하는 캘리퍼스

### 제출 일자

2024년 8월 24일 12:49:42

### 문제 설명

<p>This year, Walter’s workplace resolved to try something radically different: they’re going to change the weekly order of biscuits for the break room to a whole other brand.</p>

<p>Biscuits come in many shapes and sizes, but the particular brand they settled on has two special qualities:</p>

<ul>
	<li>It is completely planar (two-dimensional);</li>
	<li>It is perfectly polygon-shaped.</li>
</ul>

<p>However, disaster struck immediately: the available mugs in the break room are too narrow for Walter to be able to dunk these new biscuits into, no matter what combination of rotations along the three axes he tries.</p>

<p>There is only one thing for it: Walter will need to order another mug.</p>

<p>Before taking this drastic step, it is vital to know how wide the diameter of this mug will need to be in order to succesfully accommodate a (possibly rotated) biscuit of the new brand.</p>

### 입력 

 <ul>
	<li>One line containing an integer N (3 ≤ N ≤ 100), the number of vertices in the biscuit.</li>
	<li>Each of the following N lines contains two space-separated integers X<sub>i</sub> and Y<sub>i</sub> (−10<sup>5</sup> ≤ X<sub>i</sub>, Y<sub>i</sub> ≤ 10<sup>5</sup>), the coordinates of the i-th vertex.</li>
</ul>

<p>Vertices are always given in anti-clockwise order. Also, as anyone can tell you, biscuits never self-intersect and always have positive area.</p>

### 출력 

 <p>Output the minimum possible diameter of the new mug, in order that it can fit the new kind of biscuit fully inside in at least one orientation. The output must be accurate to an absolute or relative error of at most 10<sup>−6</sup>.</p>


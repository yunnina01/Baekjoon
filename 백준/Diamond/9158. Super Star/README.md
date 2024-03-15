# [Diamond V] Super Star - 9158 

[문제 링크](https://www.acmicpc.net/problem/9158) 

### 성능 요약

메모리: 18512 KB, 시간: 276 ms

### 분류

기하학, 3차원 기하학, 휴리스틱, 최소 외접원

### 제출 일자

2024년 3월 15일 10:23:56

### 문제 설명

<p>During a voyage of the starship Hakodate-maru (see Problem A), researchers found strange synchronized movements of stars. Having heard these observations, Dr. Extreme proposed a theory of "super stars". Do not take this term as a description of actors or singers. It is a revolutionary theory in astronomy.</p>

<p>According to this theory, stars we are observing are not independent objects, but only small portions of larger objects called super stars. A super star is filled with invisible (or transparent) material, and only a number of points inside or on its surface shine. These points are observed as stars by us.</p>

<p>In order to verify this theory, Dr. Extreme wants to build motion equations of super stars and to compare the solutions of these equations with observed movements of stars. As the first step, he assumes that a super star is sphere-shaped, and has the smallest possible radius such that the sphere contains all given stars in or on it. This assumption makes it possible to estimate the volume of a super star, and thus its mass (the density of the invisible material is known).</p>

<p>You are asked to help Dr. Extreme by writing a program which, given the locations of a number of stars, finds the smallest sphere containing all of them in or on it. In this computation, you should ignore the sizes of stars. In other words, a star should be regarded as a point. You may assume the universe is a Euclidean space.</p>

### 입력 

 <p>The input consists of multiple data sets. Each data set is given in the following format.</p>

<pre>n
x<sub>1</sub> y<sub>1</sub> z<sub>1</sub>
x<sub>2</sub> y<sub>2</sub> z<sub>2</sub>
...
x<sub>n</sub> y<sub>n</sub> z<sub>n</sub>
</pre>

<p>The first line of a data set contains an integer n, which is the number of points. It satisfies the condition 4 < n < 30.</p>

<p>The locations of n points are given by three-dimensional orthogonal coordinates: (x<sub>i</sub>, y<sub>i</sub>, z<sub>i</sub>) (i = 1,...,n). Three coordinates of a point appear in a line, separated by a space character.</p>

<p>Each value is given by a decimal fraction, and is between 0.0 and 100.0 (both ends inclusive). Points are at least 0.01 distant from each other.</p>

<p>The end of the input is indicated by a line containing a zero.</p>

### 출력 

 <p>For each data set, the radius of the smallest sphere containing all given points should be printed, each in a separate line. The printed values should have 5 digits after the decimal point. They may not have an error greater than 0.00001.</p>


# [Platinum V] Kronican - 13941 

[문제 링크](https://www.acmicpc.net/problem/13941) 

### 성능 요약

메모리: 18764 KB, 시간: 1460 ms

### 분류

비트마스킹, 다이나믹 프로그래밍, 비트필드를 이용한 다이나믹 프로그래밍

### 제출 일자

2025년 4월 2일 06:01:43

### 문제 설명

<p>Little Mislav owns N glasses of infinite volume, and each glass contains some water. Mislav wants to drink all the water, but he doesn’t want to drink from more than K glasses. What Mislav can do with the glasses is pour the total volume of water from one glass to another.</p>

<p>Unfortunately, it matters to Mislav what glass he picks, because not all glasses are equally distant to him. More precisely, the amount of effort it takes to pour water from glass labeled with i to glass labeled j is denoted with C<sub>ij</sub>.</p>

<p>Help Mislav and find the order of pouring the water from one glass to another such that the sum of effort is minimal. </p>

### 입력 

 <p>The first line of input contains integers N, K (1 ≤ K ≤ N ≤ 20).</p>

<p>The following N lines contains N integers C<sub>ij</sub> (0 ≤ C<sub>ij</sub> ≤ 10<sup>5</sup> ). The ith row and jth column contains value C<sub>ij</sub> . It will hold that each Cii is equal to 0. </p>

### 출력 

 <p>Output the minimal effort needed for Mislav to achieve his goal. </p>


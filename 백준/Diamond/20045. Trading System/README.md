# [Diamond V] Trading System - 20045 

[문제 링크](https://www.acmicpc.net/problem/20045) 

### 성능 요약

메모리: 217356 KB, 시간: 1204 ms

### 분류

이분 탐색, 자료 구조, 누적 합, 우선순위 큐, 퍼시스턴트 세그먼트 트리, 세그먼트 트리

### 제출 일자

2025년 2월 22일 12:29:35

### 문제 설명

<p>SY Company wants to improve its stock trading system. For this, the company decides to utilize the information on the fluctuation of the stock prices. The fluctuation value is the difference in stock prices for two consecutive days. The company collects <em>n</em> recent fluctuation values for some stock. It turns out that the stock volatility is greatly affected by the largest sum of the contiguous fluctuation values. Finding such contiguous fluctuation values whose sum is the maximum is known as the <em>largest sum contiguous subarray problem</em> in computer science, where input values are stored in an array. It is natural that utilizing the <em>k</em>(≥ 1) largest contiguous sums rather than the largest one will help improve the trading system.</p>

<p>Write a program to find the <em>k</em> largest sums of contiguous fluctuation values for the given <em>n</em> fluctuation values and a positive integer <em>k</em>.</p>

### 입력 

 <p>Your program is to read from standard input. The input starts with a line containing two integers, <em>n</em> and <em>k</em>, where 1 ≤ <em>n</em> ≤ 250,000 and 1 ≤ k ≤ min(10,000, <em>n</em>(<em>n</em> + 1)/2). The next line contains <em>n</em> integers representing <em>n</em> fluctuation values. All fluctuation values are between −10<sup>9</sup> and 10<sup>9</sup> inclusively.</p>

### 출력 

 <p>Your program is to write to standard output. Print exactly one line. The line should contain the <em>k</em> largest sums of contiguous fluctuation values in non-increasing order. Note that any contiguous sum is the sum of one or more consecutive fluctuation values.</p>


# [Platinum II] Concert Hall Scheduling - 3938 

[문제 링크](https://www.acmicpc.net/problem/3938) 

### 성능 요약

메모리: 17932 KB, 시간: 188 ms

### 분류

그래프 이론, 최대 유량, 최소 비용 최대 유량

### 제출 일자

2025년 6월 7일 10:37:11

### 문제 설명

<p>You are appointed director of a famous concert hall, to save it from bankruptcy. The hall is very popular, and receives many requests to use its two fine rooms, but unfortunately the previous director was not very efficient, and it has been losing money for many years. The two rooms are of the same size and arrangement. Therefore, each applicant wishing to hold a concert asks for a room without specifying which. Each room can be used for only one concert per day.</p>

<p>In order to make more money, you have decided to abandon the previous fixed price policy, and rather let applicants specify the price they are ready to pay. Each application shall specify a period [i, j] and an asking price w, where i and j are respectively the first and last days of the period (1 ≤ i ≤ j ≤ 365), and w is a positive integer in yen, indicating the amount the applicant is willing to pay to use a room for the whole period.</p>

<p>You have received applications for the next year, and you should now choose the applications you will accept. Each application should be either accepted for its whole period or completely rejected. Each concert should use the same room during the whole applied period.</p>

<p>Considering the dire economic situation of the concert hall, artistic quality is to be ignored, and you should just try to maximize the total income for the whole year by accepting the most profitable applications.</p>

### 입력 

 <p>The input has multiple data sets, each starting with a line consisting of a single integer n, the number of applications in the data set. Then, it is followed by n lines, each of which represents one application with a period [i, j] and an asking price w yen in the following format.</p>

<pre>i j w</pre>

<p>A line containing a single zero indicates the end of the input.</p>

<p>The maximum number of applications in a data set is one thousand, and the maximum asking price is one million yen.</p>

### 출력 

 <p>For each data set, print a single line containing an integer, the maximum total income in yen for the data set.</p>


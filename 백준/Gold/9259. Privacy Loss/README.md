# [Gold IV] Privacy Loss - 9259 

[문제 링크](https://www.acmicpc.net/problem/9259) 

### 성능 요약

메모리: 16816 KB, 시간: 172 ms

### 분류

다이나믹 프로그래밍, 배낭 문제

### 제출 일자

2024년 11월 7일 20:22:03

### 문제 설명

<p>At the heart of a lot of the ethical debate about whether the government should be allowed to spy on its citizens<sup>3</sup> is the question of whether “privacy” is in any sense worth preserving. An argument often suggested by proponents of government spying is that the only ones who want privacy are those who intend to engage in illegal activities. People offering this argument often quickly retreat when asked very personal questions about their behavior behind closed doors. Indeed, a lot of “privacy” is about protecting behavior that is not illegal, but may not be universally appreciated by others, so that we would rather others not know about it. For example, for most people, a wide circulation of their most heartfelt love letter, or pictures of them passed out on a couch drooling after a hard day at work, or similar, would be highly embarrassing.</p>

<p>The problem is that most methods proposed in the name of security have both a benefit in terms of security, and a cost in terms of privacy (as well as a cost in terms of money or effort). For instance, installing security cameras in every room of every house and apartment and analyzing the video stream would likely prevent some crimes and terrorist attacks, but also massively invade people’s privacy (besides costing a fortune). Monitoring all e-mails sent may be a somewhat smaller invasion of privacy, and also less effective and cheaper. In designing “acceptable” combinations of programs, it is therefore important to trade off the loss in privacy and the financial cost with the gain in security.</p>

<p>You will be given descriptions of a number of surveillance options, in terms of their security benefit, privacy cost, and financial cost. You will also be given a financial budget and acceptable total level of privacy invasion. Your goal is to determine the maximum total amount of security that could be achieved without violating those constraints.</p>

<p><sup>3</sup>As opposed to the legal debate about whether according to current laws, the government is allowed to do so.</p>

### 입력 

 <p>The first line is the number K of input data sets, followed by the K data sets, each of the following form:</p>

<p>The first line contains three integers n, B, P, separated by spaces. 1 ≤ n ≤ 100 is the number of surveillance options you are considering. 0 ≤ B ≤ 100 is the total budget for surveillance, and 0 ≤ P ≤ 100 is the maximum total privacy cost that is acceptable to society.</p>

<p>This is followed by n lines, each containing three integers s<sub>i</sub>, c<sub>i</sub>, p<sub>i</sub>. 0 ≤ s<sub>i</sub> ≤ 1000 is the security benefit of option i. 0 ≤ c<sub>i</sub> ≤ 100 is the cost of option i, and 0 ≤ p<sub>i</sub> ≤ 100 the privacy loss.</p>

### 출력 

 <p>For each data set, output “Data Set x:” on a line by itself, where x is its number. Then, output the maximum amount of security that can be gained without violating either the budget or the privacy constraint. Each data set should be followed by a blank line.</p>


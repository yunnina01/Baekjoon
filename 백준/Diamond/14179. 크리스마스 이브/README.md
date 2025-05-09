# [Diamond V] 크리스마스 이브 - 14179 

[문제 링크](https://www.acmicpc.net/problem/14179) 

### 성능 요약

메모리: 575572 KB, 시간: 2444 ms

### 분류

분할 정복, 분할 정복을 사용한 최적화, 다이나믹 프로그래밍, 누적 합

### 제출 일자

2025년 4월 1일 06:02:52

### 문제 설명

<p>사람들에게 산타라는 직업은 하루 벌어 1년을 먹고 사는 매우 편한 직종으로 여겨지곤 한다. 하지만 북극에서 근무중인 수많은 산타들은 이런 편견에 대해서 억울해한다. 산타는 생각보다 해야할 일이 많기 때문이다. 전세계에 있는 어린이들을 모두 사찰해야하며 이 아이들이 행한 선행과 악행 목록을 리스트로 관리해 채점도 해야하고 그 점수를 기반으로 선물을 배송여부를 평가해야하기 때문이다. 게다가 매년 12월 24일에서 25일로 넘어가는 저녁부터 새벽동안 선물을 배송해야 하는데 그 사이에도 선행 악행 점수반영도 계속 해야하기 때문에 추가 선물 배달이 생기거나 미배달분이 생기기도 한다.</p>

<p>과거 산타의 업무가 편했던 때는 가끔씩 핫초코 한 잔을 즐기는 여유를 부릴 수도 있었지만 1948년 NORAD(북미항공우주방위사령부)가 배송추적을 시작한 이후로는 잠시라도 쉬면 독촉 전화를 받게 되어 크리스마스 기간동안은 쉴 수도 없는 극한 직업이다.</p>

<p>그렇지만 시대가 발전하며서 산타 산업도 기업화되어 산타 코퍼레이션이란 이름으로 활동하고 있다. 어린 아이들의 선행과 악행을 사찰하는 정보부, 선물의 수급을 담당하는 물품관리부, 각종 마트와 행사장에 나가 아이들의 소원을 들어주는 영업부까지 수많은 산타들이 어린이들의 동심을 지켜주기 위해 노력하고 있다. 그렇다면 가장 궁금한 배송은 누가 할까? 크리스마스 이브에서 크리스마스까지 수많은 아이들에게 선물을 배달하는 업무를 맡은 산타는 자원형식으로 이루어진다. 일이 매우 고되기 때문에 배송업무를 맡은 산타는 1년간 휴가를 갈 수 있게 된다.</p>

<p>배송업무를 자원한 산타에게는 하이테크 썰매가 한대씩 지급된다. 이 하이테크 썰매는 특별한 트렁크가 있는데 이 트렁크는 물품관리부가 관리하는 창고 하나와 순간이동기로 연결되어 있다. 하지만 배송을 더 유연하게 하기 위해서 창고 한 곳에 순간이동기를 한 대씩만 설치할 수 있기에 썰매 한대당 창고 하나가 필요하다. 하지만 배송업무를 맡는 산타도 매년마다 다르고 전세계 아이들이 받을 선물도 미리 관리해야 하기 때문에 배송 시작전마다 창고 정리를 해야한다.</p>

<p>산타 코퍼레이션의 물품관리부는 일렬로 세워진 창고 n개를 가지고 있다. 이 중 배송업무를 맡을 k명의 산타들이 지급받은 하이테크 썰매와 연결시킬 k개의 창고를 선택해야한다.</p>

<p>i번째 창고에는 w<sub>i</sub>톤의 선물이 들어있다. 창고에 있는 선물을 옮기기 위해서는 한번에 모두 다른 창고로 이동시켜야한다. (예를 들어 i번째 창고에서 j번째 창고로 한번에 옮겨야 한다.)</p>

<p>x<sub>i</sub>에 위치한 창고 i에서 x<sub>j</sub>에 위치한 창고 j로 선물 w톤을 옮긴다고 하면 그 운반 비용은 |x<sub>i</sub>-x<sub>j</sub>|*w이다.</p>

<p>산타 코퍼레이션은 어린이들의 꿈과 희망을 지켜주지만 그래도 기업이므로 최소의 지출을 하려한다.</p>

<p>순간이동기를 설치할 k개의 창고를 결정할때 산타 코퍼레이션은 가장 운반 비용이 가장 적게 들도록 결정해야한다. 이때 최소 운반비용을 구해보자.</p>

### 입력 

 <p>첫 번째 줄에는 두개의 정수 n과 k가 주어진다. n은 전체 창고 개수이고 k는 설치해야할 순간이동기 개수이다. (1 ≤ k < n ≤ 5000)</p>

<p>이후 입력되는 i개의 줄에는 창고의 위치 x<sub>i</sub>와 i번째 창고에 들어있는 선물의 무게 w<sub>i</sub>가 입력된다. (1 ≤ w<sub>i</sub>, x<sub>i</sub> ≤ 10<sup>6</sup>)</p>

### 출력 

 <p>k개의 창고로 선물을 다 옮기고 난 후의 최소 운반비용을 출력하라.</p>


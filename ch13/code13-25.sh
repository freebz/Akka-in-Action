# 코드 13-25 httpie에서 Content-Type 헤더를 사용해 POST하는 예제

http -v POST localhost:5000/logs/1 Content-Type:text/plain < test.log
http -v POST localhost:5000/logs/2 Content-Type:application/json < test.json

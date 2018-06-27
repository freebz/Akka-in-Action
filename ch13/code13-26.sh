# 코드 13-26 httpie에서 Accept 헤더를 사용하는 GET 요청 예제

http -v GET localhost:5000/logs/1 'Accept:application/json'
http -v GET localhost:5000/logs/1 'Accept:text/plain'
http -v GET localhost:5000/logs/1 \
'Accept: text/html, text/plain;q=0.8, application/json;q=0.5'

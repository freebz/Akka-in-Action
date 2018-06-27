// 코드 13-38 심각한 이벤트가 한 개만 들어있는 요약

val toNot = Flow[Event].map(e=> Summary(Vector(e)))

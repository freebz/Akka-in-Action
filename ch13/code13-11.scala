// 코드 13-11 이벤트 직렬화하기

val serialize: Flow[Event, ByteString, NotUsed] =
  Flow[Event].map(event => ByteString(event.toJson.compactPrint))

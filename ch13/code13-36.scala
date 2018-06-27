// 코드 13-36 그래프 노드 만들기

val bcast = builder.add(Broadcast[Event](5))
val wbcast = builder.add(Broadcast[Event](2))
val ebcast = builder.add(Broadcast[Event](2))
val cbcast = builder.add(Broadcast[Event](2))
val okcast = builder.add(Broadcast[Event](2))

val mergeNotify = builder.add(MergePreferred[Summary](2))
val archive = builder.add(jsFlow)

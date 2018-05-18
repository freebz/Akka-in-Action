// 코드 5-1 동기 호출

val request = EventRequest(ticktNr)
val response: EventResponse = callEventService(request)
val event: Event = response.event

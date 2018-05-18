// 코드 5-21 for 내장과 traverse를 사용해 map 수행하기

def getSuggestions(event: Event): Future[Seq[Event]] = {

  val futureArtists: Future[Seq[Artists]] = callSimilarArtistsService(event)

  for(
    artists <- futureArtists
    events <- getPlannedEvents(event, artists)
  ) yeild events
}

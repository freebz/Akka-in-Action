// 코드 5-23 traverse를 사용해 조합하기

def getPlannedEventsWithTraverse(
  event: Event,
  artists: Seq[Artist]
): Future[Seq[Event]] = {
  Future.traverse(artist) { artist =>
    callArtistCalendarService(artist, event.location)
  }
}

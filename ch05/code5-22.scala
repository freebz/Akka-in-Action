// 코드 5-22 sequence를 사용해 퓨처 리스트 조합하기

def getPlannedEvents(event: Event,
                     artists: Seq[Artist]): Future[Seq[Event]] = {

  val events: Seq[Future[Event]] = artists.map { artist =>
    callArtistCalendarService(artist, event.location)
  }
  Future.sequence(events)
}

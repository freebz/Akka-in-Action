// 코드 15-20 ReadJournal 얻기

implicit val mat = ActorMaterializer()(system)
val queries =
  PersistenceQuery(system).readJournalFor[LeveldbReadJournal](
    LeveldbReadJournal.Identifier
  )

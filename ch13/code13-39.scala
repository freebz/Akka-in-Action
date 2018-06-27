// 코드 13-39 groupedWithin을 사용해 Event 모으기

def rollup(nr: Int, duration: FiniteDuration) =
  Flow[Event].groupedWithin(nr, duration)
    .map(events => Summary(events.toVector))

val rollupErr = rollup(nrErrors, errDuration)
val rollupWarn = rollup(nrWarinings, warnDuration)

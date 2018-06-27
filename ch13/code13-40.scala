// 코드 13-40 Event를 Metric으로 변환하기

val toMetric = Flow[Event].collect {
  case Event(_, service, _, time, Some(tag), Some(metric)) =>
    Metric(service, time, metric, tag)
}

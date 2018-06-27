// 코드 13-41 Metric에 drift 정보 추가하기

val recordDrift = Flow[Metric]
  .expand { metric =>
    Iterator.from(0).map(d => metric.copy(drift = d))
  }

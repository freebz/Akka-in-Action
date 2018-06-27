// 코드 13-37 그래프 노드 연결하기

bcast ~> archBuf  ~> archive.in
bcast ~> ok       ~> okcast
bcast ~> warning  ~> wbcast
bcast ~> error    ~> ebcast
bcast ~> critical ~> cbcast

okcast ~> jsFlow ~> logFileSink(logId, Ok)
okcast ~> toNot ~> mergeNotify.preferred

ebcast ~> jsFlow ~> logFileSink(logId, Error)
ebcast ~> errBuf ~> rollupErr ~> mergeNotify.in(0)

wbcast ~> jsFlow ~> logFileSink(logId, Warning)
wbcast ~> warnBuf ~> rollupWarn ~> mergeNotify.in(1)

mergeNotify ~> notifyOutFlow ~> notificationSink

FlowShape(bcast.in, archive.out)

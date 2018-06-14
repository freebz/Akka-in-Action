// 코드 12-6 CamelContext로 브로커 설정 추가하기

val camelContext = CamelExtension(system).context
camelContext.addComponent("activemq",
  ActiveMQComponent.activeMQComponent(
    "vm:(broker:(tcp://localhost:8889)?persistent=false)"))

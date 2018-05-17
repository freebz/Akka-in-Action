// 코드 4-12 로그 처리 애플리케이션에서 사용하는 오류

@SerialVersionUID(1L)
class DiskError(msg: String)
    extends Error(msg) with Serializable

@SerialVersionUID(1L)
class CorruptedFileException(msg: String, val file: File)
    extends Exception(msg) with Serializable

@SerialVersionUID(1L)
class DbNodeDownException(msg: String)
    extends Exception(msg) with Serializable

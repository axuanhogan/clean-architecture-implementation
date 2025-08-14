package personal.my.core.exception

data class UseCaseException(
    val code: String,
    override val message: String,
    override val cause: Throwable? = null,
    val extra: Any? = null,
): Exception(message, cause)

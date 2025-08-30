package com.axuanhogan.common.util.pagination

object CursorPaginationUtil {

    fun <T : ExecuteData> execute(
        cursor: Cursor? = null,
        filter :Filter,
        data: List<T>,
    ): Result<T> {

        val paginatedData = if (cursor == null) {
            val index = 0
            data.drop(index).take(filter.pageSize)
        } else {
            val index = try {
                data.indexOfFirst {
                    it.id == cursor.key
                }
            } catch (e: Exception) {
                0
            }
            when (cursor.director) {
                Director.NEXT -> {
                    data.drop(index).take(filter.pageSize)
                }
                Director.PREVIOUS -> {
                    data.dropLast(data.size - 1 - index).takeLast(filter.pageSize)
                }
            }
        }

        val prevCursor = data.getOrNull(data.indexOfFirst { it.id == paginatedData.first().id } - 1)?.let {
            Cursor(
                key = it.id,
                director = Director.PREVIOUS,
                filter = Filter(
                    pageSize = filter.pageSize,
                    startDateTime = filter.startDateTime,
                    endDateTime = filter.endDateTime,
                )

            )
        }

        val nextCursor = data.getOrNull(data.indexOfFirst { it.id == paginatedData.last().id } + 1)?.let {
            Cursor(
                key = it.id,
                director = Director.NEXT,
                filter = Filter(
                    pageSize = filter.pageSize,
                    startDateTime = filter.startDateTime,
                    endDateTime = filter.endDateTime,
                )
            )
        }

        return Result(
            paginatedData = paginatedData,
            prevCursor = prevCursor,
            nextCursor = nextCursor,
        )
    }

    interface ExecuteData {
        val id: String
    }

    data class Cursor(
        val key: String,
        val director: Director,
        val filter: Filter,
    )

    data class Filter(
        val pageSize: Int = 20,
        val startDateTime: String,
        val endDateTime: String,
    )

    enum class Director {
        NEXT,
        PREVIOUS
    }

    data class Result<T>(
        val paginatedData: List<T>,
        val prevCursor: Cursor? = null,
        val nextCursor: Cursor? = null,
    )
}

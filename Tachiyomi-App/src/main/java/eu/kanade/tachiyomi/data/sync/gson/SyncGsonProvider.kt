package eu.kanade.tachiyomi.data.sync.gson

import com.google.gson.GsonBuilder
import eu.kanade.tachiyomi.data.sync.protocol.models.common.SyncEntity

/**
 * Custom GSON instance for sync process
 */
object SyncGsonProvider {
    val gson = GsonBuilder()
            .registerTypeAdapter(SyncEntity::class.java, SyncEntityAdapter())
            .create()!!
}
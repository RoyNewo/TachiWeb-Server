/*
 * Copyright 2016 Andy Bao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package xyz.nulldev.ts.api.http.library

import org.json.JSONArray
import spark.Request
import spark.Response
import xyz.nulldev.ts.api.http.TachiWebRoute
import xyz.nulldev.ts.api.http.serializer.MangaSerializer
import xyz.nulldev.ts.ext.kInstanceLazy

/**
 * Project: TachiServer
 * Author: nulldev
 * Creation Date: 30/09/16
 */
class LibraryRoute : TachiWebRoute() {

    private val mangaSerializer: MangaSerializer by kInstanceLazy()

    override fun handleReq(request: Request, response: Response): Any {
        val array = JSONArray()
        for (manga in api.database.getLibraryMangas().executeAsBlocking()) {
            array.put(mangaSerializer.serialize(manga, true))
        }
        return success().put(KEY_CONTENT, array)
    }

    companion object {
        val KEY_CONTENT = "content"
    }
}

package com.tikalk.mvvm.model

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type


class IssuesJsonDesrializer : JsonDeserializer<Issue> {
    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Issue {

        val jObject = json.asJsonObject
        val user = jObject.get("user").asJsonObject

        return Issue(
                id = jObject.get("id").asInt,
                title = jObject.get("title").asString,
                number = jObject.get("number").asInt,
                userName = user.get("login").asString)
    }
}
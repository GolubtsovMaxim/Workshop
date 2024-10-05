package com.example.teamcity.api.generators

import com.example.teamcity.api.enums.Endpoint
import com.example.teamcity.api.models.BaseModel
import com.example.teamcity.api.requests.unchecked.UncheckedBase
import java.util.EnumMap

object TestDataStorage {
    val createdEntitiesMap : EnumMap<Endpoint, MutableSet<String>> = EnumMap(Endpoint::class.java)

    fun addCreatedEntity(endpoint: Endpoint, id: String) {
        createdEntitiesMap.getOrPut(endpoint) { HashSet() }.add(id)
    }

    fun addCreatedEntity(endpoint: Endpoint, baseModel: BaseModel) {
        addCreatedEntity(endpoint, getEntityIdOrLocator(baseModel)!!)
    }

    private fun getEntityIdOrLocator(model: BaseModel): String? {
        return try {
            model::class.java.getDeclaredField("id").let { field ->
                field.isAccessible = true
                field.get(model)?.toString().also { field.isAccessible = false }
            }
        } catch (e: NoSuchFieldException) {
            try {
                model::class.java.getDeclaredField("locator").let { field ->
                    field.isAccessible = true
                    field.get(model)?.toString().also { field.isAccessible = false }
                }
            } catch (ex: NoSuchFieldException) {
                throw IllegalStateException("Cannot get id or locator of entity", ex)
            } catch (ex: IllegalAccessException) {
                throw IllegalStateException("Cannot access field for locator", ex)
            }
        } catch (e: IllegalAccessException) {
            throw IllegalStateException("Cannot access field for id", e)
        }
    }


    fun deleteCreatedEntities() {
        createdEntitiesMap.forEach { (endpoint, ids) ->
            ids.forEach { id ->
                UncheckedBase(Specifications.retriveSpec().superUserAuth(), endpoint).delete(id)
            }
        }

        createdEntitiesMap.clear()
    }
}
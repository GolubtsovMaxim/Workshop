package com.example.teamcity.api.generators

import com.example.teamcity.api.annotations.Optional
import com.example.teamcity.api.annotations.Parameterizable
import com.example.teamcity.api.annotations.Random
import com.example.teamcity.api.models.BaseModel
import com.example.teamcity.api.models.TestData
import java.lang.reflect.ParameterizedType

object TestDataGenerator {
    @Suppress("UNCHECKED_CAST")
    fun <T : BaseModel> generate(
        generatedModels: List<BaseModel>,
        generatorClass: Class<T>,
        vararg parameters: Any?
    ): T {
        return try {
            val instance = generatorClass.getDeclaredConstructor().newInstance()
            var params = parameters
            for (field in generatorClass.declaredFields) {
                field.isAccessible = true
                if (!field.isAnnotationPresent(Optional::class.java)) {
                    val generatedClass = generatedModels.firstOrNull { it::class.java == field.type }
                    when {
                        field.isAnnotationPresent(Parameterizable::class.java) && params.isNotEmpty() -> {
                            field.set(instance, params[0])
                            params = params.copyOfRange(1, params.size)
                        }
                        field.isAnnotationPresent(Random::class.java) && field.type == String::class.java -> {
                            field.set(instance, RandomData.getString())
                        }
                        field.isAnnotationPresent(Random::class.java) && field.type == Int::class.java -> {
                            field.set(instance, RandomData.getInt())
                        }
                        BaseModel::class.java.isAssignableFrom(field.type) -> {
                            val finalParams = params
                            field.set(
                                instance,
                                generatedClass ?: generate(generatedModels, field.type.asSubclass(BaseModel::class.java), *finalParams)
                            )
                        }
                        List::class.java.isAssignableFrom(field.type) -> {
                            val pt = field.genericType as? ParameterizedType
                            val typeClass = pt?.actualTypeArguments?.firstOrNull() as? Class<*>
                            if (typeClass != null && BaseModel::class.java.isAssignableFrom(typeClass)) {
                                val finalParams = params
                                field.set(
                                    instance,
                                    generatedClass?.let { listOf(it) } ?: listOf(
                                        generate(generatedModels, typeClass.asSubclass(BaseModel::class.java), *finalParams)
                                    )
                                )
                            }
                        }
                    }
                }
                field.isAccessible = false
            }
            instance
        } catch (e: Exception) {
            e.printStackTrace()
            throw IllegalStateException("Cannot generate test data", e)
        }
    }

    // Метод, чтобы сгенерировать одну сущность. Передает пустой параметр generatedModels
    fun <T : BaseModel> generate(generatorClass: Class<T>, vararg parameters: Any): T {
        return generate(emptyList(), generatorClass, *parameters)
    }

    fun generate(): TestData {
        // итерируемся по полям TestData и для каждого наследника BaseModel
        return try {
            val instance = TestData::class.java.getDeclaredConstructor().newInstance()
            val generatedModels = mutableListOf<BaseModel>()

            TestData::class.java.declaredFields.forEach { field ->
                field.isAccessible = true
                if (BaseModel::class.java.isAssignableFrom(field.type)) {
                    val generatedModel = generate(generatedModels, field.type.asSubclass(BaseModel::class.java))
                    field.set(instance, generatedModel)
                    generatedModels.add(generatedModel)
                }
                field.isAccessible = false
            }
            instance
        } catch (e: Exception) {
            throw IllegalStateException("Cannot generate test data", e)
        }
    }
}
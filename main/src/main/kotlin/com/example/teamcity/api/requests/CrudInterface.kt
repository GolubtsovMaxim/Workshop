import com.example.teamcity.api.models.BaseModel

interface CrudInterface {
    fun create(model: BaseModel) : Any
    fun read(id: String) : Any
    fun update(id: String, model: BaseModel): Any
    fun delete(id: String): Any
}
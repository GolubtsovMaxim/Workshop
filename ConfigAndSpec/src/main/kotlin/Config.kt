import java.io.IOException
import java.io.InputStream
import java.util.Properties

class Config private constructor() {
    private val properties: Properties = Properties()
    private val CONFIG_PROPERTIES = "config.properties"

    companion object {
        private val config: Config by lazy { Config() }
        fun retrieveConfig(): Config {
            return config
        }

        fun getProperty(key: String): String? {
            return config.properties.getProperty(key)
        }
    }

    init {
        loadProperties(CONFIG_PROPERTIES)
    }

    private fun loadProperties(fileName: String) {
        try {
            val stream: InputStream? = Config::class.java.classLoader.getResourceAsStream(fileName)
            if (stream == null) {
                System.err.println("File not found: $fileName")
            } else {
                properties.load(stream)
            }
        } catch (e: IOException) {
            System.err.println("Error during file reading: $fileName")
            throw RuntimeException(e)
        }
    }
}
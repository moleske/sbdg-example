package engineer.oleske.sbdgexample

import org.springframework.context.annotation.Configuration
import org.springframework.data.gemfire.config.annotation.EnableCachingDefinedRegions
import org.springframework.data.gemfire.config.annotation.EnableClusterConfiguration
import org.springframework.data.gemfire.config.annotation.EnableLogging

@Configuration
@EnableLogging(logLevel = "error")
@EnableCachingDefinedRegions
@EnableClusterConfiguration(useHttp = true)
class GeodeConfiguration
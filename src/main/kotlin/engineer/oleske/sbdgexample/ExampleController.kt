package engineer.oleske.sbdgexample

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.GetMapping


private const val HEADER_ONE = "<h1>%s</h1>"

@RestController
class ExampleController(val exampleService: ExampleService) {
    @GetMapping("/ping")
    fun ping(): String = String.format(HEADER_ONE, "PONG")

    @GetMapping("counter/{name}")
    fun getCount(@PathVariable("name") counterName: String): String =
            String.format(HEADER_ONE, exampleService.getCount(counterName))

    @GetMapping("counter/{name}/cached")
    fun getCachedCount(@PathVariable("name") counterName: String): String =
            String.format(HEADER_ONE, exampleService.getCachedCount(counterName))

    @GetMapping("counter/{name}/reset")
    fun resetCounter(@PathVariable("name") counterName: String): String {
        exampleService.resetCounter(counterName)
        return String.format(HEADER_ONE, "0")
    }
}
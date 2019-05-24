package engineer.oleske.sbdgexample

import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.UUID.randomUUID


@WebMvcTest(ExampleController::class)
internal class ExampleControllerTest {
    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var exampleService: ExampleService


    @Test
    fun `calling ping returns Pong`() {
        mvc.perform(get("/ping"))
                .andExpect(status().isOk)
                .andExpect(content().string("<h1>PONG</h1>"))
    }

    @Test
    fun `calling counter returns Service getCount`() {
        val counterName = randomUUID().toString()
        val count = (0L..100L).random()
        `when`(exampleService.getCount(counterName)).thenReturn(count)

        mvc.perform(get("/counter/$counterName"))
                .andExpect(status().isOk)
                .andExpect(content().string("<h1>$count</h1>"))
    }

    @Test
    fun `calling couter cached returns Service getCachedCount`() {
        val counterName = randomUUID().toString()
        val count = (0L..100L).random()
        `when`(exampleService.getCachedCount(counterName)).thenReturn(count)

        mvc.perform(get("/counter/$counterName/cached"))
                .andExpect(status().isOk)
                .andExpect(content().string("<h1>$count</h1>"))
    }

    @Test
    fun `calling counter reset returns zero`() {
        val counterName = randomUUID().toString()

        mvc.perform(get("/counter/$counterName/reset"))
                .andExpect(status().isOk)
                .andExpect(content().string("<h1>0</h1>"))
    }

    @Test
    fun `calling counter reset calls Service resetCounter`() {
        val counterName = randomUUID().toString()

        mvc.perform(get("/counter/$counterName/reset"))

        verify(exampleService).resetCounter(counterName)
    }
}
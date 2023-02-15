package controller

import com.example.vtb.spock.template.controller.PaymentFeeController
import com.example.vtb.spock.template.exception.ErrorHandler
import com.example.vtb.spock.template.model.dto.PaymentFeeDto
import com.example.vtb.spock.template.service.PaymentFeeService
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static java.math.BigDecimal.ONE
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

class PaymentFeeControllerTest extends Specification {
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private PaymentFeeService paymentFeeService
    private MockMvc mockMvc

    void setup() {
        paymentFeeService = Mock()
        def paymentFeeController = new PaymentFeeController(paymentFeeService)
        mockMvc = MockMvcBuilders.standaloneSetup(paymentFeeController)
                .setControllerAdvice(new ErrorHandler())
                .build()
    }

    def "TestGetPaymentFee"() {
        given:
        def amount = random.nextObject(BigDecimal)
        def country = random.nextObject(String)
        def url = "/api/v1/payment-fee"

        def dto = new PaymentFeeDto(ONE)

        def responseView = dto

        def expectedResponse = '''
                {
                    "paymentFee": 1.0                     
                }
        '''

        when:
        def result = mockMvc.perform(get(url)
                .param("amount", amount as String)
                .param("country", country)
        ).andReturn()

        then:
        1 * paymentFeeService.getFee(amount, country) >> responseView

        def response = result.response
        response.status == HttpStatus.OK.value()
        JSONAssert.assertEquals(expectedResponse, response.getContentAsString(), false)
    }
}

package service

import com.example.vtb.spock.template.service.PaymentFeeService
import com.example.vtb.spock.template.util.PaymentFeeUtil
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification
import spock.lang.Unroll

class PaymentFeeServiceTest extends Specification {
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private PaymentFeeService paymentFeeService
    private PaymentFeeUtil paymentFeeUtil

    def setup() {
        paymentFeeUtil = Mock()
        paymentFeeService = new PaymentFeeService(paymentFeeUtil)
    }

    @Unroll
    def "TestGetFee"() {
        given:
        def country = random.nextObject(String)

        when:
        def actual = paymentFeeService.getFee(amount, country)

        then:
        callsCount * paymentFeeUtil.getFeeByCountry(country) >> BigDecimal.valueOf(2)
        actual.paymentFee == expected

        where:
        callsCount | amount                   | expected
        0          | BigDecimal.valueOf(50)   | BigDecimal.valueOf(50)
        1          | BigDecimal.valueOf(1301) | BigDecimal.valueOf(2602)
    }
}

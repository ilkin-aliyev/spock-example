package util

import com.example.vtb.spock.template.util.PaymentFeeUtil
import spock.lang.Specification
import spock.lang.Unroll

class PaymentFeeUtilTest extends Specification {
    private PaymentFeeUtil paymentFeeUtil

    def setup() {
        paymentFeeUtil = new PaymentFeeUtil()
    }

    @Unroll
    def "TestGetFeeByCountry"() {
        when:
        def actual = paymentFeeUtil.getFeeByCountry(country)

        then:
        actual == expected

        where:
        country      | expected
        "Azerbaijan" | BigDecimal.valueOf(1)
        "AZERBAIJAN" | BigDecimal.valueOf(1)
        "azerbaijan" | BigDecimal.valueOf(1)
        "Turkey"     | BigDecimal.valueOf(1.5)
        "TURKEY"     | BigDecimal.valueOf(1.5)
        "turkey"     | BigDecimal.valueOf(1.5)
        "Uk"         | BigDecimal.valueOf(2)
        "UK"         | BigDecimal.valueOf(2)
        "uk"         | BigDecimal.valueOf(2)
    }
}

package com.scaler.paymentservice.paymentgateways;

import com.stripe.Stripe;
import com.stripe.StripeClient;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StripePGAdapter implements PaymentGateway {

    @Value("${stripe.key}")
    private String stripeKey;

    @Override
    public String generatePaymentLink(Long orderId) throws StripeException {

        Stripe.apiKey = stripeKey;

//        ProductCreateParams productCreateParams = ProductCreateParams.builder()
//                .setDescription("Thank you Jaan")
//                .setName("Gift From Alisha To Mukund")
//                .setId("product_4")
//                .build();

//        Product product = Product.create(productCreateParams);

        PriceCreateParams priceCreateParams = PriceCreateParams.builder()
                .setCurrency("inr")
                .setUnitAmount(2800000L)
                .setProduct("product_1")
                .build();

        Price price = Price.create(priceCreateParams);

        PaymentLinkCreateParams params =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(
                                PaymentLinkCreateParams.AfterCompletion.builder()
                                        .setRedirect(
                                            PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                    .setUrl("https://www.youtube.com/")
                                                .build()
                                )
                                        .setType(
                                                PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT
                                        )
                                        .build()
                        )
                        .build();

        PaymentLink paymentLink = PaymentLink.create(params);

        return paymentLink.getUrl();
    }
}

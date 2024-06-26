package org.example.models;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class SabrePreOrderMeals {
    @JsonGetter("Orders")
    public List<Order> getOrders() {
        return this.orders;
    }


    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    List<Order> orders;
}


package com.mercadopago.resources.datastructures.payment;

import com.mercadopago.core.annotations.validation.NotNull;
import com.mercadopago.core.annotations.validation.Numeric;
import com.mercadopago.core.annotations.validation.Size;

/**
 * Mercado Libre MercadoPago
 * Item class
 *
 * Created by Eduardo Paoletta on 11/23/16.
 */
public class Item {

    @Size(max=256) private String id = null;
    @Size(max=256) private String title = null;
    @Size(max=256) private String description = null;
    @Size(max=600) private String pictureUrl = null;
    @Size(max=256) private String categoryId = null;
    @NotNull @Numeric(min=1) private Integer quantity = null;
    @NotNull private Float unitPrice = 0f;


    public String getId() {
        return id;
    }

    public Item setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Item setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Item setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public Item setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public Item setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Item setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public Item setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

}

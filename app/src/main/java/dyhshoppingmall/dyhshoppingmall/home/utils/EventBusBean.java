package dyhshoppingmall.dyhshoppingmall.home.utils;

/**
 * data:2017/5/3.
 * author:dingyonghui(lx)
 * function:
 */

public class EventBusBean {

    public String cover_price;
    public String name;
    public String figure;
    public String product_id;

    public EventBusBean(String cover_price, String name, String figure, String product_id) {
        this.cover_price = cover_price;
        this.name = name;
        this.figure = figure;
        this.product_id = product_id;
    }

    public String getCover_price() {
        return cover_price;
    }

    public void setCover_price(String cover_price) {
        this.cover_price = cover_price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}
